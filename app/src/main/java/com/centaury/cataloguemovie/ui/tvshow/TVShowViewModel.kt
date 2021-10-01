package com.centaury.cataloguemovie.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.domain.UseCase
import com.centaury.domain.model.Genre
import com.centaury.domain.model.TVShow
import com.centaury.domain.tvshow.interactor.GetDiscoveryTVShow
import com.centaury.domain.tvshow.interactor.GetGenreTVShow
import javax.inject.Inject

/**
 * Created by Centaury on 10/7/2019.
 */
class TVShowViewModel @Inject constructor(
    private val getDiscoveryTVShow: GetDiscoveryTVShow,
    private val getGenreTVShow: GetGenreTVShow
) : ViewModel(), TVShowContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _result = MutableLiveData<List<TVShow>>()
    val result: LiveData<List<TVShow>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _resultGenre = MutableLiveData<List<Genre>>()
    val resultGenre: LiveData<List<Genre>>
        get() = _resultGenre

    private val _errorGenre = MutableLiveData<String>()
    val errorGenre: LiveData<String>
        get() = _errorGenre

    init {
        getDiscoverTVShowContract()
        getGenreTVShowContract()
    }

    override fun getDiscoverTVShowContract() {
        _state.value = LoaderState.ShowLoading
        getDiscoveryTVShow.execute(UseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _result.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _error.postValue(it.message)
        })
    }

    override fun getGenreTVShowContract() {
        _state.value = LoaderState.ShowLoading
        getGenreTVShow.execute(UseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultGenre.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorGenre.postValue(it.message)
        })
    }

    override fun onCleared() {
        super.onCleared()
        getDiscoveryTVShow.dispose()
        getGenreTVShow.dispose()
    }
}