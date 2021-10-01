package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.AnimeEntity
import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: Attributes
)

fun AnimeEntity.toAnime(): Anime {
    return Anime(
        id,
        Attributes(
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
            ImageLinks("", "", "", "", posterImage),
            coverImage?.let { ImageLinks("", "", "", "", it) },
            episodeCount,
            episodeLength,
            totalLength,
            showType,
            createdAt
        )
    )
}
