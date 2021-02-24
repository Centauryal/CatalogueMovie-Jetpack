package com.centaury.domain.search.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.search.SearchRepository
import com.centaury.domain.search.model.Search
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class GetAllSearchMovies @Inject constructor(
    private val searchRepository: SearchRepository
) : UseCase<List<Search>, GetAllSearchMovies.Params>() {

    override fun buildUseCase(params: Params): Observable<List<Search>> =
        searchRepository.getSearchMoviesAll(params.query)

    data class Params(val query: String)

}