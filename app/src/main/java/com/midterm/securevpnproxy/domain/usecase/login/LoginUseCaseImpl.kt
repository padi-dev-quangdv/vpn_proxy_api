package com.midterm.securevpnproxy.domain.usecase.login

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.ResultModel
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) : LoginUseCase {

    override suspend fun invoke(param: LoginParam): ResultModel<LoginModel> {
        return dataSource.login(param)
    }
}