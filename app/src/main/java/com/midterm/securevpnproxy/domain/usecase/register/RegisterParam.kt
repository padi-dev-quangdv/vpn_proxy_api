package com.midterm.securevpnproxy.domain.usecase.register

data class RegisterParam(
    val email: String,
    val fullName: String,
    val password: String,
)
