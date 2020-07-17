package com.centaury.domain.detail.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.detail.DetailRepository
import com.centaury.domain.detail.model.Detail
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
class GetDetailTVShow @Inject constructor(
    private val detailRepository: DetailRepository
) : UseCase<Detail, GetDetailTVShow.Params>() {

    override fun buildUseCase(params: Params): Observable<Detail> =
        detailRepository.getDetailTVShow(params.id)

    data class Params(val id: Int)
}