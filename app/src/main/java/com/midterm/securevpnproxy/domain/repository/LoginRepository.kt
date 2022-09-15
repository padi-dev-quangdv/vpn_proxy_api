package com.midterm.securevpnproxy.domain.repository

interface LoginRepository {

    suspend fun login(email: String, password: String)

}