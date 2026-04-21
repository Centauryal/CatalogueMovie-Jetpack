package com.centaury.domain.tvshow.interactor

import androidx.paging.PagingData
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.Search
import com.centaury.domain.tvshow.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/21/21.
 */
class GetAllSearchTVShows @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : FlowableUseCase<PagingData<Search>, GetAllSearchTVShows.Params>() {

    override fun execute(params: Params): Flow<PagingData<Search>> =
        tvShowsRepository.getSearchTVShowsAll(params.query)

    data class Params(val query: String)

}