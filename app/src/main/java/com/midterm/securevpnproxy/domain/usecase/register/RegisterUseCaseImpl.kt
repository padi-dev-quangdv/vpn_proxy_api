package com.midterm.securevpnproxy.domain.usecase.register

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.RegisterModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.payload.RegisterPayload
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) :
    RegisterUseCase {
    override fun invoke(param: RegisterParam): Flow<ResultModel<RegisterModel>> {
        val payload = RegisterPayload(
            fullName = param.fullName,
            email = param.email,
            password = param.password
        )
        return dataSource.register(payload)
    }
}