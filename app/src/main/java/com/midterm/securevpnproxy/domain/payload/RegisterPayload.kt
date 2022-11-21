package com.midterm.securevpnproxy.domain.payload

data class RegisterPayload(
    val fullName: String,
    val email: String,
    val password: String,
)