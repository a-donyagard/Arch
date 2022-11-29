package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.AnimeEntity
import com.google.gson.annotations.SerializedName

data class AnimeListDto(
    @SerializedName("data")
    val data: List<Anime>,
    @SerializedName("links")
    val links: PaginationLinks?
)

fun List<AnimeEntity>.toAnimeListWrapper(paginationLinks: PaginationLinks): AnimeListDto {
    return AnimeListDto(
        map {
            it.toAnime()
        },
        paginationLinks
    )
}