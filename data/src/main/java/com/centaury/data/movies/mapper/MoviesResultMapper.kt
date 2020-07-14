package com.centaury.data.movies.mapper

import com.centaury.data.movies.repository.source.network.result.genre.GenreResponse
import com.centaury.data.movies.repository.source.network.result.genre.GenresItem
import com.centaury.data.movies.repository.source.network.result.movie.MovieResponse
import com.centaury.data.movies.repository.source.network.result.movie.ResultsItem
import com.centaury.domain.movies.model.Genre
import com.centaury.domain.movies.model.Movie
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
class MoviesResultMapper @Inject constructor() {

    fun transformMovie(movieResponse: MovieResponse): List<Movie> =
        movieResponse.results.map { it.toMovies() }

    private fun ResultsItem.toMovies() = Movie(
        id = this.id,
        title = this.title,
        titleBackground = this.originalTitle,
        image = this.posterPath,
        genre = this.genreIds,
        overview = this.overview,
        date = this.releaseDate
    )

    fun transformGenreMovie(genreResponse: GenreResponse): List<Genre> =
        genreResponse.genres.map { it.toGenreMovie() }

    private fun GenresItem.toGenreMovie() =
        Genre(
            id = this.id,
            name = this.name
        )
}