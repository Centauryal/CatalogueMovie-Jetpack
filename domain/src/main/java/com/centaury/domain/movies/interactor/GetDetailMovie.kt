package com.centaury.domain.movies.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.Detail
import com.centaury.domain.movies.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetDetailMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<Detail, GetDetailMovie.Params>() {

    override fun buildUseCase(params: Params): Observable<Detail> =
        moviesRepository.getDetailMovie(params.id)

    data class Params(val id: Int)
}