package com.arash.arch.data.model.anime

import com.arash.arch.data.source.db.AnimeEntity
import com.google.gson.annotations.SerializedName

data class KitsoResponse(
    @SerializedName("data")
    val data: List<Anime>,
    @SerializedName("links")
    val links: PaginationLinks
)

fun List<AnimeEntity>.toKitsoResponse(): KitsoResponse {
    val animeList = map {
        it.toAnime()
    }
    return KitsoResponse(
        animeList,
        PaginationLinks(
            firstOrNull()?.firstPage ?: "",
            firstOrNull()?.nextPage ?: "",
            firstOrNull()?.prevPage ?: "",
            firstOrNull()?.lastPage ?: ""
        )
    )
}