package com.centaury.data.movies.mapper

import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.domain.movies.model.MoviesEntity
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
class MoviesEntityMapper @Inject constructor() {

    fun transformEntityMovie(movieEntity: List<MovieEntity>): List<MoviesEntity> =
        movieEntity.map { it.toMovies() }

    private fun MovieEntity.toMovies() = MoviesEntity(
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

    fun transformEntityMovieById(movieEntity: MovieEntity): MoviesEntity {
        return MoviesEntity(
            movieEntity.id,
            movieEntity.title,
            movieEntity.titleBackground,
            movieEntity.image,
            movieEntity.imageBackground,
            movieEntity.genre,
            movieEntity.vote,
            movieEntity.date,
            movieEntity.overview
        )
    }

    fun transformMovieToEntity(movie: MoviesEntity): MovieEntity {
        return MovieEntity(
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
}