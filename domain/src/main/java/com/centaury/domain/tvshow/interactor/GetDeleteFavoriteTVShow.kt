package com.centaury.domain.tvshow.interactor

import com.centaury.domain.CompletableUseCase
import com.centaury.domain.tvshow.TVShowsRepository
import com.centaury.domain.tvshow.model.TVShowsEntity
import io.reactivex.Completable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
class GetDeleteFavoriteTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : CompletableUseCase<CompletableUseCase.None, TVShowsEntity>() {

    override fun buildUseCase(params: TVShowsEntity): Completable =
        tvShowsRepository.deleteFavoriteTVShow(params)

}