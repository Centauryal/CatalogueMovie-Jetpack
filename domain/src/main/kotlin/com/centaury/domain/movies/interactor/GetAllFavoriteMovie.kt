package com.centaury.domain.movies.interactor

import androidx.paging.PagingData
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.MoviesDB
import com.centaury.domain.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetAllFavoriteMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : FlowableUseCase<PagingData<MoviesDB>, FlowableUseCase.None>() {

    override fun execute(params: None): Flow<PagingData<MoviesDB>> =
        moviesRepository.getAllFavoriteMovie()
}