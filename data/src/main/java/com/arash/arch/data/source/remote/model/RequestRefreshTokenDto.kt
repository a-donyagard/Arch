package com.arash.arch.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class RequestRefreshTokenDto(
    @SerializedName("refresh")
    val refreshToken: String
)