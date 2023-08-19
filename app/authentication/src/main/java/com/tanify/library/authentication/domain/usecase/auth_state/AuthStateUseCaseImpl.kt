package com.tanify.library.authentication.domain.usecase.auth_state

import com.tanify.library.authentication.domain.datasource.AuthManager
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import com.tanify.library.libcore.usecase.ResultModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthStateUseCaseImpl @Inject constructor(
    private val authManager: AuthManager,
) : AuthStateUseCase {
    override fun execute(param: Any): Flow<ResultModel<AuthState>> {
        return authManager.getAuthState().map {
            ResultModel.Success(it)
        }
    }
}