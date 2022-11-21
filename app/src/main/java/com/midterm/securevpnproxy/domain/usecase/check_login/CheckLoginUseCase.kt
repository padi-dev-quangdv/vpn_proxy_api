package com.midterm.securevpnproxy.domain.usecase.check_login


interface CheckLoginUseCase {
    operator fun invoke(param: CheckLoginParam): Boolean
}