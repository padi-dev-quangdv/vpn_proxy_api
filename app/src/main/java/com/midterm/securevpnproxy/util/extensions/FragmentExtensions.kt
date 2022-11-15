package com.midterm.securevpnproxy.util.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

inline fun Fragment.repeatOnState(
    state: State,
    crossinline block: suspend () -> Unit,
) = viewLifecycleOwner.lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(state) {
        block()
    }
}

fun <T> Fragment.observe(flow: Flow<T>, state: State = State.STARTED, observer: suspend (T) -> Unit) {
    repeatOnState(state) {
        flow.collectLatest {
            observer(it)
        }
    }
}