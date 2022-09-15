package com.midterm.securevpnproxy.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel@Inject
constructor(private val loginUseCase: LoginUseCase): ViewModel()  {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            loginUseCase(email,password)
        }
    }

}