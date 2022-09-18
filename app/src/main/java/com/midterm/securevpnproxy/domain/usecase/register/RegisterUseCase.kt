package com.midterm.securevpnproxy.domain.usecase.register

interface RegisterUseCase {
    suspend operator fun invoke(param: RegisterParam)
}