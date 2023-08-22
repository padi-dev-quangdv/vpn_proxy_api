package com.tanify.library.authentication.domain.usecase.login

import com.tanify.library.authentication.domain.datasource.AuthDataSource
import com.tanify.library.authentication.domain.datasource.AuthManager
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.login.LoginModel
import com.tanify.library.authentication.domain.model.user_info.UserDataModel
import com.tanify.library.authentication.domain.payload.LoginPayload
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class LoginUseCaseImpl @Inject constructor(
    private val dataSource: AuthDataSource,
) : LoginUseCase {
    override fun execute(param: LoginParam): Flow<ResultModel<Unit>> {
        val payload = LoginPayload(email = param.email, password = param.password)
        return dataSource.loginWithEmailPass(payload).map {
            when (it) {
                is ResultModel.Success -> {
                    ResultModel.Success(Unit)
                }

                is ResultModel.Error -> {
                    ResultModel.Error(it.t)
                }

                else -> {
                    ResultModel.Error(t = UnknownError())
                }
            }
        }
    }
}
