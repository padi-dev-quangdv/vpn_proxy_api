package com.midterm.securevpnproxy.domain.datasource

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {
    fun login(param: LoginParam): Flow<ResultModel<LoginModel>>

    suspend fun register(param: RegisterParam)

    suspend fun resetEmail(email: String)
}