package com.centaury.domain.tvshow.interactor

import com.centaury.domain.FlowableUseCase
import com.centaury.domain.tvshow.TVShowsRepository
import com.centaury.domain.tvshow.model.TVShowsEntity
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetFavoriteTVShowById @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : FlowableUseCase<TVShowsEntity, GetFavoriteTVShowById.Params>() {

    override fun buildUseCase(params: Params): Flowable<TVShowsEntity> =
        tvShowsRepository.getFavoriteTVShowById(params.id)

    data class Params(val id: Int)

}