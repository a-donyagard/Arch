package com.arash.arch.data.model.login

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("refresh")
    val refresh: String,
    @SerializedName("access")
    val access: String,
    @SerializedName("lifetime")
    val lifeTime: Int
)