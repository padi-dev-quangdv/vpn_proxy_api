package com.midterm.securevpnproxy.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.midterm.securevpnproxy.data.dto.LoginDto
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class AuthRepository @Inject constructor() : AuthDataSource {
    override fun login(param: LoginParam): Flow<ResultModel<LoginModel>> {
        return callbackFlow {
            Firebase.auth.signInWithEmailAndPassword(param.email, param.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val firebaseUser = task.result.user
                        if (firebaseUser != null) {
                            val loginDto = LoginDto(
                                id = firebaseUser.uid,
                                user_email = firebaseUser.email ?: ""
                            )
                            val model = loginDto.toLoginModel()
                            trySend(ResultModel.Success(model))
                        } else {
                            val result =
                                ResultModel.Error(Exception())
                            trySend(result)
                        }
                    } else {
                        // err
                        task.exception?.let {
                            val result = ResultModel.Error(it)
                            trySend(result)
                        }
                    }
                }
        }
    }

    override suspend fun register(param: RegisterParam) {
        Firebase.auth.createUserWithEmailAndPassword(param.email, param.password)
    }

    override suspend fun resetEmail(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
    }

}