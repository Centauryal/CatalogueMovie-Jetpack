package com.centaury.data.genre.repository.source.network

import com.centaury.data.genre.repository.source.network.result.GenreResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
interface GenreApi {

    @GET("genre/movie/list")
    fun genreMovies(): Observable<GenreResponse>

    @GET("genre/tv/list")
    fun genreTVShows(): Observable<GenreResponse>
}