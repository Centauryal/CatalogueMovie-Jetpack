package com.centaury.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.centaury.data.db.dao.MovieDao
import com.centaury.data.db.dao.TVShowDao
import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Database(entities = [MovieEntity::class, TVShowEntity::class], version = 1, exportSchema = false)
abstract class CatalogueDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvShowDao(): TVShowDao

    companion object {
        const val DATABASE_NAME = "movie_catalogue"
    }
}