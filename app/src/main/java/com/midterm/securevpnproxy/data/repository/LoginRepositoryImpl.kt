package com.midterm.securevpnproxy.data.repository

import com.midterm.securevpnproxy.data.data_source.LoginDataSource
import com.midterm.securevpnproxy.domain.repository.LoginRepository

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource): LoginRepository {
    override suspend fun login(email: String, password: String) {
        loginDataSource.login(email,password)
    }
}