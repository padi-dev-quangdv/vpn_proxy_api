package com.midterm.securevpnproxy.domain.usecase.check_login

import android.app.Application

interface CheckLoginUseCase {

    fun checkLogin(application: Application)

    fun checkLogout(application: Application)

    fun isLogin(application: Application): Boolean
}