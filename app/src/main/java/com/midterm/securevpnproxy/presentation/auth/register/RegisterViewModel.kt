package com.midterm.securevpnproxy.presentation.auth.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.midterm.securevpnproxy.base.BaseViewModel
import com.midterm.securevpnproxy.domain.model.ResultModel
import com.midterm.securevpnproxy.domain.usecase.register.RegisterParam
import com.midterm.securevpnproxy.domain.usecase.register.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject
constructor(private val registerUseCase: RegisterUseCase) : BaseViewModel<RegisterViewModel.ViewState,RegisterViewModel.ViewEvent>() {

    init {
        viewState = MutableLiveData(ViewState())
    }

    val isEmailAlreadyExist: MutableLiveData<Boolean> = MutableLiveData()

    private var job: Job? = null

    private fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val validateResult = validateRegister(fullName, email, password, confirmPassword)
        if (!validateResult) return
        job?.cancel()
        val param = RegisterParam(email = email, fullName = fullName, password = password)
        job = viewModelScope.launch(Dispatchers.IO) {
            registerUseCase(param).collectLatest { result ->
                when (result) {
                    is ResultModel.Success -> {
                        isEmailAlreadyExist.postValue(false)
                    }
                    is ResultModel.Error -> {
                        isEmailAlreadyExist.postValue(true)
                    }
                }
            }
        }
    }

    private fun validateRegister(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (fullName.trim().isEmpty()) {
            viewState.postValue(
                viewState.value?.copy(
                    fullNameError = "Full name cannot be empty"
                )
            )
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.postValue(
                viewState.value?.copy(
                    emailError = "Invalid email"
                )
            )
            return false
        }
        if (password.trim().isEmpty()) {
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
        if (confirmPassword.trim() != password.trim()) {
            viewState.postValue(
                viewState.value?.copy(
                    confirmPasswordError = "Confirm password is incorrect"
                )
            )
            return false
        }
        return true
    }

    override fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.RegisterEvent -> register(
                event.fullName,
                event.email,
                event.password,
                event.confirmPassword
            )
        }
    }

    data class ViewState(
        val fullNameError: String? = null,
        val emailError: String? = null,
        val passwordError: String? = null,
        val confirmPasswordError: String? = null
    ): BaseViewModel.ViewState()

    sealed interface ViewEvent : BaseViewModel.ViewEvent{
        data class RegisterEvent(
            val fullName: String,
            val email: String,
            val password: String,
            val confirmPassword: String
        ) : ViewEvent
    }


    sealed interface ViewEffect {

    }
}