package com.centaury.cataloguemovie.ui.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import com.centaury.domain.movies.interactor.GetAllFavoriteMovie
import com.centaury.domain.movies.interactor.GetDeleteFavoriteMovie
import com.centaury.domain.movies.interactor.GetFavoriteMovieById
import com.centaury.domain.movies.interactor.GetInsertFavoriteMovie
import com.centaury.domain.movies.model.MoviesEntity
import com.centaury.domain.tvshow.interactor.GetAllFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetDeleteFavoriteTVShow
import com.centaury.domain.tvshow.interactor.GetFavoriteTVShowById
import com.centaury.domain.tvshow.interactor.GetInsertFavoriteTVShow
import com.centaury.domain.tvshow.model.TVShowsEntity
import javax.inject.Inject

/**
 * Created by Centaury on 11/27/2019.
 */
class DetailFavoriteMovieViewModel @Inject constructor(
    private val getAllFavoriteMovie: GetAllFavoriteMovie,
    private val getFavoriteMovieById: GetFavoriteMovieById,
    private val getInsertFavoriteMovie: GetInsertFavoriteMovie,
    private val getDeleteFavoriteMovie: GetDeleteFavoriteMovie,
    private val getAllFavoriteTVShow: GetAllFavoriteTVShow,
    private val getFavoriteTVShowById: GetFavoriteTVShowById,
    private val getInsertFavoriteTVShow: GetInsertFavoriteTVShow,
    private val getDeleteFavoriteTVShow: GetDeleteFavoriteTVShow
) : ViewModel(), DetailFavoriteContract {

    override fun getFavoriteMovieByIdContract(movieId: Int) {
    }

    override fun getInsertFavoriteMovieContract(context: Context, movie: MoviesEntity) {
    }

    override fun getDeleteFavoriteMovieContract(context: Context, movie: MoviesEntity) {
    }

    override fun getFavoriteTVShowByIdContract(tvShowId: Int) {
    }

    override fun getInsertFavoriteTVSHowContract(context: Context, tvShow: TVShowsEntity) {
    }

    override fun getDeleteFavoriteTVShowContract(context: Context, tvShow: TVShowsEntity) {
    }

}
