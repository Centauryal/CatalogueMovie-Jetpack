package com.centaury.domain.movies.interactor

import com.centaury.domain.CompletableUseCase
import com.centaury.domain.model.MoviesDB
import com.centaury.domain.movies.MoviesRepository
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetInsertFavoriteMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : CompletableUseCase<MoviesDB>() {
    override suspend fun execute(params: MoviesDB) =
        moviesRepository.insertFavoriteMovie(params)
}