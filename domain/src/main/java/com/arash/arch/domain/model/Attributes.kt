package com.arash.arch.domain.model

import com.arash.arch.domain.base.DomainModel

data class Attributes(
    val title: String,
    val description: String,
    val synopsis: String,
    val averageRating: String,
    val userCount: Int,
    val favoritesCount: Int,
    val startDate: String,
    val endDate: String?,
    val nextRelease: String?,
    val popularityRank: Int,
    val ratingRank: Int,
    val ageRating: String,
    val ageRatingGuide: String,
    val status: String,
    val posterImage: ImageLinks,
    val coverImage: ImageLinks?,
    val episodeCount: Int,
    val episodeLength: Int,
    val totalLength: Int,
    val showType: String,
    val createdAt: String
) : DomainModel