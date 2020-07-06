package com.centaury.domain.movies.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.Movie
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetDiscoveryMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<List<Movie>, UseCase.None>() {

    override fun buildUseCase(params: None): Observable<List<Movie>> =
        moviesRepository.getDiscoveryMovies()
}