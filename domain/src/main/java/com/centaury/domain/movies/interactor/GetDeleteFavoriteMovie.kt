package com.centaury.domain.movies.interactor

import com.centaury.domain.CompletableUseCase
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.MoviesDB
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetDeleteFavoriteMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : CompletableUseCase<CompletableUseCase.None, MoviesDB>() {

    override fun buildUseCase(params: MoviesDB): Completable =
        moviesRepository.deleteFavoriteMovie(params)


}