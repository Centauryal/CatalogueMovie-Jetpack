package com.centaury.domain.detail.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.detail.DetailRepository
import com.centaury.domain.detail.model.Detail
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetDetailMovie @Inject constructor(
    private val detailRepository: DetailRepository
) : UseCase<Detail, GetDetailMovie.Params>() {

    override fun buildUseCase(params: Params): Observable<Detail> =
        detailRepository.getDetailMovie(params.id)

    data class Params(val id: Int)
}