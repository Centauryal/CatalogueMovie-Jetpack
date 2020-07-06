package com.centaury.cataloguemovie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.data.CatalogueRepository
import com.centaury.cataloguemovie.data.datasource.TVShowDataSource
import com.centaury.cataloguemovie.data.datasource.TVShowDataSourceFactory
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by Centaury on 10/7/2019.
 */
class TVShowViewModel @Inject constructor(private val catalogueRepository: CatalogueRepository) :
    ViewModel() {
    private var tvshows: LiveData<PagedList<TVShowResultsItem>>? = null
    var loadingState: LiveData<Boolean>? = null
        private set
    var loadMoreLoadingState: LiveData<Boolean>? = null
        private set

    private fun init() {
        val executor: Executor =
            Executors.newFixedThreadPool(5)
        val tvShowDataSource = TVShowDataSource()
        val tvShowDataSourceFactory =
            TVShowDataSourceFactory(tvShowDataSource)
        val pagedListConfig: PagedList.Config = Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(1)
            .setPrefetchDistance(10)
            .setPageSize(10).build()
        tvshows = LivePagedListBuilder(tvShowDataSourceFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
        loadingState = tvShowDataSource.loadingState
        loadMoreLoadingState = tvShowDataSource.loadMoreLoadingState
    }

    val tVShows: LiveData<Any>?
        get() = tvshows

    fun getGenreTVShow(language: String?): LiveData<List<GenresItem>> {
        return catalogueRepository.getGenreTVShow(language)
    }

    init {
        init()
    }
}