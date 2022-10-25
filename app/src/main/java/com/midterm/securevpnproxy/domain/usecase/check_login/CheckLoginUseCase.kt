package com.midterm.securevpnproxy.domain.usecase.check_login


interface CheckLoginUseCase {

    fun checkLogin()

    fun checkLogout()

    fun isLogin(): Boolean
}