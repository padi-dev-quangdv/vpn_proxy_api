package com.midterm.securevpnproxy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.math.roundToInt

class HomeViewModel: ViewModel() {
    val timer: MutableLiveData<Timer> = MutableLiveData(Timer())


}