package com.midterm.securevpnproxy.base.compose

import com.midterm.securevpnproxy.base.BaseViewEffect
import kotlinx.coroutines.flow.Flow

interface HandleEffect<E : BaseViewEffect> {

    val defaultEffectValue: E?
        get() = null

    val provideEffectFlow: Flow<E>

    fun onEffectTriggered(effect: E?)

}