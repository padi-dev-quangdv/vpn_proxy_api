package com.midterm.securevpnproxy.presentation.login_register.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState())
    val isUserExist: MutableLiveData<Boolean> = MutableLiveData()

    private var job: Job? = null

    private fun login(email: String, password: String) {
        val validateLogin = validateLogin(email, password)
        if (!validateLogin) return
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.Main) {
            loginUseCase.invoke(LoginParam(email, password)).collectLatest { result ->
                when (result) {
                    is ResultModel.Success -> {
                        isUserExist.value = true
                    }
                    is ResultModel.Error -> {
                        isUserExist.value = false
                    }
                }
            }
        }
    }

    fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.LoginEvent -> login(event.email, event.password)
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.postValue(
                viewState.value?.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        if(password.trim().isEmpty()) {
            viewState.postValue(
                viewState.value?.copy(
                    passwordError = "Password cannot be empty"
                )
            )
        }
        if (password.trim().length < 8) {
            viewState.postValue(
                viewState.value?.copy(
                    passwordError = "Password must be longer than 8"
                )
            )
            return false
        }
        return true
    }

    data class ViewState(
        val emailError: String? = null,
        val passwordError: String? = null
    )

    sealed interface ViewEvent {
        data class LoginEvent(
            val email: String,
            val password: String
        ): ViewEvent
    }

}