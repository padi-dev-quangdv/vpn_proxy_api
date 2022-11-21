package com.midterm.securevpnproxy.domain.usecase.getUserData

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDataUseCaseImpl @Inject constructor(
    private val authDataSource: AuthDataSource
): GetUserDataUseCase {
    override fun invoke(param: LoginParam): Flow<ResultModel<LoginModel>> {
        return authDataSource.getCurrentUser()
    }
}