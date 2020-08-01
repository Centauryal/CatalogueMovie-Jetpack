package com.centaury.cataloguemovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerDetailComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.detail.model.Detail
import com.centaury.domain.movies.model.MoviesEntity
import com.centaury.domain.tvshow.model.TVShowsEntity
import kotlinx.android.synthetic.main.activity_detail_movie.*
import java.text.ParseException
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailMovieViewModel: DetailMovieViewModel

    private var movie: MoviesEntity? = null
    private var tvShow: TVShowsEntity? = null
    private var isFavorite: Boolean = false
    private var movieId: Int? = null
    private var tvShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = getColor(R.color.colorPrimary)
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
        initClick()
        initView()
    }

    private fun initView() {
        detailMovieViewModel =
            ViewModelProvider(this, viewModelFactory)[DetailMovieViewModel::class.java]

        cl_data.gone()

        initParam()
        initObserver()

        setFavorite()
        btn_favorite.scale = 2.481f
    }

    private fun initParam() {
        movieId = intent.getIntExtra(DETAIL_EXTRA_MOVIE, 0)
        if (movieId != 0) {
            detailMovieViewModel.getFavoriteMovieByIdContract(movieId!!)
            detailMovieViewModel.getDetailMovieContract(movieId!!)
            stateFavorite()
        } else {
            tvShowId = intent.getIntExtra(DETAIL_EXTRA_TV_SHOW, 0)
            detailMovieViewModel.getFavoriteTVShowByIdContract(tvShowId!!)
            detailMovieViewModel.getDetailTVShowContract(tvShowId!!)
            stateFavorite()
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

        detailMovieViewModel.resultMovieById.observe(this, Observer { movieById ->
            movie = movieById
        })

        detailMovieViewModel.errorMovieById.observe(this, Observer { errorMovieById ->
            showToast(errorMovieById)
        })

        detailMovieViewModel.resultTVShowById.observe(this, Observer { tvShowById ->
            tvShow = tvShowById
        })

        detailMovieViewModel.errorTVShowById.observe(this, Observer { errorTVShowById ->
            showToast(errorTVShowById)
        })

        detailMovieViewModel.errorInsertMovie.observe(this, Observer { errorInsertMovie ->
            showToast(errorInsertMovie)
        })

        detailMovieViewModel.errorInsertTVShow.observe(this, Observer { errorInsertTVShow ->
            showToast(errorInsertTVShow)
        })

        detailMovieViewModel.errorDeleteMovie.observe(this, Observer { errorDeleteMovie ->
            showToast(errorDeleteMovie)
        })

        detailMovieViewModel.errorDeleteTVShow.observe(this, Observer { errorDeleteTVShow ->
            showToast(errorDeleteTVShow)
        })
    }

    private fun initClick() {
        btn_favorite.setOnClickListener {
            if (isFavorite) {
                if (movieId != 0) {
                    removeFavorite(movie)
                } else {
                    removeFavorite(null, tvShow)
                }
            } else {
                addFavorite(movie, tvShow)
            }

            isFavorite = !isFavorite
            setFavorite()
            btn_favorite.playAnimation()
        }
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

    private fun addFavorite(movie: MoviesEntity? = null, tvShow: TVShowsEntity? = null) {
        if (movieId != 0) {
            movie?.let {
                val movieEntity = MoviesEntity(
                    it.id,
                    it.title,
                    it.titleBackground,
                    it.image,
                    txt_genre_detail.text.toString(),
                    it.overview,
                    it.date
                )
                detailMovieViewModel.getInsertFavoriteMovieContract(this, movieEntity)
            }
        } else {
            tvShow?.let {
                val tvShowEntity = TVShowsEntity(
                    it.id,
                    it.title,
                    it.titleBackground,
                    it.image,
                    txt_genre_detail.text.toString(),
                    it.overview,
                    it.date
                )
                detailMovieViewModel.getInsertFavoriteTVSHowContract(this, tvShowEntity)
            }
        }
    }

    private fun removeFavorite(movie: MoviesEntity? = null, tvShow: TVShowsEntity? = null) {
        if (movieId != 0) {
            movie?.let {
                detailMovieViewModel.getDeleteFavoriteMovieContract(this, it)
            }
        } else {
            tvShow?.let {
                detailMovieViewModel.getDeleteFavoriteTVShowContract(this, it)
            }
        }

    }

    private fun stateFavorite() {
        if (movieId != 0) {
            movie?.let {
                isFavorite = true
            }
            setFavorite()
        } else {
            tvShow?.let {
                isFavorite = true
            }
            setFavorite()
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            btn_favorite.speed = 1f
            btn_favorite.progress = 1f
        } else {
            btn_favorite.progress = 0f
            btn_favorite.speed = -2f
        }
    }

    companion object {
        const val DETAIL_EXTRA_MOVIE = "extra_movie"
        const val DETAIL_EXTRA_TV_SHOW = "extra_tv_show"

    }
}