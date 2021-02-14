package com.centaury.domain.movies.interactor

import com.centaury.domain.FlowableUseCase
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.MoviesDB
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetAllFavoriteMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : FlowableUseCase<List<MoviesDB>, FlowableUseCase.None>() {

    override fun buildUseCase(params: None): Flowable<List<MoviesDB>> =
        moviesRepository.getAllFavoriteMovie()


}