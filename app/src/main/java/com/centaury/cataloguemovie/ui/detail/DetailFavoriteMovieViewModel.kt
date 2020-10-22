package com.centaury.cataloguemovie.ui.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.showToast
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

    private val _resultMovieById = MutableLiveData<MoviesEntity>()
    val resultMovieById: LiveData<MoviesEntity>
        get() = _resultMovieById

    private val _errorMovieById = MutableLiveData<String>()
    val errorMovieById: LiveData<String>
        get() = _errorMovieById

    private val _errorInsertMovie = MutableLiveData<String>()
    val errorInsertMovie: LiveData<String>
        get() = _errorInsertMovie

    private val _errorDeleteMovie = MutableLiveData<String>()
    val errorDeleteMovie: LiveData<String>
        get() = _errorDeleteMovie

    private val _resultTVShowById = MutableLiveData<TVShowsEntity>()
    val resultTVShowById: LiveData<TVShowsEntity>
        get() = _resultTVShowById

    private val _errorTVShowById = MutableLiveData<String>()
    val errorTVShowById: LiveData<String>
        get() = _errorTVShowById

    private val _errorInsertTVShow = MutableLiveData<String>()
    val errorInsertTVShow: LiveData<String>
        get() = _errorInsertTVShow

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

    override fun getInsertFavoriteMovieContract(context: Context, movie: MoviesEntity) {
        getInsertFavoriteMovie.execute(movie, onSuccess = {
            context.showToast(R.string.txt_movie_add)
        }, onError = {
            _errorInsertMovie.postValue(it.message)
        })
    }

    override fun getDeleteFavoriteMovieContract(context: Context, movie: MoviesEntity) {
        getDeleteFavoriteMovie.execute(movie, onSuccess = {
            context.showToast(R.string.txt_movie_remove)
        }, onError = {
            _errorDeleteMovie.postValue(it.message)
        })
    }

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

    override fun getInsertFavoriteTVSHowContract(context: Context, tvShow: TVShowsEntity) {
        getInsertFavoriteTVShow.execute(tvShow, onSuccess = {
            context.showToast(R.string.txt_movie_add)
        }, onError = {
            _errorInsertTVShow.postValue(it.message)
        })
    }

    override fun getDeleteFavoriteTVShowContract(context: Context, tvShow: TVShowsEntity) {
        getDeleteFavoriteTVShow.execute(tvShow, onSuccess = {
            context.showToast(R.string.txt_movie_remove)
        }, onError = {
            _errorDeleteTVShow.postValue(it.message)
        })
    }

}
