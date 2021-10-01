package com.centaury.cataloguemovie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.domain.UseCase
import com.centaury.domain.model.Genre
import com.centaury.domain.model.Movie
import com.centaury.domain.movies.interactor.GetDiscoveryMovie
import com.centaury.domain.movies.interactor.GetGenreMovie
import javax.inject.Inject

/**
 * Created by Centaury on 10/7/2019.
 */
class MovieViewModel @Inject constructor(
    private val getDiscoveryMovie: GetDiscoveryMovie,
    private val getGenreMovie: GetGenreMovie
) : ViewModel(), MovieContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _result = MutableLiveData<List<Movie>>()
    val result: LiveData<List<Movie>>
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
        getDiscoverMovieContract()
        getGenreMovieContract()
    }

    override fun getDiscoverMovieContract() {
        _state.value = LoaderState.ShowLoading
        getDiscoveryMovie.execute(UseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _result.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _error.postValue(it.message)
        })
    }

    override fun getGenreMovieContract() {
        _state.value = LoaderState.ShowLoading
        getGenreMovie.execute(UseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultGenre.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorGenre.postValue(it.message)
        })
    }

    override fun onCleared() {
        super.onCleared()
        getDiscoveryMovie.dispose()
        getGenreMovie.dispose()
    }
}