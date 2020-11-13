package com.centaury.cataloguemovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.Status
import com.centaury.domain.detail.interactor.GetDetailMovie
import com.centaury.domain.detail.interactor.GetDetailTVShow
import com.centaury.domain.detail.model.Detail
import com.centaury.domain.movies.interactor.GetDeleteFavoriteMovie
import com.centaury.domain.movies.interactor.GetFavoriteMovieById
import com.centaury.domain.movies.interactor.GetInsertFavoriteMovie
import com.centaury.domain.movies.model.MoviesEntity
import com.centaury.domain.tvshow.interactor.GetDeleteFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetFavoriteTVShowById
import com.centaury.domain.tvshow.interactor.GetInsertFavoriteTVShow
import com.centaury.domain.tvshow.model.TVShowsEntity
import javax.inject.Inject

/**
 * Created by Centaury on 10/7/2019.
 */
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovie: GetDetailMovie,
    private val getDetailTVShow: GetDetailTVShow,
    private val getFavoriteMovieById: GetFavoriteMovieById,
    private val getInsertFavoriteMovie: GetInsertFavoriteMovie,
    private val getDeleteFavoriteMovie: GetDeleteFavoriteMovie,
    private val getFavoriteTVShowById: GetFavoriteTVShowById,
    private val getInsertFavoriteTVShow: GetInsertFavoriteTVShow,
    private val getDeleteFavoriteTVShow: GetDeleteFavoriteTVShow
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

    private val _resultMovieById = MutableLiveData<MoviesEntity>()
    val resultMovieById: LiveData<MoviesEntity>
        get() = _resultMovieById

    private val _errorMovieById = MutableLiveData<String>()
    val errorMovieById: LiveData<String>
        get() = _errorMovieById

    private val _resultInsertMovie = MutableLiveData<Status>()
    val resultInsertMovie: LiveData<Status>
        get() = _resultInsertMovie

    private val _errorInsertMovie = MutableLiveData<String>()
    val errorInsertMovie: LiveData<String>
        get() = _errorInsertMovie

    private val _resultDeleteMovie = MutableLiveData<Status>()
    val resultDeleteMovie: LiveData<Status>
        get() = _resultDeleteMovie

    private val _errorDeleteMovie = MutableLiveData<String>()
    val errorDeleteMovie: LiveData<String>
        get() = _errorDeleteMovie

    private val _resultTVShowById = MutableLiveData<TVShowsEntity>()
    val resultTVShowById: LiveData<TVShowsEntity>
        get() = _resultTVShowById

    private val _errorTVShowById = MutableLiveData<String>()
    val errorTVShowById: LiveData<String>
        get() = _errorTVShowById

    private val _resultInsertTVShow = MutableLiveData<Status>()
    val resultInsertTVShow: LiveData<Status>
        get() = _resultInsertTVShow

    private val _errorInsertTVShow = MutableLiveData<String>()
    val errorInsertTVShow: LiveData<String>
        get() = _errorInsertTVShow

    private val _resultDeleteTVShow = MutableLiveData<Status>()
    val resultDeleteTVShow: LiveData<Status>
        get() = _resultDeleteTVShow

    private val _errorDeleteTVShow = MutableLiveData<String>()
    val errorDeleteTVShow: LiveData<String>
        get() = _errorDeleteTVShow

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

    override fun getFavoriteMovieByIdContract(movieId: Int) {
        getFavoriteMovieById.execute(GetFavoriteMovieById.Params(movieId), onSuccess = {
            _resultMovieById.postValue(it)
        }, onError = {
            _errorMovieById.postValue(it.message)
        })
    }

    override fun getInsertFavoriteMovieContract(movie: MoviesEntity) {
        getInsertFavoriteMovie.execute(movie, onSuccess = {
            _resultInsertMovie.value = Status.SUCCESS
        }, onError = {
            _errorInsertMovie.postValue(it.message)
        })
    }

    override fun getDeleteFavoriteMovieContract(movie: MoviesEntity) {
        getDeleteFavoriteMovie.execute(movie, onSuccess = {
            _resultDeleteMovie.value = Status.SUCCESS
        }, onError = {
            _errorDeleteMovie.postValue(it.message)
        })
    }

    override fun getFavoriteTVShowByIdContract(tvShowId: Int) {
        getFavoriteTVShowById.execute(GetFavoriteTVShowById.Params(tvShowId), onSuccess = {
            _resultTVShowById.postValue(it)
        }, onError = {
            _errorTVShowById.postValue(it.message)
        })
    }

    override fun getInsertFavoriteTVSHowContract(tvShow: TVShowsEntity) {
        getInsertFavoriteTVShow.execute(tvShow, onSuccess = {
            _resultInsertTVShow.value = Status.SUCCESS
        }, onError = {
            _errorInsertTVShow.postValue(it.message)
        })
    }

    override fun getDeleteFavoriteTVShowContract(tvShow: TVShowsEntity) {
        getDeleteFavoriteTVShow.execute(tvShow, onSuccess = {
            _resultDeleteTVShow.value = Status.SUCCESS
        }, onError = {
            _errorDeleteTVShow.postValue(it.message)
        })
    }

}