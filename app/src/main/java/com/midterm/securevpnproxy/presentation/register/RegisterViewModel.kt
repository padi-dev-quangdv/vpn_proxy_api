package com.midterm.securevpnproxy.presentation.register

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.midterm.securevpnproxy.domain.usecase.register.RegisterUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject
constructor(private val registerUseCase: RegisterUseCaseImpl) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val viewState: MutableLiveData<ViewState> = MutableLiveData(ViewState())

    private fun register(fullName: String, email: String, password: String) {
        val validateResult = validateRegister(fullName, email, password)
        if (!validateResult) {
            return
        }

        uiScope.launch {
            val result = registerUseCase(fullName, email, password)
            //////
        }
    }

    private fun validateRegister(fullName: String, email: String, password: String): Boolean {
        if (fullName.trim().isEmpty()) {
            viewState.postValue(viewState.value?.copy(
                fullNameError = "Full name khong hop le"
            ))
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            viewState.postValue(viewState.value?.copy(
                emailError = "Email khong hop le"
            ))
            return false
        }
        if (password.trim().length < 8) {
            viewState.postValue(viewState.value?.copy(
                passwordError = "Password khong hop le"
            ))
            return false
        }
        return true
    }

    fun onEvent(event: ViewEvent) {
        when (event) {
            is ViewEvent.RegisterEvent -> register(event.fullName, event.email, event.password)
        }
    }

    data class ViewState (
        val fullNameError: String? = null,
        val emailError: String? = null,
        val passwordError: String? = null
    )

    sealed interface ViewEvent {
        data class RegisterEvent(
            val fullName: String,
            val email: String,
            val password: String,
        ) : ViewEvent
    }

    sealed interface ViewEffect {

    }
}