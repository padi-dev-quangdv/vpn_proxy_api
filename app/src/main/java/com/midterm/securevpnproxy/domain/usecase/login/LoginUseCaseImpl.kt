package com.midterm.securevpnproxy.domain.usecase.login

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.payload.LoginPayload
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) : LoginUseCase {
    override fun invoke(param: LoginParam): Flow<ResultModel<LoginModel>> {
        val payload = LoginPayload(email = param.email, password = param.password)
        return dataSource.login(payload)
    }
}
