package com.tanify.library.authentication.domain.usecase.register

import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.authentication.domain.datasource.AuthManager
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.register.RegisterModel
import com.tanify.library.authentication.domain.payload.RegisterPayload
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class RegisterUseCaseImpl @Inject constructor(
    private val dataSource: AuthDataSource,
    private val authManager: AuthManager,
) : RegisterUseCase {

    override fun execute(param: RegisterParam): Flow<ResultModel<RegisterModel>> {
        val payload = RegisterPayload(
            fullName = param.fullName,
            email = param.email,
            password = param.password
        )
        return dataSource.registerWithEmailPassword(payload)
            .onEach {
//                authManager.setUserData(UserDataModel(email = it.email, fullName = it.fullName))
//                authManager.setAccessToken(it.accessToken)
//                authManager.setRefreshToken(it.refreshToken)
            }
    }
}