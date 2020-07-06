package com.centaury.domain.movies.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.Detail
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetDetailMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<Detail, UseCase.None>() {

    override fun buildUseCase(params: None): Observable<Detail> {
        TODO("Not yet implemented")
    }


}