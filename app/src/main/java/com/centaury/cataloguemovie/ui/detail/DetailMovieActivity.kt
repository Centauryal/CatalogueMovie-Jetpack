package com.centaury.cataloguemovie.ui.detail

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ActivityDetailMovieBinding
import com.centaury.cataloguemovie.di.component.DaggerDetailComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.detail.model.Detail
import com.centaury.domain.movies.model.MoviesDB
import com.centaury.domain.tvshow.model.TVShowsDB
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailMovieViewModel: DetailMovieViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityDetailMovieBinding

    private var movie: MoviesDB? = null
    private var tvShow: TVShowsDB? = null
    private var detailFavorite: Detail? = null
    private var isFavorite: Boolean = false
    private var movieId: Int? = null
    private var tvShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_detail_movie)
        showSystemUI()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.navigationBarDividerColor = getColorFromAttr(R.attr.colorPrimary)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow)
            setDisplayShowTitleEnabled(false)
        }

        checkConnection(this)
        initClick()
        initView(binding)
    }

    private fun initView(binding: ActivityDetailMovieBinding) {
        initParam()
        initObserver(binding)

        setFavorite()
        binding.btnFavorite.scale = 2.481f
    }

    private fun initParam() {
        movieId = intent.getIntExtra(DETAIL_EXTRA_MOVIE, 0)
        if (movieId != 0) {
            movieId?.let {
                detailMovieViewModel.getFavoriteMovieByIdContract(it)
                detailMovieViewModel.getDetailMovieContract(it)
            }
        } else {
            tvShowId = intent.getIntExtra(DETAIL_EXTRA_TV_SHOW, 0)
            tvShowId?.let {
                detailMovieViewModel.getFavoriteTVShowByIdContract(it)
                detailMovieViewModel.getDetailTVShowContract(it)
            }
        }
    }

    private fun initObserver(binding: ActivityDetailMovieBinding) {
        detailMovieViewModel.state.observe(this, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasDetailMovies = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasDetailMovies = false
                }
            }
        })

        detailMovieViewModel.resultMovie.observe(this, { resultMovie ->
            binding.ivImgDetail.transitionName = resultMovie.title
            supportPostponeEnterTransition()
            showDetail(resultMovie)
            detailFavorite = resultMovie
        })

        detailMovieViewModel.errorMovie.observe(this, { errorMovie ->
            timberE(errorMovie)
        })

        detailMovieViewModel.resultTVShow.observe(this, { resultTVShow ->
            binding.ivImgDetail.transitionName = resultTVShow.title
            supportPostponeEnterTransition()
            showDetail(resultTVShow)
            detailFavorite = resultTVShow
        })

        detailMovieViewModel.errorTVShow.observe(this, { errorTVShow ->
            timberE(errorTVShow)
        })

        detailMovieViewModel.resultMovieById.observe(this, { movieById ->
            movie = movieById
            stateFavorite()
        })

        detailMovieViewModel.errorMovieById.observe(this, { errorMovieById ->
            timberE(errorMovieById)
        })

        detailMovieViewModel.resultTVShowById.observe(this, { tvShowById ->
            tvShow = tvShowById
            stateFavorite()
        })

        detailMovieViewModel.errorTVShowById.observe(this, { errorTVShowById ->
            timberE(errorTVShowById)
        })

        detailMovieViewModel.resultInsertMovie.observe(this, {
            showToast(R.string.txt_movie_add)
        })

        detailMovieViewModel.errorInsertMovie.observe(this, { errorInsertMovie ->
            timberE(errorInsertMovie)
        })

        detailMovieViewModel.resultInsertTVShow.observe(this, {
            showToast(R.string.txt_movie_add)
        })

        detailMovieViewModel.errorInsertTVShow.observe(this, { errorInsertTVShow ->
            timberE(errorInsertTVShow)
        })

        detailMovieViewModel.resultDeleteMovie.observe(this, {
            showToast(R.string.txt_movie_remove)
        })

        detailMovieViewModel.errorDeleteMovie.observe(this, { errorDeleteMovie ->
            timberE(errorDeleteMovie)
        })

        detailMovieViewModel.resultDeleteTVShow.observe(this, {
            showToast(R.string.txt_movie_remove)
        })

        detailMovieViewModel.errorDeleteTVShow.observe(this, { errorDeleteTVShow ->
            timberE(errorDeleteTVShow)
        })
    }

    private fun initClick() {
        binding.setClickFavoriteListener {
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
        binding.btnFavorite.playAnimation()
    }

    private fun showDetail(detail: Detail) {
        binding.txtTitleDetail.text = detail.title
        binding.txtRateMovie.text = detail.rating.toString()
        val rating = (detail.rating / 2).toFloat()
        binding.rbRatingDetail.rating = rating

        if (detail.genre.isEmpty()) {
            binding.txtGenreDetail.text = getString(R.string.txt_no_genre)
        } else {
            CommonUtils.getGenresString(detail.genre, binding.txtGenreDetail)
        }

        if (detail.overview.isEmpty() || detail.overview == "") {
            binding.txtDescDetail.text = getString(R.string.txt_no_desc)
        } else {
            binding.txtDescDetail.text = detail.overview
        }

        loadFromUrl(binding.ivImgDetail, detail.image)

        if (detail.imageBackground != "null") {
            loadFromUrl(binding.ivCoverDetail, detail.imageBackground)
        } else {
            loadFromUrl(binding.ivCoverDetail, detail.image)
        }

        binding.txtDateDetail.text = CommonUtils.toDateString(detail.date)
    }

    private fun initInjector() {
        DaggerDetailComponent.builder()
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
        checkConnection(this)
        binding.shimmerViewContainer.startShimmer()
    }

    public override fun onPause() {
        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    private fun addFavorite(detailFavorite: Detail) {
        if (movieId != 0) {
            detailFavorite.let {
                val movieEntity = MoviesDB(
                    it.id,
                    it.title,
                    it.originalTitle,
                    it.image,
                    it.imageBackground,
                    binding.txtGenreDetail.text.toString(),
                    binding.txtRateMovie.text.toString(),
                    it.date,
                    it.overview
                )
                detailMovieViewModel.getInsertFavoriteMovieContract(movieEntity)
            }
        } else {
            detailFavorite.let {
                val tvShowEntity = TVShowsDB(
                    it.id,
                    it.title,
                    it.originalTitle,
                    it.image,
                    it.imageBackground,
                    binding.txtGenreDetail.text.toString(),
                    binding.txtRateMovie.text.toString(),
                    it.date,
                    it.overview
                )
                detailMovieViewModel.getInsertFavoriteTVSHowContract(tvShowEntity)
            }
        }
    }

    private fun removeFavorite(movie: MoviesDB? = null, tvShow: TVShowsDB? = null) {
        if (movieId != 0) {
            movie?.let {
                detailMovieViewModel.getDeleteFavoriteMovieContract(it)
            }
        } else {
            tvShow?.let {
                detailMovieViewModel.getDeleteFavoriteTVShowContract(it)
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
            binding.btnFavorite.speed = 1f
            binding.btnFavorite.progress = 1f
        } else {
            binding.btnFavorite.progress = 0f
            binding.btnFavorite.speed = -2f
        }
    }

    companion object {
        const val DETAIL_EXTRA_MOVIE = "extra_movie"
        const val DETAIL_EXTRA_TV_SHOW = "extra_tv_show"

    }
}