package com.centaury.data.tvshow.repository.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity.Companion.TABLE_NAME_TV_SHOW

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Entity(tableName = TABLE_NAME_TV_SHOW)
data class TVShowEntity(

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = COLUMN_TV_SHOW_ID)
    val id: Int,

    @ColumnInfo(name = COLUMN_TITLE)
    val title: String,

    @ColumnInfo(name = COLUMN_TITLE_BACKGROUND)
    val titleBackground: String,

    @ColumnInfo(name = COLUMN_IMAGE)
    val image: String,

    @ColumnInfo(name = COLUMN_IMAGE_BACKGROUND)
    val imageBackground: String,

    @ColumnInfo(name = COLUMN_GENRE)
    val genre: String,

    @ColumnInfo(name = COLUMN_VOTE)
    val vote: String,

    @ColumnInfo(name = COLUMN_DATE)
    val date: String,

    @ColumnInfo(name = COLUMN_OVERVIEW)
    val overview: String
) {

    companion object {
        const val TABLE_NAME_TV_SHOW = "tv_show_entities"
        const val COLUMN_TV_SHOW_ID = "tv_show_id"
        private const val COLUMN_TITLE = "name"
        private const val COLUMN_TITLE_BACKGROUND = "title_background"
        private const val COLUMN_IMAGE = "image"
        private const val COLUMN_IMAGE_BACKGROUND = "image_background"
        private const val COLUMN_GENRE = "genre"
        private const val COLUMN_VOTE = "vote"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_OVERVIEW = "overview"
    }
}