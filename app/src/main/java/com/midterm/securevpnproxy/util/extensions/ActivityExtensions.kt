package com.midterm.securevpnproxy.util.extensions

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

inline fun AppCompatActivity.repeatOnState(
    state: Lifecycle.State,
    crossinline block: suspend () -> Unit,
) = lifecycleScope.launch {
    lifecycle.repeatOnLifecycle(state) {
        block()
    }
}