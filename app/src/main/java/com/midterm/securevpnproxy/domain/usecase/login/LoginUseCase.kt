package com.midterm.securevpnproxy.domain.usecase.login

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface LoginUseCase {
    operator fun invoke(param: LoginParam): Flow<ResultModel<LoginModel>>

    fun getCurrentUser(): Flow<LoginModel>
}