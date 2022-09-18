package com.midterm.securevpnproxy.domain.usecase.login

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel

interface LoginUseCase {
    suspend operator fun invoke(param: LoginParam): ResultModel<LoginModel>
}