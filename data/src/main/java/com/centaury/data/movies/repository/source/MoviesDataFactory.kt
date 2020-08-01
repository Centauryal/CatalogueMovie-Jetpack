package com.centaury.data.movies.repository.source

import com.centaury.data.common.Source
import com.centaury.data.db.CatalogueDatabase
import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.local.LocalMoviesEntityData
import com.centaury.data.movies.repository.source.network.MoviesApi
import com.centaury.data.movies.repository.source.network.NetworkMoviesEntityData
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class MoviesDataFactory @Inject constructor(
    private val moviesApi: MoviesApi,
    private val catalogueDatabase: CatalogueDatabase
) {

    fun createData(source: Source): MoviesEntityData =
        when (source) {
            Source.LOCAL -> LocalMoviesEntityData(catalogueDatabase)
            Source.NETWORK -> NetworkMoviesEntityData(
                moviesApi
            )
        }
}