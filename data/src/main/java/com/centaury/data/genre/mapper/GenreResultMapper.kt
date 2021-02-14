package com.centaury.data.genre.mapper

import com.centaury.data.genre.repository.source.network.result.GenreResponse
import com.centaury.data.genre.repository.source.network.result.GenresItem
import com.centaury.domain.genre.model.Genre
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class GenreResultMapper @Inject constructor() {

    fun transformGenreMovie(genreResponse: GenreResponse): List<Genre> =
        genreResponse.genres.map { it.toGenre() }

    fun transformGenreTVShow(genreResponse: GenreResponse): List<Genre> =
        genreResponse.genres.map { it.toGenre() }

    private fun GenresItem.toGenre() =
        Genre(
            id = this.id,
            name = this.name
        )
}