//package com.tanify.library.authentication.data.repository
//
//import com.google.firebase.Timestamp
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.firestore.ktx.firestore
//import com.google.firebase.ktx.Firebase
//import com.tanify.library.authentication.data.dto.firestore.FireStoreUserModel
//import com.tanify.library.authentication.data.repository.network.AppApiService
//import com.tanify.library.authentication.data.repository.network.body.LoginBody
//import com.tanify.library.authentication.domain.datasource.AuthDataSource
//import com.tanify.library.authentication.domain.model.login.LoginModel
//import com.tanify.library.authentication.domain.model.register.RegisterModel
//import com.tanify.library.libcore.usecase.ResultModel
//import com.tanify.library.authentication.domain.payload.LoginPayload
//import com.tanify.library.authentication.domain.payload.RegisterPayload
//import com.tanify.library.authentication.domain.usecase.reset_password.ResetPasswordParam
//import javax.inject.Inject
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import kotlinx.coroutines.tasks.await
//
//class AuthRepository @Inject constructor(
//    private val appApiService: AppApiService,
//) : AuthDataSource {
//
//    override fun loginWithEmailPass(payload: LoginPayload): Flow<ResultModel<LoginModel>> {
//        return flow {
//            val result = try {
//                val signInResult = Firebase.auth.signInWithEmailAndPassword(payload.email, payload.password).await()
//                val userId = signInResult.user!!.uid
//                val body = LoginBody(email = payload.email, password = payload.password)
//                val result = appApiService.login(body)
//                // success
//                val model = result.toModel()
//                // save user info (userData + login State)
//                ResultModel.Success(model)
//            } catch (t: Throwable) {
//                t.printStackTrace()
//                ResultModel.Error(t)
//            }
//            emit(result)
//        }
//    }
//
//    private fun get
//
//    override fun registerWithEmailPassword(payload: RegisterPayload): Flow<ResultModel<RegisterModel>> {
//        return flow {
//            val result = try {
//                val createAccountResult =
//                    Firebase.auth.createUserWithEmailAndPassword(payload.email, payload.password)
//                        .await()
//                val userId = createAccountResult.user!!.uid
//                val firebaseUser = FireStoreUserModel(
//                    email = payload.email,
//                    name = payload.fullName,
//                    createdAt = Timestamp.now()
//                )
//                Firebase.firestore.runBatch {
//                    it.set(Firebase.firestore.collection("user").document(userId), firebaseUser)
//                }.await()
//                ResultModel.Success(
//                    RegisterModel(
//                        email = payload.email,
//                        fullName = payload.fullName,
//                        id = userId
//                    )
//                )
//            } catch (t: Throwable) {
//                t.printStackTrace()
//                ResultModel.Error(t)
//            }
//            emit(result)
//        }
//    }
//
//    override suspend fun resetPassword(param: ResetPasswordParam) {
//        Firebase.auth.sendPasswordResetEmail(param.email)
//    }
//
//}