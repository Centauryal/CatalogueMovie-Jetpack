package com.centaury.data.genre.repository.source

import com.centaury.data.common.Source
import com.centaury.data.genre.repository.GenreEntityData
import com.centaury.data.genre.repository.source.local.LocalGenreEntityData
import com.centaury.data.genre.repository.source.network.GenreApi
import com.centaury.data.genre.repository.source.network.NetworkGenreEntityData
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class GenreDataFactory @Inject constructor(
    private val genreApi: GenreApi
) {

    fun createData(source: Source): GenreEntityData =
        when (source) {
            Source.LOCAL -> LocalGenreEntityData()
            Source.NETWORK -> NetworkGenreEntityData(genreApi)
        }
}