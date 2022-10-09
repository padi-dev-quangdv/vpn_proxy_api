package com.midterm.securevpnproxy.presentation.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel: ViewModel() {
    val timer: MutableLiveData<Timer> = MutableLiveData(Timer())
    val timerTask: MutableLiveData<TimerTask> = MutableLiveData()
    val time: MutableLiveData<Double> = MutableLiveData(0.0)

     fun startTimer() {
         timerTask.value = object : TimerTask() {
             override fun run() {
                 viewModelScope.launch {
                     time.value?.inc()
                 }
             }

         }
     }

}