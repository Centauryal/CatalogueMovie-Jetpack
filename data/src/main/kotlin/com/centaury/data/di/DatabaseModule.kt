package com.centaury.data.di

import android.content.Context
import androidx.room.Room
import com.centaury.cataloguemovie.BuildConfig
import com.centaury.data.db.CatalogueDatabase
import com.centaury.data.db.CatalogueDatabase.Companion.DATABASE_NAME
import com.centaury.data.db.dao.MovieDao
import com.centaury.data.db.dao.TVShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideCatalogueDatabase(@ApplicationContext context: Context): CatalogueDatabase {
        val builder = Room.databaseBuilder(context, CatalogueDatabase::class.java, DATABASE_NAME)
        val factory = SupportOpenHelperFactory(BuildConfig.PASS_PHRASE.toByteArray())
        builder.openHelperFactory(factory)

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(catalogueDatabase: CatalogueDatabase): MovieDao =
        catalogueDatabase.movieDao()

    @Provides
    @Singleton
    fun provideTVShowDao(catalogueDatabase: CatalogueDatabase): TVShowDao =
        catalogueDatabase.tvShowDao()
}
