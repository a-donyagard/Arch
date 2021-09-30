package com.arash.arch.data.model.wrapper

import com.google.gson.annotations.SerializedName

data class PaginationResponse<T>(
    @SerializedName("next")
    val nextPage: String?,
    @SerializedName("results")
    val results: List<T>
)