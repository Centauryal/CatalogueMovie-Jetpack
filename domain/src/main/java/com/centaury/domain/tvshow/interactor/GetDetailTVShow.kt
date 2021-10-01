package com.centaury.domain.tvshow.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.Detail
import com.centaury.domain.tvshow.TVShowsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
class GetDetailTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : UseCase<Detail, GetDetailTVShow.Params>() {

    override fun buildUseCase(params: Params): Observable<Detail> =
        tvShowsRepository.getDetailTVShow(params.id)

    data class Params(val id: Int)
}