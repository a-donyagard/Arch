package com.arash.arch.data.source.db

import androidx.room.ColumnInfo
import com.arash.arch.data.model.anime.ImageLinks

data class ImageLinksDb(
    @ColumnInfo(name = "tiny")
    val tiny: String,
    @ColumnInfo(name = "large")
    val large: String,
    @ColumnInfo(name = "small")
    val small: String,
    @ColumnInfo(name = "medium")
    val medium: String?,
    @ColumnInfo(name = "original")
    val original: String
)

fun ImageLinks.toImageLinksDb(): ImageLinksDb = ImageLinksDb(tiny, large, small, medium, original)
