package com.centaury.cataloguemovie.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.domain.UseCase
import com.centaury.domain.genre.interactor.GetGenreMovie
import com.centaury.domain.genre.interactor.GetGenreTVShow
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.search.interactor.GetAllSearchMovies
import com.centaury.domain.search.interactor.GetAllSearchTVShows
import com.centaury.domain.search.model.Search
import javax.inject.Inject

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/20/21.
 */
class SearchViewModel @Inject constructor(
    private val getAllSearchMovies: GetAllSearchMovies,
    private val getAllSearchTVShows: GetAllSearchTVShows,
    private val getGenreMovie: GetGenreMovie,
    private val getGenreTVShow: GetGenreTVShow
) : ViewModel(), SearchContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _resultSearchMovies = MutableLiveData<List<Search>>()
    val resultSearchMovies: LiveData<List<Search>>
        get() = _resultSearchMovies

    private val _errorSearchMovies = MutableLiveData<String>()
    val errorSearchMovies: LiveData<String>
        get() = _errorSearchMovies

    private val _resultSearchTVShows = MutableLiveData<List<Search>>()
    val resultSearchTVShows: LiveData<List<Search>>
        get() = _resultSearchTVShows

    private val _errorSearchTVShows = MutableLiveData<String>()
    val errorSearchTVShows: LiveData<String>
        get() = _errorSearchTVShows

    private val _resultGenreMovie = MutableLiveData<List<Genre>>()
    val resultGenreMovie: LiveData<List<Genre>>
        get() = _resultGenreMovie

    private val _errorGenreMovie = MutableLiveData<String>()
    val errorGenreMovie: LiveData<String>
        get() = _errorGenreMovie

    private val _resultGenreTVShow = MutableLiveData<List<Genre>>()
    val resultGenreTVShow: LiveData<List<Genre>>
        get() = _resultGenreTVShow

    private val _errorGenreTVShow = MutableLiveData<String>()
    val errorGenreTVShow: LiveData<String>
        get() = _errorGenreTVShow

    override fun getAllSearchMoviesContract(query: String) {
        _state.value = LoaderState.ShowLoading
        getAllSearchMovies.execute(GetAllSearchMovies.Params(query), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultSearchMovies.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorSearchMovies.postValue(it.message)
        })
    }

    override fun getAllSearchTVShowsContract(query: String) {
        _state.value = LoaderState.ShowLoading
        getAllSearchTVShows.execute(GetAllSearchTVShows.Params(query), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultSearchTVShows.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorSearchTVShows.postValue(it.message)
        })
    }

    override fun getGenreMovieContract() {
        _state.value = LoaderState.ShowLoading
        getGenreMovie.execute(UseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultGenreMovie.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorGenreMovie.postValue(it.message)
        })
    }

    override fun getGenreTVShowContract() {
        _state.value = LoaderState.ShowLoading
        getGenreTVShow.execute(UseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _resultGenreTVShow.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _errorGenreTVShow.postValue(it.message)
        })
    }
}