package com.midterm.securevpnproxy.domain.datasource

import com.midterm.securevpnproxy.domain.payload.LoginPayload
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.RegisterModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.payload.RegisterPayload
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordParam
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun isLogin(): Boolean

    fun login(payload: LoginPayload): Flow<ResultModel<LoginModel>>

    fun getCurrentUser(): Flow<ResultModel<LoginModel>>

    fun register(payload: RegisterPayload): Flow<ResultModel<RegisterModel>>

    suspend fun resetPassword(param: ResetPasswordParam)
}