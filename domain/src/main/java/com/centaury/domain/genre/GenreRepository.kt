package com.centaury.domain.genre

import com.centaury.domain.genre.model.Genre
import io.reactivex.Observable

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface GenreRepository {

    fun getGenreMovies(): Observable<List<Genre>>

    fun getGenreTVShows(): Observable<List<Genre>>
}