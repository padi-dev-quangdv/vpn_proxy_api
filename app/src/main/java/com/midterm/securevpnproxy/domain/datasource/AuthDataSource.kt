package com.midterm.securevpnproxy.domain.datasource

import android.app.Application
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.RegisterModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import com.midterm.securevpnproxy.domain.usecase.reset_password.ResetPasswordParam
import kotlinx.coroutines.flow.Flow

interface AuthDataSource {

    fun checkLogin(application: Application)

    fun checkLogout(application: Application)

    fun isLogin(application: Application): Boolean

    fun login(param: LoginParam): Flow<ResultModel<LoginModel>>

    fun getCurrentUser(): Flow<LoginModel>

    fun register(param: RegisterParam): Flow<ResultModel<RegisterModel>>

    suspend fun resetPassword(param: ResetPasswordParam)
}