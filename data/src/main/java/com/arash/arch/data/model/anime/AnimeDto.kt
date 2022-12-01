package com.arash.arch.data.model.anime

import com.arash.arch.data.model.EntityModel
import com.arash.arch.data.model.PaginationLinksDto
import com.arash.arch.data.source.db.AnimeEntity
import com.arash.arch.domain.model.Anime
import com.arash.arch.domain.model.Attributes
import com.arash.arch.domain.model.ResponseWrapper
import com.google.gson.annotations.SerializedName

data class AnimeDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("attributes")
    val attributes: AttributesDto
) : EntityModel<Anime> {
    override fun toDomain(): Anime {
        return Anime(
            id,
            attributes.toDomain()
        )
    }
}

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
            posterImage.toImageLinks(),
            coverImage?.toImageLinks(),
            episodeCount,
            episodeLength,
            totalLength,
            showType,
            createdAt
        )
    )
}

fun List<AnimeEntity>.toResponseWrapper(paginationLinks: PaginationLinksDto): ResponseWrapper<Anime> {
    return ResponseWrapper(
        map {
            it.toAnime()
        },
        paginationLinks.toDomain()
    )
}
