package com.centaury.domain.tvshow.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.Search
import com.centaury.domain.tvshow.TVShowsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/21/21.
 */
class GetAllSearchTVShows @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : UseCase<List<Search>, GetAllSearchTVShows.Params>() {

    override fun buildUseCase(params: Params): Observable<List<Search>> =
        tvShowsRepository.getSearchTVShowsAll(params.query)

    data class Params(val query: String)

}