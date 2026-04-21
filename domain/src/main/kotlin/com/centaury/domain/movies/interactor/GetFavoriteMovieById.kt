package com.centaury.domain.movies.interactor

import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.MoviesDB
import com.centaury.domain.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetFavoriteMovieById @Inject constructor(
    private val moviesRepository: MoviesRepository
) : FlowableUseCase<MoviesDB?, GetFavoriteMovieById.Params>() {

    override fun execute(params: Params): Flow<MoviesDB?> =
        moviesRepository.getFavoriteMovieById(params.id)

    data class Params(val id: Int)

}
