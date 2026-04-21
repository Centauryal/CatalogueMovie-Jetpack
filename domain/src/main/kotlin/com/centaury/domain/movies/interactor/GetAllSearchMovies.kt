package com.centaury.domain.movies.interactor

import androidx.paging.PagingData
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.Search
import com.centaury.domain.movies.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class GetAllSearchMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) : FlowableUseCase<PagingData<Search>, GetAllSearchMovies.Params>() {

    override fun execute(params: Params): Flow<PagingData<Search>> =
        moviesRepository.getSearchMoviesAll(params.query)

    data class Params(val query: String)

}