package com.centaury.domain.tvshow.interactor

import androidx.paging.PagingData
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.TVShow
import com.centaury.domain.tvshow.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class GetDiscoveryTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : FlowableUseCase<PagingData<TVShow>, FlowableUseCase.None>() {

    override fun execute(params: None): Flow<PagingData<TVShow>> =
        tvShowsRepository.getDiscoveryTVShows()
}