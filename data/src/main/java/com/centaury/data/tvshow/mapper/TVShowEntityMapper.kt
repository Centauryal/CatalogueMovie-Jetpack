package com.centaury.data.tvshow.mapper

import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.domain.tvshow.model.TVShowsDB
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
class TVShowEntityMapper @Inject constructor() {

    fun transformEntityTVShow(tvShowEntity: List<TVShowEntity>): List<TVShowsDB> =
        tvShowEntity.map { it.toTVShow() }

    fun transformEntityTVShowById(tvShowEntity: TVShowEntity): TVShowsDB = tvShowEntity.toTVShow()

    private fun TVShowEntity.toTVShow() = TVShowsDB(
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

    fun transformTVShowToEntity(tvShow: TVShowsDB): TVShowEntity =
        TVShowEntity(
            tvShow.id,
            tvShow.title,
            tvShow.titleBackground,
            tvShow.image,
            tvShow.imageBackground,
            tvShow.genre,
            tvShow.vote,
            tvShow.date,
            tvShow.overview
        )
}