package com.centaury.data.genre.repository.source.network

import com.centaury.data.genre.repository.GenreEntityData
import com.centaury.data.genre.repository.source.network.result.GenreResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class NetworkGenreEntityData @Inject constructor(
    private val genreApi: GenreApi
) : GenreEntityData {
    override fun genreMovies(): Observable<GenreResponse> = genreApi.genreMovies()

    override fun genreTVShows(): Observable<GenreResponse> = genreApi.genreTVShows()
}