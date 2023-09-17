package com.tanify.library.networking.utils

import com.tanify.library.libcore.failure.Failure
interface ErrorHandler {

    fun fromThrowable(throwable: Throwable): Failure?
}
