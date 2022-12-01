package com.arash.arch.data.model.anime

import com.arash.arch.data.model.EntityModel
import com.arash.arch.data.source.db.ImageLinksDb
import com.arash.arch.domain.model.ImageLinks
import com.google.gson.annotations.SerializedName

data class ImageLinksDto(
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
):EntityModel<ImageLinks> {
    override fun toDomain(): ImageLinks {
        return ImageLinks(
            tiny,
            large,
            small,
            medium,
            original
        )
    }
}

fun ImageLinksDb.toImageLinks(): ImageLinks = ImageLinks(tiny, large, small, medium, original)
