package com.centaury.domain.genre.interactor

import com.centaury.domain.UseCase
import com.centaury.domain.genre.GenreRepository
import com.centaury.domain.genre.model.Genre
import io.reactivex.Observable
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class GetGenreMovie @Inject constructor(
    private val genreRepository: GenreRepository
) : UseCase<List<Genre>, UseCase.None>() {

    override fun buildUseCase(params: None): Observable<List<Genre>> =
        genreRepository.getGenreMovies()
}