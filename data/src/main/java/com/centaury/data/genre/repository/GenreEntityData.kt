package com.centaury.data.genre.repository

import com.centaury.data.genre.repository.source.network.result.GenreResponse
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface GenreEntityData {

    fun genreMovies(): Observable<GenreResponse>

    fun genreTVShows(): Observable<GenreResponse>
}