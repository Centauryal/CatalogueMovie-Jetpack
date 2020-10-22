package com.centaury.cataloguemovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerDetailFavoriteComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.detail.model.Detail
import com.centaury.domain.movies.model.MoviesEntity
import com.centaury.domain.tvshow.model.TVShowsEntity
import kotlinx.android.synthetic.main.activity_detail_favorite_movie.*
import java.text.ParseException
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailFavoriteMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var detailFavoriteMovieViewModel: DetailFavoriteMovieViewModel

    private var movie: MoviesEntity? = null
    private var tvShow: TVShowsEntity? = null
    private var detailFavorite: Detail? = null
    private var isFavorite: Boolean = false
    private var movieId: Int? = null
    private var tvShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorite_movie)
        showSystemUI()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = getColor(R.color.colorPrimary)
        }

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayShowTitleEnabled(false)
        }

        initInjector()
        initClick()
        initView()
    }

    private fun initView() {
        detailFavoriteMovieViewModel =
            ViewModelProvider(this, viewModelFactory)[DetailFavoriteMovieViewModel::class.java]

        cl_fav_data.gone()

        initParam()
        initObserver()

        setFavorite()
        btn_favorite.scale = 2.481f
    }

    private fun initParam() {
        movieId = intent.getIntExtra(DETAIL_EXTRA_FAV_MOVIE, 0)
        if (movieId != 0) {
            movieId?.let { detailFavoriteMovieViewModel.getFavoriteMovieByIdContract(it) }
        } else {
            tvShowId = intent.getIntExtra(DETAIL_EXTRA_FAV_TV_SHOW, 0)
            tvShowId?.let { detailFavoriteMovieViewModel.getFavoriteTVShowByIdContract(it) }
        }
    }

    private fun initObserver() {
        detailFavoriteMovieViewModel.state.observe(this, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    shimmer_view_container.startShimmer()
                    shimmer_view_container.visible()
                }
                is LoaderState.HideLoading -> {
                    cl_fav_data.visible()
                    shimmer_view_container.stopShimmer()
                    shimmer_view_container.gone()
                }
            }
        })

        detailFavoriteMovieViewModel.resultMovieById.observe(this, { movieById ->
            movie = movieById
            showDetail(DetailMapper.mapperMovieToDetail(movieById))
            detailFavorite = DetailMapper.mapperMovieToDetail(movieById)
            stateFavorite()
        })

        detailFavoriteMovieViewModel.errorMovieById.observe(this, { errorMovieById ->
            showToast(errorMovieById)
        })

        detailFavoriteMovieViewModel.resultTVShowById.observe(this, { tvShowById ->
            tvShow = tvShowById
            showDetail(DetailMapper.mapperTVShowToDetail(tvShowById))
            detailFavorite = DetailMapper.mapperTVShowToDetail(tvShowById)
            stateFavorite()
        })

        detailFavoriteMovieViewModel.errorTVShowById.observe(this, { errorTVShowById ->
            showToast(errorTVShowById)
        })

        detailFavoriteMovieViewModel.errorInsertMovie.observe(this, { errorInsertMovie ->
            showToast(errorInsertMovie)
        })

        detailFavoriteMovieViewModel.errorInsertTVShow.observe(this, { errorInsertTVShow ->
            showToast(errorInsertTVShow)
        })

        detailFavoriteMovieViewModel.errorDeleteMovie.observe(this, { errorDeleteMovie ->
            showToast(errorDeleteMovie)
        })

        detailFavoriteMovieViewModel.errorDeleteTVShow.observe(this, { errorDeleteTVShow ->
            showToast(errorDeleteTVShow)
        })
    }

    private fun initClick() {
        btn_favorite.setOnClickListener {
            clickFavorite()
        }
    }

    private fun clickFavorite() {
        if (isFavorite) {
            if (movieId != 0) {
                removeFavorite(movie)
            } else {
                removeFavorite(null, tvShow)
            }
        } else {
            detailFavorite?.let { detail -> addFavorite(detail) }
        }

        isFavorite = !isFavorite
        setFavorite()
        btn_favorite.playAnimation()
    }

    private fun showDetail(detail: Detail) {
        txt_title_fav_detail.text = detail.title
        txt_rate_fav_movie.text = detail.rating.toString()
        val rating = (detail.rating / 2).toFloat()
        rb_rating_fav_detail.rating = rating

        if (detail.genre.isEmpty()) {
            txt_genre_fav_detail.text = getString(R.string.txt_no_genre)
        } else {
            CommonUtils.getGenresString(detail.genre, txt_genre_fav_detail)
        }

        if (detail.overview.isEmpty() || detail.overview == "") {
            txt_desc_fav_detail.text = getString(R.string.txt_no_desc)
        } else {
            txt_desc_fav_detail.text = detail.overview
        }

        loadFromUrl(
            iv_img_fav_detail,
            detail.image,
            R.drawable.ic_loading,
            R.drawable.ic_error
        )

        if (detail.imageBackground != "null") {
            loadFromUrl(
                iv_cover_fav_detail,
                detail.imageBackground,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )
        } else {
            loadFromUrl(
                iv_cover_fav_detail,
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
            txt_date_fav_detail.text = releaseDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun initInjector() {
        DaggerDetailFavoriteComponent.builder()
            .appComponent((application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

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

    private fun addFavorite(detailFavorite: Detail) {
        if (movieId != 0) {
            detailFavorite.let {
                val movieEntity = MoviesEntity(
                    it.id,
                    it.title,
                    it.originalTitle,
                    it.image,
                    it.imageBackground,
                    txt_genre_fav_detail.text.toString(),
                    txt_rate_fav_movie.text.toString(),
                    it.date,
                    it.overview
                )
                detailFavoriteMovieViewModel.getInsertFavoriteMovieContract(this, movieEntity)
            }
        } else {
            detailFavorite.let {
                val tvShowEntity = TVShowsEntity(
                    it.id,
                    it.title,
                    it.originalTitle,
                    it.image,
                    it.imageBackground,
                    txt_genre_fav_detail.text.toString(),
                    txt_rate_fav_movie.text.toString(),
                    it.date,
                    it.overview
                )
                detailFavoriteMovieViewModel.getInsertFavoriteTVSHowContract(this, tvShowEntity)
            }
        }
    }

    private fun removeFavorite(movie: MoviesEntity? = null, tvShow: TVShowsEntity? = null) {
        if (movieId != 0) {
            movie?.let {
                detailFavoriteMovieViewModel.getDeleteFavoriteMovieContract(this, it)
            }
        } else {
            tvShow?.let {
                detailFavoriteMovieViewModel.getDeleteFavoriteTVShowContract(this, it)
            }
        }
    }

    private fun stateFavorite() {
        if (movieId != 0) {
            movie?.let {
                isFavorite = true
                setFavorite()
            }
        } else {
            tvShow?.let {
                isFavorite = true
                setFavorite()
            }
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
        const val DETAIL_EXTRA_FAV_MOVIE = "extra_fav_movie"
        const val DETAIL_EXTRA_FAV_TV_SHOW = "extra_fav_tv_show"
    }
}