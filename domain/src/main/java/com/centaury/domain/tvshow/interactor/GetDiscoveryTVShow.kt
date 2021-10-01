package com.centaury.domain.tvshow.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.TVShow
import com.centaury.domain.tvshow.TVShowsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class GetDiscoveryTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : UseCase<List<TVShow>, UseCase.None>() {

    override fun buildUseCase(params: None): Observable<List<TVShow>> =
        tvShowsRepository.getDiscoveryTVShows()


}