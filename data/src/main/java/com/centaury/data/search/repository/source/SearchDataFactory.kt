package com.centaury.data.search.repository.source

import com.centaury.data.common.Source
import com.centaury.data.search.repository.SearchEntityData
import com.centaury.data.search.repository.source.local.LocalSearchEntityData
import com.centaury.data.search.repository.source.network.NetworkSearchEntityData
import com.centaury.data.search.repository.source.network.SearchApi
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/17/21.
 */
class SearchDataFactory @Inject constructor(
    private val searchApi: SearchApi
) {

    fun createData(source: Source): SearchEntityData =
        when (source) {
            Source.LOCAL -> LocalSearchEntityData()
            Source.NETWORK -> NetworkSearchEntityData(searchApi)
        }
}