package com.midterm.securevpnproxy.domain.usecase.login

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) : LoginUseCase {

    override fun invoke(param: LoginParam): Flow<ResultModel<LoginModel>> {
        return dataSource.login(param)
    }
}