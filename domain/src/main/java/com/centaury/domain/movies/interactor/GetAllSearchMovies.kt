package com.centaury.domain.movies.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.Search
import com.centaury.domain.movies.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class GetAllSearchMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<List<Search>, GetAllSearchMovies.Params>() {

    override fun buildUseCase(params: Params): Observable<List<Search>> =
        moviesRepository.getSearchMoviesAll(params.query)

    data class Params(val query: String)

}