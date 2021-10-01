package com.centaury.domain.tvshow.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.Genre
import com.centaury.domain.tvshow.TVShowsRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class GetGenreTVShow @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : UseCase<List<Genre>, UseCase.None>() {

    override fun buildUseCase(params: None): Observable<List<Genre>> =
        tvShowsRepository.getGenreTVShows()


}