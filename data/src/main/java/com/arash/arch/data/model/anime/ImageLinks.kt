package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.ImageLinksDb
import com.google.gson.annotations.SerializedName

data class ImageLinks(
    @SerializedName("tiny")
    val tiny: String,
    @SerializedName("large")
    val large: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("medium")
    val medium: String?,
    @SerializedName("original")
    val original: String
)

fun ImageLinksDb.toImageLinks(): ImageLinks = ImageLinks(tiny, large, small, medium, original)
