package com.centaury.data.movies.mapper

import com.centaury.data.movies.repository.source.network.result.*
import com.centaury.domain.model.Detail
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Movie
import com.centaury.domain.model.Search
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

    fun transformDetailMovie(detailMovieResponse: DetailMovieResponse): Detail =
        Detail(
            detailMovieResponse.id,
            detailMovieResponse.title,
            detailMovieResponse.originalTitle,
            detailMovieResponse.posterPath,
            detailMovieResponse.backdropPath,
            listGenre(detailMovieResponse.genres),
            detailMovieResponse.voteAverage,
            detailMovieResponse.voteAverage,
            detailMovieResponse.releaseDate,
            detailMovieResponse.overview
        )

    private fun listGenre(itemList: List<MovieGenresItem>): List<String> {
        val genre: MutableList<String> = ArrayList()

        for (genreItem in itemList) {
            genre.add(genreItem.name)
        }
        return genre
    }

    fun transformGenreMovie(genreMovieResponse: GenreMovieResponse): List<Genre> =
        genreMovieResponse.genres.map { it.toGenre() }

    private fun MovieGenresItem.toGenre() =
        Genre(
            id = this.id,
            name = this.name
        )

    fun transformSearchMovie(searchMovieResponse: SearchMovieResponse): List<Search> =
        searchMovieResponse.results.map { it.toSearchMovie() }

    private fun SearchMoviesItem.toSearchMovie() = Search(
        id = this.id,
        title = this.title,
        titleBackground = this.originalTitle,
        image = this.posterPath,
        genre = this.genreIds,
        overview = this.overview,
        date = this.releaseDate
    )
}