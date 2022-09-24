package com.midterm.securevpnproxy.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam

class AuthRepository : AuthDataSource {
    override suspend fun login(param: LoginParam): ResultModel<LoginModel> {
        return try {
            val result: AuthResult =
                Firebase.auth.signInWithEmailAndPassword(param.email, param.password).result
            // Luu lai thong tin
            // Tra ve thong tin user
            // ResultModel.Success(LoginDto(user_email = "hieu", id = "123"))
            ResultModel.Success(LoginModel(result.user?.uid ?: "", result.user?.email ?: ""))
        } catch (t: Throwable) {
            t.printStackTrace()
            ResultModel.Error(t)
        }
    }

    override suspend fun register(param: RegisterParam) {
        Firebase.auth.createUserWithEmailAndPassword(param.email, param.password)
    }

    override suspend fun resetEmail(email: String) {
        Firebase.auth.sendPasswordResetEmail(email)
    }

}