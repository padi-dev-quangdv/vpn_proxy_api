package com.midterm.securevpnproxy.domain.usecase.getUserData

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import kotlinx.coroutines.flow.Flow


interface GetUserDataUseCase {
    operator fun invoke(param: LoginParam): Flow<ResultModel<LoginModel>>
}