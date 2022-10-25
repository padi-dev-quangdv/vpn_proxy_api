package com.midterm.securevpnproxy.domain.usecase.check_login

import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import javax.inject.Inject

class CheckLoginUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) :
    CheckLoginUseCase {
    override fun checkLogin() {
        return dataSource.checkLogin()
    }

    override fun checkLogout() {
        return dataSource.checkLogout()
    }

    override fun isLogin(): Boolean {
        return dataSource.isLogin()
    }
}