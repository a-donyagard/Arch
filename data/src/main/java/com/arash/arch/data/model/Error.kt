package com.arash.arch.data.model

import com.google.gson.annotations.SerializedName

sealed class Error {
    /**
     * an unexpected error
     */
    data class NotDefined(val throwable: Throwable) : Error()
    object Null : Error()
    object EmptyResponse : Error()
}

sealed class HttpError : Error() {

    object ConnectionFailed : HttpError()
    data class InvalidResponse(
        @SerializedName("detail")
        val message: String?
    ) : HttpError()

    object TimeOut : HttpError()
    object UnAuthorized : HttpError()
    object Forbidden : HttpError()
    object PageNotFound : HttpError()
    object ServerBroken : HttpError()
    object BadRequest : HttpError()
}
