package com.centaury.data.movies.mapper

import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.domain.movies.model.MoviesDB
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
class MoviesEntityMapper @Inject constructor() {

    fun transformEntityMovie(movieEntity: List<MovieEntity>): List<MoviesDB> =
        movieEntity.map { it.toMovies() }

    fun transformEntityMovieById(movieEntity: MovieEntity): MoviesDB = movieEntity.toMovies()

    private fun MovieEntity.toMovies() = MoviesDB(
        id = this.id,
        title = this.title,
        titleBackground = this.titleBackground,
        image = this.image,
        imageBackground = this.imageBackground,
        genre = this.genre,
        vote = this.vote,
        date = this.date,
        overview = this.overview
    )

    fun transformMovieToEntity(movie: MoviesDB): MovieEntity =
        MovieEntity(
            movie.id,
            movie.title,
            movie.titleBackground,
            movie.image,
            movie.imageBackground,
            movie.genre,
            movie.vote,
            movie.date,
            movie.overview
        )
}