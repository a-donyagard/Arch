package com.arash.arch.data.source.remote

import com.arash.arch.data.source.preference.AppPreferencesHelper
import com.arash.arch.data.source.remote.model.RequestRefreshTokenDto
import com.arash.arch.data.util.DataConstants
import dagger.Lazy
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

/**
 * A Helper class that attempts to refresh api token if server returns UNAUTHORIZED error
 * @param apis An instance of [APIs] to call [APIs.refreshToken] api
 * @param appPref An instance of [AppPreferencesHelper] to save refreshed access token, token type,
 * and newly refresh token into preference.
 */
class TokenAuthenticator(
    private val loginDataSource: Lazy<LoginDataSource>,
    private val appPref: Lazy<AppPreferencesHelper>
) : Authenticator {

    override fun authenticate(route: Route?, response: Response?): Request? {
        val requestRefresh = RequestRefreshTokenDto(appPref.get().getRefreshToken() ?: "")
        val refreshApi = loginDataSource.get().refreshToken(requestRefresh)
        val refreshResponse = refreshApi.execute()

        return if (refreshResponse.isSuccessful) {
            val body = refreshResponse.body()!!

            appPref.get().setToken(body.access)
            appPref.get().setRefreshToken(body.refresh)

            response
                ?.request()
                ?.newBuilder()
                ?.header(
                    "Authorization", DataConstants.tokenType + " " + body.access
                )?.build()
        } else {
            appPref.get().setToken("")
            appPref.get().setRefreshToken("")
            null
        }
    }
}