package com.tanify.library.networking.utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tanify.library.libcore.failure.Failure
import com.tanify.library.libcore.usecase.ResultModel
import kotlin.reflect.KClass
import retrofit2.HttpException
import timber.log.Timber
import java.net.HttpURLConnection.HTTP_BAD_REQUEST

suspend inline fun <DTO, DM, ERROR : Any> safeCallApi(
    crossinline apiCall: suspend () -> DTO,
    transformer: (DTO) -> DM,
    selfHandleStatusCode: List<Int> = listOf(HTTP_BAD_REQUEST),
    errorClass: KClass<ERROR>,
    errorMapper: (ERROR) -> Failure,
    noinline onCallDone: suspend (DTO) -> Unit = {},
): ResultModel<DM> {
    return try {
        val dto = apiCall()
        onCallDone(dto)
        val model = transformer(dto)
        ResultModel.Success(model)
    } catch (throwable: Throwable) {
        Timber.e(throwable)
        //Check if the callback is not null and the statusCode should be handle manual
        if (throwable is HttpException && throwable.code() in selfHandleStatusCode) {
            val errorString = throwable.response()?.errorBody()?.string() ?: ""

            val errorModel = try {
                moshi.adapter(errorClass.java).fromJson(errorString)
            } catch (exception: Throwable) {
                exception.printStackTrace()
                null
            }

            errorModel ?: return ResultModel.Error(Failure.Unknown)
            val failure = errorMapper(errorModel)
            return ResultModel.Error(failure)
        }

        val error = ErrorHandlerImpl.fromThrowable(throwable) ?: Failure.Unknown
        ResultModel.Error(error)
    }
}

val moshi: Moshi by lazy { Moshi.Builder().add(KotlinJsonAdapterFactory()).build() }