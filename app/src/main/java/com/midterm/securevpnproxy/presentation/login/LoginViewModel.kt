package com.midterm.securevpnproxy.presentation.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.login.LoginUseCaseImpl
import com.midterm.securevpnproxy.presentation.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCaseImpl) : ViewModel() {

    val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState())
    val isExistUser: MutableLiveData<Boolean> = MutableLiveData()

    private fun login(email: String, password: String) {
        val validateLogin = validateLogin(email,password)
        if(!validateLogin) {
            return
        }
        else {
            viewModelScope.launch {
                val result = loginUseCase(LoginParam(email, password))
                if (result is ResultModel.Success) {
                    isExistUser.value = true
                } else if (result is ResultModel.Error) {
                    isExistUser.value = false
                }
            }
        }
    }

   fun onEvent(event: ViewEvent) {
       when(event) {
           is ViewEvent.LoginEvent -> login(event.email,event.password)
       }
   }

    private fun validateLogin(email: String, password:String): Boolean
    {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.postValue(viewState.value?.copy(
                emailError = "Invalid email"
            ))
            return false
        }
        if(password.trim().length < 8) {
            viewState.postValue(viewState.value?.copy(
                passwordError = "Password must be longer than 8"
            ))
            return false
        }
        return true
    }

    data class ViewState(
        val emailError: String? = null,
        val passwordError: String? = null
    )

}