package com.centaury.data.movies.mapper

import com.centaury.data.movies.repository.source.local.entity.MovieEntity
import com.centaury.domain.model.MoviesDB
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Singleton
class MoviesEntityMapper @Inject constructor() {

    fun transformEntityMovieById(movieEntity: MovieEntity): MoviesDB =
        with(movieEntity) {
            MoviesDB(
                id = id,
                title = title,
                titleBackground = titleBackground,
                image = image,
                imageBackground = imageBackground,
                genre = genre,
                vote = vote,
                date = date,
                overview = overview
            )
        }

    fun transformMovieToEntity(movie: MoviesDB): MovieEntity =
        with(movie) {
            MovieEntity(
                id,
                title,
                titleBackground,
                image,
                imageBackground,
                genre,
                vote,
                date,
                overview
            )
        }
}