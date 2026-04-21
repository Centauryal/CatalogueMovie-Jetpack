package com.centaury.domain.movies.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.model.Genre
import com.centaury.domain.movies.MoviesRepository
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetGenreMovie @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<List<Genre>, UseCase.None>() {

    override suspend fun execute(params: None): List<Genre> =
        moviesRepository.getGenreMovies()
}
