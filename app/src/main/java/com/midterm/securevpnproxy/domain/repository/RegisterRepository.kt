package com.midterm.securevpnproxy.domain.repository

interface RegisterRepository {

    suspend fun register(fullName: String,email: String, password: String)
}