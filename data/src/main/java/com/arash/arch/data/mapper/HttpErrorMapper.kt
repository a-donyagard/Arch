package com.arash.arch.data.mapper

import com.google.gson.Gson
import com.arash.arch.data.model.HttpError
import com.arash.arch.data.model.Error
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class HttpErrorMapper @Inject constructor(private val gson: Gson) {

    fun mapToErrorModel(throwable: Throwable): Error? {
        return when (throwable) {
            is HttpException -> {
                getHttpError(throwable)
            }
            is SocketTimeoutException -> {
                HttpError.TimeOut
            }
            is IOException -> {
                HttpError.ConnectionFailed
            }
            else -> null
        }
    }

    private fun getHttpError(httpException: HttpException): Error {
        return when (httpException.code()) {
            400 -> {
                try {
                    val errorBody = httpException.response()?.errorBody()?.string()!!
                    if (errorBody.isEmpty()) {
                        throw Exception()
                    }
                    gson.fromJson(errorBody, HttpError.InvalidResponse::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                    HttpError.BadRequest
                }
            }
            401 -> HttpError.UnAuthorized
            403 -> HttpError.Forbidden
            404 -> HttpError.PageNotFound
            500 -> HttpError.ServerBroken
            else -> Error.NotDefined(httpException)
        }
    }
}
