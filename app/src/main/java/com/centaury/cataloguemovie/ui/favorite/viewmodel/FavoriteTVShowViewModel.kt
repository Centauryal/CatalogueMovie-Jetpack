package com.centaury.cataloguemovie.ui.favorite.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.showToast
import com.centaury.domain.FlowableUseCase
import com.centaury.domain.tvshow.interactor.GetAllFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetDeleteFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetFavoriteTVShowById
import com.centaury.domain.tvshow.model.TVShowsEntity
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

    private val _result = MutableLiveData<List<TVShowsEntity>>()
    val result: LiveData<List<TVShowsEntity>>
        get() = _result

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _resultTVShowById = MutableLiveData<TVShowsEntity>()
    val resultTVShowById: LiveData<TVShowsEntity>
        get() = _resultTVShowById

    private val _errorTVShowById = MutableLiveData<String>()
    val errorTVShowById: LiveData<String>
        get() = _errorTVShowById

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

    override fun getFavoriteTVShowByIdContract(tvShowId: Int) {
        getFavoriteTVShowById.execute(GetFavoriteTVShowById.Params(tvShowId), onSuccess = {
            _resultTVShowById.postValue(it)
        }, onError = {
            _errorTVShowById.postValue(it.message)
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
