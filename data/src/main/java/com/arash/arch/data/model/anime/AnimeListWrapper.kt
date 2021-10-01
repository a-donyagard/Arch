package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.AnimeEntity
import com.google.gson.annotations.SerializedName

data class AnimeListWrapper(
    @SerializedName("data")
    val data: List<Anime>,
    @SerializedName("links")
    val links: PaginationLinks?
)

fun List<AnimeEntity>.toAnimeListWrapper(paginationLinks: PaginationLinks): AnimeListWrapper {
    return AnimeListWrapper(
        map {
            it.toAnime()
        },
        paginationLinks
    )
}