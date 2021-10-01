package com.arash.arch.data.source.db

import androidx.room.ColumnInfo
import com.arash.arch.data.model.anime.PaginationLinks

data class PaginationLinksDb(
    @ColumnInfo(name = "first")
    val first: String,
    @ColumnInfo(name = "next")
    val next: String?,
    @ColumnInfo(name = "prev")
    val prev: String?,
    @ColumnInfo(name = "last")
    val last: String
)

fun PaginationLinks.toPaginationLinksDb(): PaginationLinksDb =
    PaginationLinksDb(first, next, prev, last)
