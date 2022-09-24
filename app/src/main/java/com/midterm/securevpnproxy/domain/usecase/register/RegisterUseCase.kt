package com.midterm.securevpnproxy.domain.usecase.register

import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel

interface RegisterUseCase {
    suspend operator fun invoke(param: RegisterParam)
}