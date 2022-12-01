package com.arash.arch.ui.anime

import com.arash.arch.R
import com.arash.arch.domain.model.Anime
import com.arash.arch.util.providers.ResourceProvider

sealed class AnimeDataItem {
    companion object {
        const val ANIME_ITEM = 0
        const val LOADING_ITEM = 1
    }

    abstract val viewType: Int
    abstract val id: Int

    data class AnimeItem(
        val anime: Anime,
        val coverImageUrl: String,
        val episodesDetailText: String
    ) : AnimeDataItem() {
        override val viewType: Int = ANIME_ITEM
        override val id: Int = anime.id
    }

    object LoadingItem : AnimeDataItem() {
        override val viewType = LOADING_ITEM
        override val id: Int = -1
    }
}

fun List<Anime>.toAnimeDataItems(resourceProvider: ResourceProvider): List<AnimeDataItem.AnimeItem> {
    return map { anime ->
        AnimeDataItem.AnimeItem(
            anime,
            anime.attributes.coverImage?.original ?: anime.attributes.posterImage.original,
            String.format(
                resourceProvider.getString(R.string.episodes_detail_format),
                anime.attributes.episodeCount,
                anime.attributes.startDate.split("-").first()
            )
        )
    }
}
