package com.centaury.cataloguemovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.domain.detail.interactor.GetDetailMovie
import com.centaury.domain.detail.interactor.GetDetailTVShow
import com.centaury.domain.detail.model.Detail
import javax.inject.Inject

/**
 * Created by Centaury on 10/7/2019.
 */
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovie: GetDetailMovie,
    private val getDetailTVShow: GetDetailTVShow
) : ViewModel(), DetailContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _resultMovie = MutableLiveData<Detail>()
    val resultMovie: LiveData<Detail>
        get() = _resultMovie

    private val _errorMovie = MutableLiveData<String>()
    val errorMovie: LiveData<String>
        get() = _errorMovie

    private val _resultTVShow = MutableLiveData<Detail>()
    val resultTVShow: LiveData<Detail>
        get() = _resultTVShow

    private val _errorTVShow = MutableLiveData<String>()
    val errorTVShow: LiveData<String>
        get() = _errorTVShow

    override fun getDetailMovieContract(movieId: Int) {
        _state.value = LoaderState.ShowLoading
        getDetailMovie.execute(GetDetailMovie.Params(movieId), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultMovie.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorMovie.postValue(it.message)
        })
    }

    override fun getDetailTVShowContract(tvShowId: Int) {
        _state.value = LoaderState.ShowLoading
        getDetailTVShow.execute(GetDetailTVShow.Params(tvShowId), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultTVShow.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorTVShow.postValue(it.message)
        })
    }

}