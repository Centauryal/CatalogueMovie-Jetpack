package com.centaury.data.tvshow.repository.source

import com.centaury.data.common.Source
import com.centaury.data.tvshow.repository.TVShowsEntityData
import com.centaury.data.tvshow.repository.source.local.LocalTVShowsEntityData
import com.centaury.data.tvshow.repository.source.network.NetworkTVShowsEntityData
import com.centaury.data.tvshow.repository.source.network.TVShowApi
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/15/2020.
 */
class TVShowsDataFactory @Inject constructor(
    private val tvShowApi: TVShowApi
) {

    fun createData(source: Source): TVShowsEntityData =
        when (source) {
            Source.LOCAL -> LocalTVShowsEntityData()
            Source.NETWORK -> NetworkTVShowsEntityData(tvShowApi)
        }
}