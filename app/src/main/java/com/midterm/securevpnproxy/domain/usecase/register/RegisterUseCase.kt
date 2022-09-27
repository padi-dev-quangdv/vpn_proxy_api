package com.midterm.securevpnproxy.domain.usecase.register

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {
    operator fun invoke(param: RegisterParam): Flow<ResultModel<LoginModel>>
}