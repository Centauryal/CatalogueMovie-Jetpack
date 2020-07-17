package com.centaury.data.detail.mapper

import com.centaury.data.detail.repository.source.network.result.movie.DetailMovieResponse
import com.centaury.data.detail.repository.source.network.result.movie.MovieGenresItem
import com.centaury.data.detail.repository.source.network.result.tvshow.DetailTVShowResponse
import com.centaury.data.detail.repository.source.network.result.tvshow.TVShowGenresItem
import com.centaury.domain.detail.model.Detail
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
class DetailResultMapper @Inject constructor() {

    fun transformDetailMovie(detailMovieResponse: DetailMovieResponse): Detail {
        return Detail(
            detailMovieResponse.id,
            detailMovieResponse.title,
            detailMovieResponse.posterPath,
            detailMovieResponse.backdropPath,
            listGenre(detailMovieResponse.genres),
            detailMovieResponse.voteAverage,
            detailMovieResponse.voteAverage,
            detailMovieResponse.releaseDate,
            detailMovieResponse.overview
        )
    }

    fun transformDetailTVShow(detailTVShowResponse: DetailTVShowResponse): Detail {
        return Detail(
            detailTVShowResponse.id,
            detailTVShowResponse.name,
            detailTVShowResponse.posterPath,
            detailTVShowResponse.backdropPath,
            listGenreTVShow(detailTVShowResponse.genres),
            detailTVShowResponse.voteAverage,
            detailTVShowResponse.voteAverage,
            detailTVShowResponse.firstAirDate,
            detailTVShowResponse.overview
        )
    }

    private fun listGenre(itemList: List<MovieGenresItem>): List<String> {
        val genre: MutableList<String> = ArrayList()

        for (genreItem in itemList) {
            genre.add(genreItem.name)
        }
        return genre
    }

    private fun listGenreTVShow(itemList: List<TVShowGenresItem>): List<String> {
        val genre: MutableList<String> = ArrayList()

        for (genreItem in itemList) {
            genre.add(genreItem.name)
        }
        return genre
    }
}