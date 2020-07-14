package com.centaury.cataloguemovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.centaury.cataloguemovie.R
import kotlinx.android.synthetic.main.activity_detail_favorite_movie.*

class DetailFavoriteMovieActivity : AppCompatActivity() {
    /*private var movieEntity: MovieEntity? = null
    private var tvShowEntity: TVShowEntity? = null
    private var detailFavoriteMovieViewModel: DetailFavoriteMovieViewModel? = null
    private val imageBaseUrl: String = BuildConfig.IMAGE_URL
    private val sizeImage = AppConstants.SIZE_IMAGE
    private var movieId = 0
    private var tvshowId = 0
    private var isFavorite = false*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite_movie)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val view = window.decorView
            view.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        /*detailFavoriteMovieViewModel = obtainViewModel(this)
        movieId = intent.getIntExtra(AppConstants.DETAIL_EXTRA_FAV_MOVIE, 0)
        mClFavData.setVisibility(View.GONE)
        mShimmerViewContainer.setVisibility(View.VISIBLE)
        mShimmerViewContainer.startShimmer()
        if (movieId != 0) {
            mShimmerViewContainer.stopShimmer()
            mShimmerViewContainer.setVisibility(View.GONE)
            mClFavData.setVisibility(View.VISIBLE)
            try {
                movieEntity = detailFavoriteMovieViewModel.getDetailFavMovie(movieId)
                itemMovie(movieEntity)
                stateFavoriteMovie(movieId)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        } else {
            tvshowId = intent.getIntExtra(AppConstants.DETAIL_EXTRA_FAV_TV_SHOW, 0)
            mShimmerViewContainer.stopShimmer()
            mShimmerViewContainer.setVisibility(View.GONE)
            mClFavData.setVisibility(View.VISIBLE)
            try {
                tvShowEntity = detailFavoriteMovieViewModel.getDetailFavTVShow(tvshowId)
                itemTVShow(tvShowEntity)
                stateFavoriteTVShow(tvshowId)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        setFavorite()
        mBtnFavorite.setScale(2.481f)*/
    }

    /*private fun itemMovie(movie: MovieEntity?) {
        mTxtTitleFavdetail.setText(movie!!.name)
        mTxtRateFavmovie.setText(movie.voteAverage.toString())
        val movieRating = (movie.voteAverage / 2).toFloat()
        mRbRatingFavdetail.setRating(movieRating)
        if (movie.genres == null || movie.genres == "") {
            mTxtGenreFavdetail.setText(resources.getString(R.string.txt_no_genre))
        } else {
            mTxtGenreFavdetail.setText(movie.genres)
        }
        if (movie.overview == null || movie.overview == "") {
            mTxtDescFavdetail.setText(resources.getString(R.string.txt_no_desc))
        } else {
            mTxtDescFavdetail.setText(movie.overview)
        }
        GlideApp.with(this)
            .load(imageBaseUrl + sizeImage + movie.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(mIvImgFavdetail)
        if (movie.backdropPath != null) {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + movie.backdropPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverFavdetail)
        } else {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + movie.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverFavdetail)
        }
        try {
            val date: Date = inputDate.parse(movie.releaseDate)
            var releaseDate: String? = null
            if (date != null) {
                releaseDate = outputDate.format(date)
            }
            mTxtDateFavdetail.setText(releaseDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }*/

    /*private fun itemTVShow(tvshow: TVShowEntity?) {
        mTxtTitleFavdetail.setText(tvshow!!.name)
        mTxtRateFavmovie.setText(tvshow.voteAverage.toString())
        val movieRating = (tvshow.voteAverage / 2).toFloat()
        mRbRatingFavdetail.setRating(movieRating)
        if (tvshow.genres == null || tvshow.genres == "") {
            mTxtGenreFavdetail.setText(resources.getString(R.string.txt_no_genre))
        } else {
            mTxtGenreFavdetail.setText(tvshow.genres)
        }
        if (tvshow.overview == null || tvshow.overview == "") {
            mTxtDescFavdetail.setText(resources.getString(R.string.txt_no_desc))
        } else {
            mTxtDescFavdetail.setText(tvshow.overview)
        }
        GlideApp.with(this)
            .load(imageBaseUrl + sizeImage + tvshow.posterPath)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(mIvImgFavdetail)
        if (tvshow.backdropPath != null) {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + tvshow.backdropPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverFavdetail)
        } else {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + tvshow.posterPath)
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverFavdetail)
        }
        try {
            val date: Date = inputDate.parse(tvshow.releaseDate)
            var releaseDate: String? = null
            if (date != null) {
                releaseDate = outputDate.format(date)
            }
            mTxtDateFavdetail.setText(releaseDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    public override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    public override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }

    /*private fun addFavorite() {
        if (movieId != 0) {
            val entity = MovieEntity(
                movieEntity!!.movieId,
                mTxtTitleFavdetail.getText().toString(),
                movieEntity!!.originalName,
                movieEntity!!.posterPath,
                movieEntity!!.backdropPath,
                mTxtGenreFavdetail.getText().toString(),
                movieEntity!!.releaseDate,
                mTxtDescFavdetail.getText().toString(),
                movieEntity!!.voteAverage
            )
            detailFavoriteMovieViewModel.insertFavoriteMovie(entity)
        } else {
            val entity = TVShowEntity(
                tvShowEntity!!.tvshowId,
                mTxtTitleFavdetail.getText().toString(),
                tvShowEntity!!.originalName,
                tvShowEntity!!.posterPath,
                tvShowEntity!!.backdropPath,
                mTxtGenreFavdetail.getText().toString(),
                tvShowEntity!!.releaseDate,
                mTxtDescFavdetail.getText().toString(),
                tvShowEntity!!.voteAverage
            )
            detailFavoriteMovieViewModel.insertFavoriteTVShow(entity)
        }
        Toast.makeText(this, getString(R.string.txt_movie_add), Toast.LENGTH_SHORT).show()
    }

    private fun removeFavorite(id: Int) {
        if (movieId != 0) {
            val movieEntity: MovieEntity
            try {
                movieEntity = detailFavoriteMovieViewModel.getDetailFavMovie(id)
                detailFavoriteMovieViewModel.deleteFavoriteMovie(movieEntity)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        } else {
            val tvShowEntity: TVShowEntity
            try {
                tvShowEntity = detailFavoriteMovieViewModel.getDetailFavTVShow(id)
                detailFavoriteMovieViewModel.deleteFavoriteTVShow(tvShowEntity)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        Toast.makeText(this, getString(R.string.txt_movie_remove), Toast.LENGTH_SHORT).show()
    }

    private fun stateFavoriteMovie(id: Int) {
        val movieEntity: MovieEntity
        try {
            movieEntity = detailFavoriteMovieViewModel.getDetailFavMovie(id)
            if (movieEntity != null) {
                isFavorite = true
            }
            setFavorite()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun stateFavoriteTVShow(id: Int) {
        val tvShowEntity: TVShowEntity
        try {
            tvShowEntity = detailFavoriteMovieViewModel.getDetailFavTVShow(id)
            if (tvShowEntity != null) {
                isFavorite = true
            }
            setFavorite()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            mBtnFavorite.setSpeed(1f)
            mBtnFavorite.setProgress(1f)
        } else {
            mBtnFavorite.setProgress(0f)
            mBtnFavorite.setSpeed(-2f)
        }
    }

    @OnClick(R.id.btn_favorite)
    fun onClick(v: View) {
        if (v.id == R.id.btn_favorite) {
            if (isFavorite) {
                if (movieId != 0) {
                    removeFavorite(movieId)
                } else {
                    removeFavorite(tvshowId)
                }
            } else {
                addFavorite()
            }
            isFavorite = !isFavorite
            setFavorite()
            mBtnFavorite.playAnimation()
        }
    }*/
}