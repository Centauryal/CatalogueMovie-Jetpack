package com.centaury.cataloguemovie.favorite.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.Event
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.Status
import com.centaury.domain.model.MoviesDB
import com.centaury.domain.model.TVShowsDB
import com.centaury.domain.movies.interactor.GetDeleteFavoriteMovie
import com.centaury.domain.movies.interactor.GetFavoriteMovieById
import com.centaury.domain.movies.interactor.GetInsertFavoriteMovie
import com.centaury.domain.tvshow.interactor.GetDeleteFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetFavoriteTVShowById
import com.centaury.domain.tvshow.interactor.GetInsertFavoriteTVShow
import javax.inject.Inject

/**
 * Created by Centaury on 11/27/2019.
 */
class DetailFavoriteMovieViewModel @Inject constructor(
    private val getFavoriteMovieById: GetFavoriteMovieById,
    private val getInsertFavoriteMovie: GetInsertFavoriteMovie,
    private val getDeleteFavoriteMovie: GetDeleteFavoriteMovie,
    private val getFavoriteTVShowById: GetFavoriteTVShowById,
    private val getInsertFavoriteTVShow: GetInsertFavoriteTVShow,
    private val getDeleteFavoriteTVShow: GetDeleteFavoriteTVShow
) : ViewModel(), DetailFavoriteContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _resultMovieById = MutableLiveData<MoviesDB>()
    val resultMovieById: LiveData<MoviesDB>
        get() = _resultMovieById

    private val _errorMovieById = MutableLiveData<String>()
    val errorMovieById: LiveData<String>
        get() = _errorMovieById

    private val _resultInsertMovie = MutableLiveData<Event<Status>>()
    val resultInsertMovie: LiveData<Event<Status>>
        get() = _resultInsertMovie

    private val _errorInsertMovie = MutableLiveData<String>()
    val errorInsertMovie: LiveData<String>
        get() = _errorInsertMovie

    private val _resultDeleteMovie = MutableLiveData<Event<Status>>()
    val resultDeleteMovie: LiveData<Event<Status>>
        get() = _resultDeleteMovie

    private val _errorDeleteMovie = MutableLiveData<String>()
    val errorDeleteMovie: LiveData<String>
        get() = _errorDeleteMovie

    private val _resultTVShowById = MutableLiveData<TVShowsDB>()
    val resultTVShowById: LiveData<TVShowsDB>
        get() = _resultTVShowById

    private val _errorTVShowById = MutableLiveData<String>()
    val errorTVShowById: LiveData<String>
        get() = _errorTVShowById

    private val _resultInsertTVShow = MutableLiveData<Event<Status>>()
    val resultInsertTVShow: LiveData<Event<Status>>
        get() = _resultInsertTVShow

    private val _errorInsertTVShow = MutableLiveData<String>()
    val errorInsertTVShow: LiveData<String>
        get() = _errorInsertTVShow

    private val _resultDeleteTVShow = MutableLiveData<Event<Status>>()
    val resultDeleteTVShow: LiveData<Event<Status>>
        get() = _resultDeleteTVShow

    private val _errorDeleteTVShow = MutableLiveData<String>()
    val errorDeleteTVShow: LiveData<String>
        get() = _errorDeleteTVShow

    override fun getFavoriteMovieByIdContract(movieId: Int) {
        _state.value = LoaderState.ShowLoading
        getFavoriteMovieById.execute(GetFavoriteMovieById.Params(movieId), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultMovieById.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorMovieById.postValue(it.message)
        })
    }

    override fun getInsertFavoriteMovieContract(movie: MoviesDB) =
        getInsertFavoriteMovie.execute(movie, onSuccess = {
            _resultInsertMovie.value = Event(Status.SUCCESS)
        }, onError = {
            _errorInsertMovie.postValue(it.message)
        })

    override fun getDeleteFavoriteMovieContract(movie: MoviesDB) =
        getDeleteFavoriteMovie.execute(movie, onSuccess = {
            _resultDeleteMovie.value = Event(Status.SUCCESS)
        }, onError = {
            _errorDeleteMovie.postValue(it.message)
        })

    override fun getFavoriteTVShowByIdContract(tvShowId: Int) {
        _state.value = LoaderState.ShowLoading
        getFavoriteTVShowById.execute(GetFavoriteTVShowById.Params(tvShowId), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultTVShowById.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorTVShowById.postValue(it.message)
        })
    }

    override fun getInsertFavoriteTVSHowContract(tvShow: TVShowsDB) =
        getInsertFavoriteTVShow.execute(tvShow, onSuccess = {
            _resultInsertTVShow.value = Event(Status.SUCCESS)
        }, onError = {
            _errorInsertTVShow.postValue(it.message)
        })

    override fun getDeleteFavoriteTVShowContract(tvShow: TVShowsDB) =
        getDeleteFavoriteTVShow.execute(tvShow, onSuccess = {
            _resultDeleteTVShow.value = Event(Status.SUCCESS)
        }, onError = {
            _errorDeleteTVShow.postValue(it.message)
        })

    override fun onCleared() {
        super.onCleared()
        getFavoriteMovieById.dispose()
        getInsertFavoriteMovie.dispose()
        getDeleteFavoriteMovie.dispose()
        getFavoriteTVShowById.dispose()
        getInsertFavoriteTVShow.dispose()
        getDeleteFavoriteTVShow.dispose()
    }
}
