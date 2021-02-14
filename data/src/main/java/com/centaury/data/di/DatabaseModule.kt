package com.centaury.data.di

import android.content.Context
import androidx.room.Room
import com.centaury.data.db.CatalogueDatabase
import com.centaury.data.db.CatalogueDatabase.Companion.DATABASE_NAME
import com.centaury.data.db.dao.MovieDao
import com.centaury.data.db.dao.TVShowDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCatalogueDatabase(context: Context): CatalogueDatabase =
        Room.databaseBuilder(context, CatalogueDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideMovieDao(catalogueDatabase: CatalogueDatabase): MovieDao =
        catalogueDatabase.movieDao()

    @Provides
    @Singleton
    fun provideTVShowDao(catalogueDatabase: CatalogueDatabase): TVShowDao =
        catalogueDatabase.tvShowDao()
}