package com.midterm.securevpnproxy.presentation.auth.login

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.domain.model.LoginModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.check_login.CheckLoginParam
import com.midterm.securevpnproxy.domain.usecase.check_login.CheckLoginUseCase
import com.midterm.securevpnproxy.domain.usecase.login.LoginParam
import com.midterm.securevpnproxy.domain.usecase.login.LoginUseCase
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewEffect
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkLoginUseCase: CheckLoginUseCase
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {

    private var loginJob: Job? = null

    private fun login(email: String, password: String) {
        val validateLogin = validateLogin(email, password)
        if (!validateLogin) return
        loginJob?.cancel()
        loginJob = coroutineScope.launch {
            when (val result = loginUseCase.invoke(LoginParam(email, password)).firstOrNull()) {
                is ResultModel.Success -> {
                    setEffect(ViewEffect.LoginSuccess)
                }
                is ResultModel.Error -> {
                    setEffect(
                        ViewEffect.Error(
                            message = result.t.localizedMessage ?: "Unknown error"
                        )
                    )
                }
            }
        }
    }

    private fun checkLogin() {
        val loggedIn = checkLoginUseCase(CheckLoginParam())
        setState(currentState.copy(loggedIn = loggedIn))
    }


    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.LoginEvent -> login(event.email, event.password)
            is ViewEvent.CheckLogin -> checkLogin()
        }
    }


    private fun validateLogin(email: String, password: String): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setState(
                currentState.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        if (password.trim().isEmpty()) {
            setState(
                currentState.copy(
                    passwordError = "Password cannot be empty"
                )
            )
        }
        if (password.trim().length < 8) {
            setState(
                currentState.copy(
                    passwordError = "Password must be longer than 8"
                )
            )
            return false
        }
        return true
    }

    data class ViewState(
        val loggedIn: Boolean = false,
        val emailError: String? = null,
        val passwordError: String? = null
    ) : BaseViewState

    sealed interface ViewEvent : BaseViewEvent {
        data class LoginEvent(
            val email: String,
            val password: String
        ) : ViewEvent

        object CheckLogin: ViewEvent
    }

    sealed interface ViewEffect : BaseViewEffect {
        data class Error(val message: String) : ViewEffect

        object LoginSuccess : ViewEffect
    }

}