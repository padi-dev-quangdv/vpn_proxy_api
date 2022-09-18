package com.midterm.securevpnproxy.domain.datasource

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam

interface AuthDataSource {
    suspend fun login(param: LoginParam): ResultModel<LoginModel>

    suspend fun register(param: RegisterParam)
}