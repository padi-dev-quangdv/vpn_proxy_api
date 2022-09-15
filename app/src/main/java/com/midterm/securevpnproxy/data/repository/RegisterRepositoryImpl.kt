package com.midterm.securevpnproxy.data.repository

import com.midterm.securevpnproxy.data.data_source.RegisterDataSource
import com.midterm.securevpnproxy.domain.model.User
import com.midterm.securevpnproxy.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl(private val dataSource: RegisterDataSource): RegisterRepository {

    override suspend fun register(fullName: String, email: String, password: String) {
        dataSource.register(email, password)
    }

}