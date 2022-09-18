package com.midterm.securevpnproxy.domain.usecase.register

import android.util.Log
import com.midterm.securevpnproxy.domain.model.InvalidNoteException
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import javax.inject.Inject

class RegisterUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource): RegisterUseCase {
    override suspend fun invoke(param: RegisterParam) {
        if(param.fullName.isBlank()) {
            throw InvalidNoteException("The full name can't be empty")
        }
        if(param.email.isBlank()) {
            throw InvalidNoteException("The email can't be empty")
        }
        if(param.password.isBlank()) {
            throw InvalidNoteException("The password can't be empty")
        }
        Log.d("ViewModel","inside use case")
        dataSource.register(param)
    }
}