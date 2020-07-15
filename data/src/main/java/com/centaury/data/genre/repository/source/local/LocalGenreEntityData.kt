package com.centaury.data.genre.repository.source.local

import com.centaury.data.genre.repository.GenreEntityData
import com.centaury.data.genre.repository.source.network.result.GenreResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class LocalGenreEntityData @Inject constructor(
) : GenreEntityData {

    override fun genreMovies(): Observable<GenreResponse> {
        TODO("Not yet implemented")
    }

    override fun genreTVShows(): Observable<GenreResponse> {
        TODO("Not yet implemented")
    }
}