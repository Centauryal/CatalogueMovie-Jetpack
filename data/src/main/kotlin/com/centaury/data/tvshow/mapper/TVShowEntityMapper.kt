package com.centaury.data.tvshow.mapper

import com.centaury.data.tvshow.repository.source.local.entity.TVShowEntity
import com.centaury.domain.model.TVShowsDB
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/24/2020.
 */
@Singleton
class TVShowEntityMapper @Inject constructor() {

    fun transformEntityTVShowById(tvShowEntity: TVShowEntity): TVShowsDB =
        with(tvShowEntity) {
            TVShowsDB(
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
        }

    fun transformTVShowToEntity(tvShow: TVShowsDB): TVShowEntity =
        with(tvShow) {
            TVShowEntity(
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