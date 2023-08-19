package com.midterm.securevpnproxy.util.extensions

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun AppCompatActivity.repeatOnState(
    state: Lifecycle.State,
    crossinline block: suspend () -> Unit,
) = lifecycleScope.launch {
    lifecycle.repeatOnLifecycle(state) {
        block()
    }
}
fun <T> AppCompatActivity.observe(flow: Flow<T>, state: Lifecycle.State = Lifecycle.State.STARTED, observer: suspend (T) -> Unit) {
    repeatOnState(state) {
        flow.collectLatest {
            observer(it)
        }
    }
}
