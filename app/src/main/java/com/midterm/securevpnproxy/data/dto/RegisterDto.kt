package com.midterm.securevpnproxy.data.dto

import com.midterm.securevpnproxy.domain.model.RegisterModel

class RegisterDto (
    val email: String,
    val id: String
        ) {
    fun toRegisterModel(): RegisterModel {
        return RegisterModel(id = id, email = email)
    }
}