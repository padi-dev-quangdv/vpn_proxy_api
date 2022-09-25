package com.midterm.securevpnproxy.domain.usecase.reset_password

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import javax.inject.Inject

class ResetPasswordUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) :
    ResetPasswordUseCase {
    override suspend fun invoke(email: String) {
        dataSource.resetEmail(email)
    }
}