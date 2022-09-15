package com.midterm.securevpnproxy.domain.use_case

import android.util.Log
import com.midterm.securevpnproxy.domain.model.InvalidNoteException
import com.midterm.securevpnproxy.domain.repository.RegisterRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: RegisterRepository) {

    suspend operator fun invoke(fullName: String,email: String, password: String){
        if(fullName.isBlank()) {
            throw InvalidNoteException("The full name can't be empty")
        }
        if(email.isBlank()) {
            throw InvalidNoteException("The email can't be empty")
        }
        if(password.isBlank()) {
            throw InvalidNoteException("The password can't be empty")
        }
        Log.d("ViewModel","inside use case")
        repository.register(fullName,email,password)
    }
}