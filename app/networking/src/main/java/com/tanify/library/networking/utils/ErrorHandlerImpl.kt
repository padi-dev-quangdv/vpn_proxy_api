package com.tanify.library.networking.utils

import com.tanify.library.libcore.failure.Failure
import com.tanify.library.libcore.failure.Failure.*
import okio.IOException
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.UnknownHostException

object ErrorHandlerImpl : ErrorHandler {

    override fun fromThrowable(throwable: Throwable): Failure? {
        return when (throwable) {
            is HttpException -> resolveNetworkException(throwable)
//            is IOException -> resolveIoException(throwable)
            else -> null
            //TODO: Handle more exception case in this function
        }
    }

    private fun resolveNetworkException(httpException: HttpException): Failure? {
        return when (httpException.code()) {
            HttpURLConnection.HTTP_NOT_FOUND -> NetworkError.NotFound
            HttpURLConnection.HTTP_FORBIDDEN -> NetworkError.AccessDenied
            HttpURLConnection.HTTP_UNAVAILABLE, HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkError.ServiceUnavailable
            else -> null
            //TODO: Handle more exception case in this function
        }
    }
}
