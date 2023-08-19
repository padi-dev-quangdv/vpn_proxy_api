package com.tanify.library.authentication.domain.usecase.login

import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.authentication.domain.datasource.AuthManager
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.login.LoginModel
import com.tanify.library.authentication.domain.payload.LoginPayload
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class LoginUseCaseImpl @Inject constructor(
    private val dataSource: AuthDataSource,
    private val authManager: AuthManager,
) : LoginUseCase {
    override fun execute(param: LoginParam): Flow<ResultModel<LoginModel>> {
        val payload = LoginPayload(email = param.email, password = param.password)
        return dataSource.loginWithEmailPass(payload)
            .onEach {
                if (it is ResultModel.Success) {
                    val model = it.result
//                    authManager.setUserData(UserDataModel(email = model.email))
                }
            }
    }
}
