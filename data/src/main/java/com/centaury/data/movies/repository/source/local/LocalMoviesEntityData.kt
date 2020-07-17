package com.centaury.data.movies.repository.source.local

import com.centaury.data.movies.repository.MoviesEntityData
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class LocalMoviesEntityData @Inject constructor(
) : MoviesEntityData {
    override fun discoveryMovies(): Observable<MovieResponse> {
        TODO("Not yet implemented")
    }

}