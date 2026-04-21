package com.centaury.domain.tvshow.interactor

import com.centaury.domain.CompletableUseCase
import com.centaury.domain.model.TVShowsDB
import com.centaury.domain.tvshow.TVShowsRepository
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetDeleteFavoriteTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : CompletableUseCase<TVShowsDB>() {

    override suspend fun execute(params: TVShowsDB) =
        tvShowsRepository.deleteFavoriteTVShow(params)
}