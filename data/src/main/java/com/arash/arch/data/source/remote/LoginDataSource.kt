package com.arash.arch.data.source.remote

import com.arash.arch.data.model.login.Token
import com.arash.arch.data.source.remote.model.RequestRefreshTokenDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginDataSource {
    @POST("accounts/token/refresh/")
    fun refreshToken(@Body requestRefreshTokenDto: RequestRefreshTokenDto): Call<Token>
}