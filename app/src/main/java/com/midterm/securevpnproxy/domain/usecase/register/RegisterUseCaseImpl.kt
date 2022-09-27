package com.midterm.securevpnproxy.domain.usecase.register

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource): RegisterUseCase {
    override fun invoke(param: RegisterParam): Flow<ResultModel<LoginModel>> {
        return dataSource.register(param)
    }
}