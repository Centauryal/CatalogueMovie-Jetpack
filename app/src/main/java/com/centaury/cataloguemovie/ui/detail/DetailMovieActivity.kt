package com.centaury.cataloguemovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.centaury.cataloguemovie.R
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity() {
    /*private DateFormat inputDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private DateFormat outputDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());

    private DetailMovieResponse detailMovieResponse;
    private DetailTVShowResponse detailTVShowResponse;
    private DetailMovieViewModel detailMovieViewModel;
    private String imageBaseUrl = BuildConfig.IMAGE_URL;
    private String sizeImage = AppConstants.SIZE_IMAGE;
    private int movieId;
    private int tvshowId;
    private Boolean isFavorite = false;*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
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

        /*detailMovieViewModel = obtainViewModel(this)
        val language = Locale.getDefault().toLanguageTag()
        mClData.setVisibility(View.GONE)
        mShimmerViewContainer.setVisibility(View.VISIBLE)
        mShimmerViewContainer.startShimmer()
        movieId = intent.getIntExtra(AppConstants.DETAIL_EXTRA_MOVIE, 0)
        if (movieId !== 0) {
            detailMovieViewModel.setMovieId(java.lang.String.valueOf(movieId))
            detailMovieViewModel.getDetailMovie(language).observe(this, { detailMovieResponse ->
                mShimmerViewContainer.stopShimmer()
                mShimmerViewContainer.setVisibility(View.GONE)
                mClData.setVisibility(View.VISIBLE)
                itemMovie(detailMovieResponse)
                detailMovieResponse = detailMovieResponse
                stateFavoriteMovie(movieId)
            })
        } else {
            tvshowId = intent.getIntExtra(AppConstants.DETAIL_EXTRA_TV_SHOW, 0)
            detailMovieViewModel.setTvshowId(java.lang.String.valueOf(tvshowId))
            detailMovieViewModel.getDetailTVShow(language).observe(this, { detailTVShowResponse ->
                mShimmerViewContainer.stopShimmer()
                mShimmerViewContainer.setVisibility(View.GONE)
                mClData.setVisibility(View.VISIBLE)
                itemTVShow(detailTVShowResponse)
                detailTVShowResponse = detailTVShowResponse
                stateFavoriteTVShow(tvshowId)
            })
        }
        setFavorite()
        mBtnFavorite.setScale(2.481f)*/
    }

    /*private fun itemMovie(movie: DetailMovieResponse) {
        mTxtTitledetail.setText(movie.getTitle())
        mTxtRatemovie.setText(java.lang.String.valueOf(movie.getVoteAverage()))
        val movieRating = (movie.getVoteAverage() / 2) as Float
        mRbRatingdetail.setRating(movieRating)
        if (movie.getGenres().size() === 0) {
            mTxtGenredetail.setText(resources.getString(R.string.txt_no_genre))
        } else {
            getGenresString(movie.getGenres())
        }
        if (movie.getOverview() == null || movie.getOverview().equals("")) {
            mTxtDescdetail.setText(resources.getString(R.string.txt_no_desc))
        } else {
            mTxtDescdetail.setText(movie.getOverview())
        }
        GlideApp.with(this)
            .load(imageBaseUrl + sizeImage + movie.getPosterPath())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(mIvImgdetail)
        if (movie.getBackdropPath() != null) {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + movie.getBackdropPath())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverdetail)
        } else {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + movie.getPosterPath())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverdetail)
        }
        try {
            val date: Date = inputDate.parse(movie.getReleaseDate())
            var releaseDate: String? = null
            if (date != null) {
                releaseDate = outputDate.format(date)
            }
            mTxtDatedetail.setText(releaseDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }*/

    /*private fun itemTVShow(tvshow: DetailTVShowResponse) {
        mTxtTitledetail.setText(tvshow.getName())
        mTxtRatemovie.setText(java.lang.String.valueOf(tvshow.getVoteAverage()))
        val movieRating = (tvshow.getVoteAverage() / 2) as Float
        mRbRatingdetail.setRating(movieRating)
        if (tvshow.getGenres().size() === 0) {
            mTxtGenredetail.setText(resources.getString(R.string.txt_no_genre))
        } else {
            getGenresString(tvshow.getGenres())
        }
        if (tvshow.getOverview() == null || tvshow.getOverview().equals("")) {
            mTxtDescdetail.setText(resources.getString(R.string.txt_no_desc))
        } else {
            mTxtDescdetail.setText(tvshow.getOverview())
        }
        GlideApp.with(this)
            .load(imageBaseUrl + sizeImage + tvshow.getPosterPath())
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
            .into(mIvImgdetail)
        if (tvshow.getBackdropPath() != null) {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + tvshow.getBackdropPath())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverdetail)
        } else {
            GlideApp.with(this)
                .load(imageBaseUrl + sizeImage + tvshow.getPosterPath())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvCoverdetail)
        }
        try {
            val date: Date = inputDate.parse(tvshow.getFirstAirDate())
            var releaseDate: String? = null
            if (date != null) {
                releaseDate = outputDate.format(date)
            }
            mTxtDatedetail.setText(releaseDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }*/

    public override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    public override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }

    /*private fun addFavorite() {
        if (movieId !== 0) {
            val movieEntity = MovieEntity(
                detailMovieResponse.getId(),
                mTxtTitledetail.getText().toString(),
                detailMovieResponse.getOriginalTitle(),
                detailMovieResponse.getPosterPath(),
                detailMovieResponse.getBackdropPath(),
                mTxtGenredetail.getText().toString(),
                detailMovieResponse.getReleaseDate(),
                mTxtDescdetail.getText().toString(),
                detailMovieResponse.getVoteAverage()
            )
            detailMovieViewModel.insertFavoriteMovie(movieEntity)
        } else {
            val tvShowEntity = TVShowEntity(
                detailTVShowResponse.getId(),
                mTxtTitledetail.getText().toString(),
                detailTVShowResponse.getOriginalName(),
                detailTVShowResponse.getPosterPath(),
                detailTVShowResponse.getBackdropPath(),
                mTxtGenredetail.getText().toString(),
                detailTVShowResponse.getFirstAirDate(),
                mTxtDescdetail.getText().toString(),
                detailTVShowResponse.getVoteAverage()
            )
            detailMovieViewModel.insertFavoriteTVShow(tvShowEntity)
        }
        Toast.makeText(this, getString(R.string.txt_movie_add), Toast.LENGTH_SHORT).show()
    }

    private fun removeFavorite(id: Int) {
        if (movieId !== 0) {
            val movieEntity: MovieEntity
            try {
                movieEntity = detailMovieViewModel.getDetailFavMovie(id)
                detailMovieViewModel.deleteFavoriteMovie(movieEntity)
            } catch (e: ExecutionException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        } else {
            val tvShowEntity: TVShowEntity
            try {
                tvShowEntity = detailMovieViewModel.getDetailFavTVShow(id)
                detailMovieViewModel.deleteFavoriteTVShow(tvShowEntity)
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
            movieEntity = detailMovieViewModel.getDetailFavMovie(id)
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
            tvShowEntity = detailMovieViewModel.getDetailFavTVShow(id)
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
                if (movieId !== 0) {
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