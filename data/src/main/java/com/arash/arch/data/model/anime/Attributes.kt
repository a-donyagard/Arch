package com.arash.arch.data.model.anime

import com.google.gson.annotations.SerializedName
import java.util.*

data class Attributes(
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("synopsis")
    val synopsis: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("canonicalTitle")
    val title: String,
    @SerializedName("averageRating")
    val averageRating: String,
    @SerializedName("userCount")
    val userCount: Int,
    @SerializedName("favoritesCount")
    val favoritesCount: Int,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("endDate")
    val endDate: String,
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
    val posterImage: ImageLinks,
    @SerializedName("coverImage")
    val coverImage: ImageLinks?,
    @SerializedName("episodeCount")
    val episodeCount: Int,
    @SerializedName("episodeLength")
    val episodeLength: Int,
    @SerializedName("totalLength")
    val totalLength: Int,
    @SerializedName("showType")
    val showType: String
)
