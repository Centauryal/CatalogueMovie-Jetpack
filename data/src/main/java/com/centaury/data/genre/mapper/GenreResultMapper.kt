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
        genreResponse.genres.map { it.toGenreMovie() }

    private fun GenresItem.toGenreMovie() =
        Genre(
            id = this.id,
            name = this.name
        )

    fun transformGenreTVShow(genreResponse: GenreResponse): List<Genre> =
        genreResponse.genres.map { it.toGenreTVShow() }

    private fun GenresItem.toGenreTVShow() =
        Genre(
            id = this.id,
            name = this.name
        )
}