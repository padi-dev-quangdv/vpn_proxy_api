package com.midterm.securevpnproxy.domain.usecase.login

import android.util.Log
import com.midterm.securevpnproxy.data.dto.LoginDto
import com.midterm.securevpnproxy.domain.model.InvalidNoteException
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.datasource.AuthDataSource
import com.midterm.securevpnproxy.domain.model.ResultModel
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(private val dataSource: AuthDataSource) : LoginUseCase {

    override suspend fun invoke(param: LoginParam): ResultModel<LoginDto> {
        if (param.email.isBlank()) {
            throw InvalidNoteException("The email can't be empty")
        }
        if (param.password.isBlank()) {
            throw InvalidNoteException("The password can't be empty")
        }
        Log.d("ViewModel", "inside use case")
        return dataSource.login(param)
    }
}