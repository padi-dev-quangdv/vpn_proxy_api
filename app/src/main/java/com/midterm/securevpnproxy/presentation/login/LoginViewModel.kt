package com.midterm.securevpnproxy.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase(LoginParam(email, password))
            if (result is ResultModel.Success) {
                ///
                val email = result.result.email
            } else if (result is ResultModel.Error) {
                // show loi cho ng dung
            }
        }
    }

}