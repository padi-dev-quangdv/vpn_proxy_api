package com.midterm.securevpnproxy.data.dto

import com.midterm.securevpnproxy.domain.model.LoginModel

class LoginDto(val user_email: String, val id: String) {
    fun toLoginModel(): LoginModel {
        return LoginModel(id = id, email = user_email)
    }
}