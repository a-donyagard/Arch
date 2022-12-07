package com.arash.arch.data.source.db

import androidx.room.*
import com.arash.arch.data.model.ResponseWrapperDto
import com.arash.arch.data.model.anime.AnimeDto
import com.arash.arch.domain.model.Anime

@Entity(tableName = "AnimeEntity", indices = [Index(value = ["id"], unique = true)])
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
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
    @Embedded(prefix = "poster_image_")
    val posterImage: ImageLinksDb,
    @Embedded(prefix = "cover_image_")
    val coverImage: ImageLinksDb?,
    @ColumnInfo(name = "episode_count")
    val episodeCount: Int,
    @ColumnInfo(name = "episode_length")
    val episodeLength: Int,
    @ColumnInfo(name = "total_length")
    val totalLength: Int,
    @ColumnInfo(name = "show_type")
    val showType: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "row_created_time")
    val rowCreatedTime: Long
)

fun ResponseWrapperDto<List<AnimeDto>>.toAnimeEntityList(): List<AnimeEntity> {
    return data.map {
        it.attributes.run {
            AnimeEntity(
                it.id,
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
                posterImage.toImageLinksDb(),
                coverImage?.toImageLinksDb(),
                episodeCount,
                episodeLength,
                totalLength,
                showType,
                createdAt,
                System.currentTimeMillis()
            )
        }
    }
}