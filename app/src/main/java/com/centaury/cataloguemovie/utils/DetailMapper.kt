package com.centaury.cataloguemovie.utils

import com.centaury.domain.detail.model.Detail
import com.centaury.domain.movies.model.MoviesEntity
import com.centaury.domain.tvshow.model.TVShowsEntity

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 10/21/2020.
 */
object DetailMapper {

    fun mapperMovieToDetail(movie: MoviesEntity): Detail {
        return Detail(
            movie.id,
            movie.title,
            movie.titleBackground,
            movie.image,
            movie.imageBackground,
            listOf(movie.genre),
            movie.vote.toDouble(),
            movie.vote.toDouble(),
            movie.date,
            movie.overview
        )
    }

    fun mapperTVShowToDetail(tvShow: TVShowsEntity): Detail {
        return Detail(
            tvShow.id,
            tvShow.title,
            tvShow.titleBackground,
            tvShow.image,
            tvShow.imageBackground,
            listOf(tvShow.genre),
            tvShow.vote.toDouble(),
            tvShow.vote.toDouble(),
            tvShow.date,
            tvShow.overview
        )
    }
}