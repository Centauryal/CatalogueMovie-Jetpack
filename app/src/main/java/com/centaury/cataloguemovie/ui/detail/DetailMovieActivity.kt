package com.centaury.cataloguemovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerDetailComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.detail.model.Detail
import kotlinx.android.synthetic.main.activity_detail_movie.*
import java.text.ParseException
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailMovieViewModel: DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val view = window.decorView
            view.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initInjector()
        initView()

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

    private fun initView() {
        detailMovieViewModel =
            ViewModelProvider(this, viewModelFactory)[DetailMovieViewModel::class.java]

        initParam()
        initObserver()

        cl_data.gone()
        btn_favorite.scale = 2.481f
    }

    private fun initParam() {
        val movieId = intent.getIntExtra(DETAIL_EXTRA_MOVIE, 0)
        if (movieId != 0) {
            detailMovieViewModel.getDetailMovieContract(movieId)
        } else {
            val tvShowId = intent.getIntExtra(DETAIL_EXTRA_TV_SHOW, 0)
            detailMovieViewModel.getDetailTVShowContract(tvShowId)
        }
    }

    private fun initObserver() {
        detailMovieViewModel.state.observe(this, Observer { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    shimmer_view_container.startShimmer()
                    shimmer_view_container.visible()
                }
                is LoaderState.HideLoading -> {
                    cl_data.visible()
                    shimmer_view_container.stopShimmer()
                    shimmer_view_container.gone()
                }
            }
        })

        detailMovieViewModel.resultMovie.observe(this, Observer { resultMovie ->
            showDetail(resultMovie)
        })

        detailMovieViewModel.errorMovie.observe(this, Observer { errorMovie ->
            showToast(errorMovie)
        })

        detailMovieViewModel.resultTVShow.observe(this, Observer { resultTVShow ->
            showDetail(resultTVShow)
        })

        detailMovieViewModel.errorTVShow.observe(this, Observer { errorTVShow ->
            showToast(errorTVShow)
        })
    }

    private fun showDetail(detail: Detail) {
        txt_title_detail.text = detail.title
        txt_rate_movie.text = detail.rating.toString()
        val rating = (detail.rating / 2).toFloat()
        rb_rating_detail.rating = rating

        if (detail.genre.isEmpty()) {
            txt_genre_detail.text = getString(R.string.txt_no_genre)
        } else {
            CommonUtils.getGenresString(detail.genre, txt_genre_detail)
        }

        if (detail.overview.isEmpty() || detail.overview == "") {
            txt_desc_detail.text = getString(R.string.txt_no_desc)
        } else {
            txt_desc_detail.text = detail.overview
        }

        loadFromUrl(
            iv_img_detail,
            detail.image,
            R.drawable.ic_loading,
            R.drawable.ic_error
        )

        if (detail.imageBackground != "null") {
            loadFromUrl(
                iv_cover_detail,
                detail.imageBackground,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )
        } else {
            loadFromUrl(
                iv_cover_detail,
                detail.image,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )
        }

        try {
            val date = CommonUtils.inputDate().parse(detail.date)
            var releaseDate: String
            date.let {
                releaseDate = CommonUtils.outputDate().format(it)
            }
            txt_date_detail.text = releaseDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun initInjector() {
        DaggerDetailComponent.builder()
            .appComponent((application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

    public override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    public override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }

    companion object {
        const val DETAIL_EXTRA_MOVIE = "extra_movie"
        const val DETAIL_EXTRA_TV_SHOW = "extra_tv_show"

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