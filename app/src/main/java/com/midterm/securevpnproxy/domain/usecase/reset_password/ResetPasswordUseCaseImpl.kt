package com.midterm.securevpnproxy.domain.usecase.reset_password

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) :
    ResetPasswordUseCase {
    override suspend fun invoke(param: ResetPasswordParam) {
        dataSource.resetPassword(param)
    }

}