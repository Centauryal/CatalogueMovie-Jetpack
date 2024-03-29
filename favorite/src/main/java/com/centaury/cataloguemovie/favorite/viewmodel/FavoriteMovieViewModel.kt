package com.centaury.cataloguemovie.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.Event
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.Status
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.MoviesDB
import com.centaury.domain.movies.interactor.GetAllFavoriteMovie
import com.centaury.domain.movies.interactor.GetDeleteFavoriteMovie
import com.centaury.domain.movies.interactor.GetFavoriteMovieById
import javax.inject.Inject

/**
 * Created by Centaury on 11/23/2019.
 */
class FavoriteMovieViewModel @Inject constructor(
    private val getAllFavoriteMovie: GetAllFavoriteMovie,
    private val getFavoriteMovieById: GetFavoriteMovieById,
    private val getDeleteFavoriteMovie: GetDeleteFavoriteMovie
) : ViewModel(), FavoriteMovieContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _result = MutableLiveData<List<MoviesDB>>()
    val result: LiveData<List<MoviesDB>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _resultMovieById = MutableLiveData<MoviesDB>()
    val resultMovieById: LiveData<MoviesDB>
        get() = _resultMovieById

    private val _errorMovieById = MutableLiveData<String>()
    val errorMovieById: LiveData<String>
        get() = _errorMovieById

    private val _resultDeleteMovie = MutableLiveData<Event<Status>>()
    val resultDeleteMovie: LiveData<Event<Status>>
        get() = _resultDeleteMovie

    private val _errorDeleteMovie = MutableLiveData<String>()
    val errorDeleteMovie: LiveData<String>
        get() = _errorDeleteMovie

    init {
        getAllFavoriteMovieContract()
    }

    override fun getAllFavoriteMovieContract() {
        _state.value = LoaderState.ShowLoading
        getAllFavoriteMovie.execute(FlowableUseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _result.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _error.postValue(it.message)
        })
    }

    override fun getFavoriteMovieByIdContract(movieId: Int) =
        getFavoriteMovieById.execute(GetFavoriteMovieById.Params(movieId), onSuccess = {
            _resultMovieById.postValue(it)
        }, onError = {
            _errorMovieById.postValue(it.message)
        })

    override fun getDeleteFavoriteMovieContract(movie: MoviesDB) =
        getDeleteFavoriteMovie.execute(movie, onSuccess = {
            _resultDeleteMovie.value = Event(Status.SUCCESS)
        }, onError = {
            _errorDeleteMovie.postValue(it.message)
        })

    override fun onCleared() {
        super.onCleared()
        getAllFavoriteMovie.dispose()
        getDeleteFavoriteMovie.dispose()
        getFavoriteMovieById.dispose()
    }


}
