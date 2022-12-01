package com.arash.arch.util.providers

import android.util.Log
import com.arash.arch.R
import com.arash.arch.domain.base.HttpError
import com.arash.arch.domain.base.Error
import javax.inject.Inject

/**
 * Used to map given [Error] to related persian message
 */
class ErrorMessageProvider @Inject constructor(private val resourceProvider: ResourceProvider) {

    fun getErrorMessage(error: Error): String {
        when (error) {
            is HttpError.PageNotFound -> Log.d(TAG, "getErrorMessage: Page Not Found")
            is HttpError.BadRequest -> Log.d(TAG, "getErrorMessage: Bad Request")
            is HttpError.ServerBroken -> Log.d(TAG, "getErrorMessage: Server Broken")
            is HttpError.ConnectionFailed -> Log.d(TAG, "getErrorMessage: Connection Failed")
            is HttpError.TimeOut -> Log.d(TAG, "getErrorMessage: Time Out")
            is HttpError.UnAuthorized -> Log.d(TAG, "getErrorMessage: Un Authorized")
            else -> Log.d(TAG, "getErrorMessage: Unknown Error")
        }
        return when (error) {
            is Error.EmptyResponse -> resourceProvider.getString(R.string.empty_response_error)
            is Error.NotDefined -> {
                Log.d(TAG, "getErrorMessage: ${error.throwable}")
                resourceProvider.getString(R.string.not_defined_error)
            }
            is Error.Null -> resourceProvider.getString(R.string.fail)
            is HttpError.InvalidResponse -> error.message
                ?: resourceProvider.getString(R.string.fail)
            is HttpError.UnAuthorized -> resourceProvider.getString(R.string.you_are_not_logged_in)
            else -> resourceProvider.getString(R.string.network_error)
        }
    }

    companion object {
        private const val TAG = "ErrorMessageProvider"
    }
}