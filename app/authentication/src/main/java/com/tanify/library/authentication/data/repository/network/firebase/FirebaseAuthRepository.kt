package com.tanify.library.authentication.data.repository.network.firebase

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.tanify.library.authentication.data.dto.firestore.FireStoreUserModel
import com.tanify.library.authentication.data.dto.firestore.toModel
import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.login.LoginModel
import com.tanify.library.authentication.domain.model.register.RegisterModel
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.authentication.domain.payload.LoginPayload
import com.tanify.library.authentication.domain.payload.RegisterPayload
import com.tanify.library.authentication.domain.usecase.reset_password.ResetPasswordParam
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : AuthDataSource {

    private val userInfoFlow: MutableSharedFlow<UserDataModel> = MutableSharedFlow(replay = 1)
    private var userInfoListenerRegistration: ListenerRegistration? = null

    override fun loginWithEmailPass(payload: LoginPayload): Flow<ResultModel<LoginModel>> {
        return flow {
            val result = try {
                val signInResult = auth.signInWithEmailAndPassword(
                    payload.email,
                    payload.password
                ).await()
                val userId = signInResult.user!!.uid
                val result = LoginModel(email = payload.email, userId = userId)
                ResultModel.Success(result)
            } catch (t: Throwable) {
                t.printStackTrace()
                ResultModel.Error(t)
            }
            emit(result)
        }
    }

    override fun registerWithEmailPassword(payload: RegisterPayload): Flow<ResultModel<RegisterModel>> {
        return flow {
            val result = try {
                val createAccountResult =
                    auth.createUserWithEmailAndPassword(payload.email, payload.password)
                        .await()
                val userId = createAccountResult.user!!.uid
                val firebaseUser = FireStoreUserModel(
                    email = payload.email,
                    name = payload.fullName,
                    createdAt = Timestamp.now()
                )
                db.runBatch {
                    it.set(db.collection("user").document(userId), firebaseUser)
                }.await()
                ResultModel.Success(
                    RegisterModel(
                        email = payload.email,
                        fullName = payload.fullName,
                        id = userId
                    )
                )
            } catch (t: Throwable) {
                t.printStackTrace()
                ResultModel.Error(t)
            }
            emit(result)
        }
    }

    override fun resetPassword(param: ResetPasswordParam): Flow<ResultModel<Boolean>> =
        flow {
            val result = try {
                auth.sendPasswordResetEmail(param.email).await()
                ResultModel.Success(true)
            } catch (t: Throwable) {
                t.printStackTrace()
                ResultModel.Error(t)
            }
            emit(result)
        }

    override fun getUserInfo(): Flow<ResultModel<UserDataModel>> =
        flow {
            if (userInfoListenerRegistration == null) subscribeUserInfo()
            emitAll(userInfoFlow.map { ResultModel.Success(it) })
        }

    override fun subscribeUserInfo() {
        userInfoListenerRegistration?.remove()
        userInfoListenerRegistration = db
            .collection("user")
            .document(auth.uid!!)
            .addSnapshotListener { data, error ->
                if (error != null) {
                    error.printStackTrace()
                    return@addSnapshotListener
                }
                data ?: return@addSnapshotListener
                val firebaseDoc = data.toObject(FireStoreUserModel::class.java)
                firebaseDoc?.let {
                    userInfoFlow.tryEmit(firebaseDoc.toModel())
                }
            }
    }
}