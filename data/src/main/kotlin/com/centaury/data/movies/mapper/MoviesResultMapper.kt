package com.centaury.data.movies.mapper

import com.centaury.data.movies.repository.source.network.result.DetailMovieResponse
import com.centaury.data.movies.repository.source.network.result.GenreMovieResponse
import com.centaury.data.movies.repository.source.network.result.MovieResponse
import com.centaury.data.movies.repository.source.network.result.ResultsItem
import com.centaury.data.movies.repository.source.network.result.SearchMovieResponse
import com.centaury.data.movies.repository.source.network.result.SearchMoviesItem
import com.centaury.domain.model.Detail
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Movie
import com.centaury.domain.model.Search
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
@Singleton
class MoviesResultMapper @Inject constructor() {

    fun transformMovie(movieResponse: MovieResponse): List<Movie> =
        movieResponse.results.map(::toMovies)

    private fun toMovies(resultsItem: ResultsItem) = with(resultsItem) {
        Movie(
            id = this.id,
            title = this.title,
            titleBackground = this.originalTitle,
            image = this.posterPath,
            genre = this.genreIds,
            overview = this.overview,
            date = this.releaseDate
        )
    }

    fun transformDetailMovie(detailMovieResponse: DetailMovieResponse): Detail =
        with(detailMovieResponse) {
            Detail(
                id,
                title,
                originalTitle,
                posterPath,
                backdropPath,
                genres.map { it.name },
                voteAverage,
                voteAverage,
                releaseDate,
                overview
            )
        }

    fun transformGenreMovie(genreMovieResponse: GenreMovieResponse): List<Genre> =
        genreMovieResponse.genres.map { Genre(it.id, it.name) }

    fun transformSearchMovie(searchMovieResponse: SearchMovieResponse): List<Search> =
        searchMovieResponse.results.map(::toSearchMovie)

    private fun toSearchMovie(searchMoviesItem: SearchMoviesItem) = with(searchMoviesItem) {
        Search(
            id = this.id,
            title = this.title,
            titleBackground = this.originalTitle,
            image = this.posterPath,
            genre = this.genreIds,
            overview = this.overview,
            date = this.releaseDate
        )
    }
}