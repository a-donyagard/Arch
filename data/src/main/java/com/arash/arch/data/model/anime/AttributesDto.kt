package com.arash.arch.data.model.anime

import com.arash.arch.data.model.EntityModel
import com.arash.arch.domain.model.Attributes
import com.google.gson.annotations.SerializedName

data class AttributesDto(
    @SerializedName("canonicalTitle")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("averageRating")
    val averageRating: String,
    @SerializedName("userCount")
    val userCount: Int,
    @SerializedName("favoritesCount")
    val favoritesCount: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String?,
    @SerializedName("nextRelease")
    val nextRelease: String?,
    @SerializedName("popularityRank")
    val popularityRank: Int,
    @SerializedName("ratingRank")
    val ratingRank: Int,
    @SerializedName("ageRating")
    val ageRating: String,
    @SerializedName("ageRatingGuide")
    val ageRatingGuide: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("posterImage")
    val posterImage: ImageLinksDto,
    @SerializedName("coverImage")
    val coverImage: ImageLinksDto?,
    @SerializedName("episodeCount")
    val episodeCount: Int,
    @SerializedName("episodeLength")
    val episodeLength: Int,
    @SerializedName("totalLength")
    val totalLength: Int,
    @SerializedName("showType")
    val showType: String,
    @SerializedName("createdAt")
    val createdAt: String
) : EntityModel<Attributes> {
    override fun toDomain(): Attributes {
        return Attributes(
            title,
            description,
            synopsis,
            averageRating,
            userCount,
            favoritesCount,
            startDate,
            endDate,
            nextRelease,
            popularityRank,
            ratingRank,
            ageRating,
            ageRatingGuide,
            status,
            posterImage.toDomain(),
            coverImage?.toDomain(),
            episodeCount,
            episodeLength,
            totalLength,
            showType,
            createdAt
        )
    }
}
