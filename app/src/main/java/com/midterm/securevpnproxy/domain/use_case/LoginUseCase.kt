package com.midterm.securevpnproxy.domain.use_case

import android.util.Log
import com.midterm.securevpnproxy.domain.model.InvalidNoteException
import com.midterm.securevpnproxy.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase  @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(email: String, password: String){
        if(email.isBlank()) {
            throw InvalidNoteException("The email can't be empty")
        }
        if(password.isBlank()) {
            throw InvalidNoteException("The password can't be empty")
        }
        Log.d("ViewModel","inside use case")
        repository.login(email,password)
    }
}