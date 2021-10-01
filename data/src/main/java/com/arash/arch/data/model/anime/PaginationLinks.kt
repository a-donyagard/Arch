package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.PaginationLinksDb
import com.google.gson.annotations.SerializedName

data class PaginationLinks(
    @SerializedName("first")
    val first: String,
    @SerializedName("next")
    val next: String?,
    @SerializedName("prev")
    val prev: String?,
    @SerializedName("last")
    val last: String
)

fun PaginationLinksDb.toPaginationLinks(): PaginationLinks =
    PaginationLinks(first, next, prev, last)
