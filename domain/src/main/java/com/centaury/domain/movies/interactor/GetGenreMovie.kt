package com.centaury.domain.movies.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.movies.MoviesRepository
import com.centaury.domain.movies.model.Genre
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetGenreMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<List<Genre>, UseCase.None>() {

    override fun buildUseCase(params: None): Observable<List<Genre>> =
        moviesRepository.getGenreMovies()
}