package com.midterm.securevpnproxy.domain.usecase.check_login

import android.app.Application
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import javax.inject.Inject

class CheckLoginUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) :
    CheckLoginUseCase {
    override fun checkLogin(application: Application) {
        return dataSource.checkLogin(application)
    }

    override fun checkLogout(application: Application) {
        return dataSource.checkLogout(application)
    }

    override fun isLogin(application: Application): Boolean {
        return dataSource.isLogin(application)
    }
}