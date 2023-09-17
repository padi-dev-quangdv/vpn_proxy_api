package com.tanify.library.libcore.failure

sealed class Failure : Throwable() {
    data object Unknown : Failure()

    sealed class NetworkError : Failure() {
        data object NotFound : NetworkError()
        data object NoConnection : NetworkError()
        data object AccessDenied : NetworkError()
        data object ServiceUnavailable : NetworkError()
    }


    open class FeatureError(
        open val error: String? = null,
        open val throwable: Throwable? = null,
    ) : Failure()
}