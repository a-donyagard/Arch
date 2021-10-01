package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.AnimeEntity
import com.google.gson.annotations.SerializedName

data class AnimeListWrapper(
    @SerializedName("data")
    val data: List<Anime>,
    @SerializedName("links")
    val links: PaginationLinks?
)

fun List<AnimeEntity>.toKitsoResponse(): AnimeListWrapper {
    val animeList = map {
        it.toAnime()
    }
    return AnimeListWrapper(
        animeList,
        firstOrNull()?.paginationLinks?.toPaginationLinks()
    )
}