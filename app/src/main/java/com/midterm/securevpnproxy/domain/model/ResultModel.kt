package com.midterm.securevpnproxy.domain.model

interface ResultModel<out T> {
    data class Success<T>(val result: T) : ResultModel<T>
    data class Error(val t: Throwable) : ResultModel<Nothing>

}