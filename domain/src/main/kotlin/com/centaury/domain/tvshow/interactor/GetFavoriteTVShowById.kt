package com.centaury.domain.tvshow.interactor

import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.TVShowsDB
import com.centaury.domain.tvshow.TVShowsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetFavoriteTVShowById @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : FlowableUseCase<TVShowsDB?, GetFavoriteTVShowById.Params>() {

    override fun execute(params: Params): Flow<TVShowsDB?> =
        tvShowsRepository.getFavoriteTVShowById(params.id)

    data class Params(val id: Int)

}