package com.midterm.securevpnproxy.domain.usecase.register

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource): RegisterUseCase {
    override suspend fun invoke(param: RegisterParam) {
        dataSource.register(param)
    }
}