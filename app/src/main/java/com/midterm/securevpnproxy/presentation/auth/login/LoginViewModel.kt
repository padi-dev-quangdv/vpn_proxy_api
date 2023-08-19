package com.midterm.securevpnproxy.presentation.auth.login

import android.util.Patterns
import com.midterm.securevpnproxy.base.BaseViewEffect
import com.midterm.securevpnproxy.base.BaseViewEvent
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.base.BaseViewState
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewEffect
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewEvent
import com.midterm.securevpnproxy.presentation.auth.login.LoginViewModel.ViewState
import com.tanify.library.authentication.domain.datasource.AuthManager
import com.tanify.library.libcore.usecase.ResultModel
import com.tanify.library.authentication.domain.model.auth_state.AuthState
import com.tanify.library.authentication.domain.usecase.login.LoginParam
import com.tanify.library.authentication.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val authManager: AuthManager,
) : BaseViewModel<ViewState, ViewEvent, ViewEffect>(ViewState()) {

    private var loginJob: Job? = null

    private fun login(email: String, password: String) {
        val validateLogin = validateLogin(email, password)
        if (!validateLogin) return
        loginJob?.cancel()
        loginJob = loginUseCase.execute(LoginParam(email, password))
            .onEach {
                when (it) {
                    is ResultModel.Success -> {
                        setEffect(ViewEffect.LoginSuccess)
                    }

                    is ResultModel.Error -> {
                        setEffect(
                            ViewEffect.Error(
                                message = it.t.localizedMessage ?: "Unknown Error"
                            )
                        )
                    }
                }
            }.launchIn(coroutineScope)
    }

    private fun checkLogin() {
        coroutineScope.launch {
            authManager.getAuthState().collectLatest {
                setState(currentState.copy(loggedIn = it == AuthState.LOGGED_IN))
            }
        }
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

        object CheckLogin : ViewEvent
    }

    sealed interface ViewEffect : BaseViewEffect {
        data class Error(val message: String) : ViewEffect

        object LoginSuccess : ViewEffect
    }

}