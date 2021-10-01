package com.arash.arch.data.source.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.arash.arch.data.model.anime.KitsoResponse

@Entity(tableName = "AnimeEntity", indices = [Index(value = ["id"], unique = true)])
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "average_rating")
    val averageRating: String,
    @ColumnInfo(name = "user_count")
    val userCount: Int,
    @ColumnInfo(name = "favorites_count")
    val favoritesCount: Int,
    @ColumnInfo(name = "start_date")
    val startDate: String,
    @ColumnInfo(name = "end_date")
    val endDate: String?,
    @ColumnInfo(name = "next_release")
    val nextRelease: String?,
    @ColumnInfo(name = "popularity_rank")
    val popularityRank: Int,
    @ColumnInfo(name = "rating_rank")
    val ratingRank: Int,
    @ColumnInfo(name = "age_rating")
    val ageRating: String,
    @ColumnInfo(name = "age_rating_guide")
    val ageRatingGuide: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "poster_image")
    val posterImage: String,
    @ColumnInfo(name = "cover_image")
    val coverImage: String?,
    @ColumnInfo(name = "episode_count")
    val episodeCount: Int,
    @ColumnInfo(name = "episode_length")
    val episodeLength: Int,
    @ColumnInfo(name = "total_length")
    val totalLength: Int,
    @ColumnInfo(name = "show_type")
    val showType: String,
    @ColumnInfo(name = "first_page")
    val firstPage: String,
    @ColumnInfo(name = "next_page")
    val nextPage: String?,
    @ColumnInfo(name = "prev_page")
    val prevPage: String?,
    @ColumnInfo(name = "last_page")
    val lastPage: String
)

fun KitsoResponse.toAnimeEntityList(): List<AnimeEntity> {
    return data.map {
        AnimeEntity(
            it.id,
            it.attributes.createdAt,
            it.attributes.synopsis,
            it.attributes.description,
            it.attributes.title,
            it.attributes.averageRating,
            it.attributes.userCount,
            it.attributes.favoritesCount,
            it.attributes.startDate,
            it.attributes.endDate,
            it.attributes.nextRelease,
            it.attributes.popularityRank,
            it.attributes.ratingRank,
            it.attributes.ageRating,
            it.attributes.ageRatingGuide,
            it.attributes.status,
            it.attributes.posterImage.original,
            it.attributes.coverImage?.original,
            it.attributes.episodeCount,
            it.attributes.episodeLength,
            it.attributes.totalLength,
            it.attributes.showType,
            links.first,
            links.next,
            links.prev,
            links.last
        )
    }
}