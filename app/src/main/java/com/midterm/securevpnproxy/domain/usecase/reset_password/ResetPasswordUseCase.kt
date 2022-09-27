package com.midterm.securevpnproxy.domain.usecase.reset_password



interface ResetPasswordUseCase {
    suspend operator fun invoke(param: ResetPasswordParam)
}