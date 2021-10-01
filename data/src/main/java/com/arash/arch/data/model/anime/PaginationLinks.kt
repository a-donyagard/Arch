package com.arash.arch.data.model.anime

import com.google.gson.annotations.SerializedName

data class PaginationLinks(
    @SerializedName("first")
    val first: String?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?,
    @SerializedName("last")
    val last: String?
)
