package com.midterm.securevpnproxy.presentation.register

import androidx.lifecycle.ViewModel
import com.midterm.securevpnproxy.domain.use_case.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject
constructor(private val registerUseCase: RegisterUseCase): ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun register(fullName: String,email: String, password: String) {

        uiScope.launch {
            registerUseCase(fullName,email,password)
        }
    }

}