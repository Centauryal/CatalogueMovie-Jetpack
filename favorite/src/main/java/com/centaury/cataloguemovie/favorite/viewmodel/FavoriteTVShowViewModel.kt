package com.centaury.cataloguemovie.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.utils.Event
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.Status
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.model.TVShowsDB
import com.centaury.domain.tvshow.interactor.GetAllFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetDeleteFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetFavoriteTVShowById
import javax.inject.Inject

/**
 * Created by Centaury on 11/23/2019.
 */
class FavoriteTVShowViewModel @Inject constructor(
    private val getAllFavoriteTVShow: GetAllFavoriteTVShow,
    private val getFavoriteTVShowById: GetFavoriteTVShowById,
    private val getDeleteFavoriteTVShow: GetDeleteFavoriteTVShow
) : ViewModel(), FavoriteTVShowContract {

    private val _state = MutableLiveData<LoaderState>()
    val state: LiveData<LoaderState>
        get() = _state

    private val _result = MutableLiveData<List<TVShowsDB>>()
    val result: LiveData<List<TVShowsDB>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _resultTVShowById = MutableLiveData<TVShowsDB>()
    val resultTVShowById: LiveData<TVShowsDB>
        get() = _resultTVShowById

    private val _errorTVShowById = MutableLiveData<String>()
    val errorTVShowById: LiveData<String>
        get() = _errorTVShowById

    private val _resultDeleteTVShow = MutableLiveData<Event<Status>>()
    val resultDeleteTVShow: LiveData<Event<Status>>
        get() = _resultDeleteTVShow

    private val _errorDeleteTVShow = MutableLiveData<String>()
    val errorDeleteTVShow: LiveData<String>
        get() = _errorDeleteTVShow

    init {
        getAllFavoriteTVShowContract()
    }

    override fun getAllFavoriteTVShowContract() {
        _state.value = LoaderState.ShowLoading
        getAllFavoriteTVShow.execute(FlowableUseCase.None(), onSuccess = {
            _state.value = LoaderState.HideLoading
            _result.postValue(it)
        }, onError = {
            _state.value = LoaderState.HideLoading
            _error.postValue(it.message)
        })
    }

    override fun getFavoriteTVShowByIdContract(tvShowId: Int) =
        getFavoriteTVShowById.execute(GetFavoriteTVShowById.Params(tvShowId), onSuccess = {
            _resultTVShowById.postValue(it)
        }, onError = {
            _errorTVShowById.postValue(it.message)
        })

    override fun getDeleteFavoriteTVShowContract(tvShow: TVShowsDB) =
        getDeleteFavoriteTVShow.execute(tvShow, onSuccess = {
            _resultDeleteTVShow.value = Event(Status.SUCCESS)
        }, onError = {
            _errorDeleteTVShow.postValue(it.message)
        })

    override fun onCleared() {
        super.onCleared()
        getFavoriteTVShowById.dispose()
        getAllFavoriteTVShow.dispose()
        getDeleteFavoriteTVShow.dispose()
    }
}
