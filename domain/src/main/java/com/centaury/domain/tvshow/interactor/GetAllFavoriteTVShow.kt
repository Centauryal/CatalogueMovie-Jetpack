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
class GetAllFavoriteTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : FlowableUseCase<List<TVShowsEntity>, FlowableUseCase.None>() {

    override fun buildUseCase(params: None): Flowable<List<TVShowsEntity>> =
        tvShowsRepository.getAllFavoriteTVShow()

}