package com.arash.arch.data.model.anime

import com.google.gson.annotations.SerializedName

data class KitsoResponse(
    @SerializedName("data")
    val data: List<Anime>,
    @SerializedName("links")
    val links: PaginationLinks
)
