package com.centaury.data.detail.repository.source

import com.centaury.data.common.Source
import com.centaury.data.detail.repository.DetailEntityData
import com.centaury.data.detail.repository.source.local.LocalDetailEntityData
import com.centaury.data.detail.repository.source.network.DetailApi
import com.centaury.data.detail.repository.source.network.NetworkDetailEntityData
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/16/2020.
 */
class DetailDataFactory @Inject constructor(
    private val detailApi: DetailApi
) {

    fun createData(source: Source): DetailEntityData =
        when (source) {
            Source.LOCAL -> LocalDetailEntityData()
            Source.NETWORK -> NetworkDetailEntityData(detailApi)
        }
}