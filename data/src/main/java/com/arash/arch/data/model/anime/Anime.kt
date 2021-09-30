package com.arash.arch.data.model.anime

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: Attributes
)
