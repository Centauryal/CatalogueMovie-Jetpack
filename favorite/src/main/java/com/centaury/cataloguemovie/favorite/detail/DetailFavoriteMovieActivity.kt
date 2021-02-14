package com.centaury.cataloguemovie.favorite.detail

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
import com.centaury.cataloguemovie.favorite.R.layout
import com.centaury.cataloguemovie.favorite.databinding.ActivityDetailFavoriteMovieBinding
import com.centaury.cataloguemovie.favorite.di.component.DaggerDetailFavoriteComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.detail.model.Detail
import com.centaury.domain.movies.model.MoviesDB
import com.centaury.domain.tvshow.model.TVShowsDB
import javax.inject.Inject

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailFavoriteMovieActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val detailFavoriteMovieViewModel: DetailFavoriteMovieViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: ActivityDetailFavoriteMovieBinding

    private var movie: MoviesDB? = null
    private var tvShow: TVShowsDB? = null
    private var detailFavorite: Detail? = null
    private var isFavorite: Boolean = false
    private var movieId: Int? = null
    private var tvShowId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        initInjector()
        super.onCreate(savedInstanceState)
        binding = setContentView(this, layout.activity_detail_favorite_movie)
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
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp)
            setDisplayShowTitleEnabled(false)
        }

        initClick()
        initView(binding)
    }

    private fun initView(binding: ActivityDetailFavoriteMovieBinding) {
        initParam()
        initObserver(binding)

        setFavorite()
        binding.btnFavorite.scale = 2.481f
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

    private fun initObserver(binding: ActivityDetailFavoriteMovieBinding) {
        detailFavoriteMovieViewModel.state.observe(this, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasDetailFavoriteMovies = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasDetailFavoriteMovies = false
                }
            }
        })

        detailFavoriteMovieViewModel.resultMovieById.observe(this, { movieById ->
            movie = movieById
            binding.ivImgFavDetail.transitionName = movieById.title
            supportPostponeEnterTransition()
            showDetail(DetailMapper.mapperMovieToDetail(movieById))
            detailFavorite = DetailMapper.mapperMovieToDetail(movieById)
            stateFavorite()
        })

        detailFavoriteMovieViewModel.errorMovieById.observe(this, { errorMovieById ->
            timberE(errorMovieById)
        })

        detailFavoriteMovieViewModel.resultTVShowById.observe(this, { tvShowById ->
            tvShow = tvShowById
            binding.ivImgFavDetail.transitionName = tvShowById.title
            supportPostponeEnterTransition()
            showDetail(DetailMapper.mapperTVShowToDetail(tvShowById))
            detailFavorite = DetailMapper.mapperTVShowToDetail(tvShowById)
            stateFavorite()
        })

        detailFavoriteMovieViewModel.errorTVShowById.observe(this, { errorTVShowById ->
            timberE(errorTVShowById)
        })

        detailFavoriteMovieViewModel.resultInsertMovie.observe(this, {
            showToast(R.string.txt_movie_add)
        })

        detailFavoriteMovieViewModel.errorInsertMovie.observe(this, { errorInsertMovie ->
            timberE(errorInsertMovie)
        })

        detailFavoriteMovieViewModel.resultInsertTVShow.observe(this, {
            showToast(R.string.txt_movie_add)
        })

        detailFavoriteMovieViewModel.errorInsertTVShow.observe(this, { errorInsertTVShow ->
            timberE(errorInsertTVShow)
        })

        detailFavoriteMovieViewModel.resultDeleteMovie.observe(this, {
            showToast(R.string.txt_movie_remove)
        })

        detailFavoriteMovieViewModel.errorDeleteMovie.observe(this, { errorDeleteMovie ->
            timberE(errorDeleteMovie)
        })

        detailFavoriteMovieViewModel.resultDeleteTVShow.observe(this, {
            showToast(R.string.txt_movie_remove)
        })

        detailFavoriteMovieViewModel.errorDeleteTVShow.observe(this, { errorDeleteTVShow ->
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
        binding.txtTitleFavDetail.text = detail.title
        binding.txtRateFavMovie.text = detail.rating.toString()
        val rating = (detail.rating / 2).toFloat()
        binding.rbRatingFavDetail.rating = rating

        if (detail.genre.isEmpty()) {
            binding.txtGenreFavDetail.text = getString(R.string.txt_no_genre)
        } else {
            CommonUtils.getGenresString(detail.genre, binding.txtGenreFavDetail)
        }

        if (detail.overview.isEmpty() || detail.overview == "") {
            binding.txtDescFavDetail.text = getString(R.string.txt_no_desc)
        } else {
            binding.txtDescFavDetail.text = detail.overview
        }

        loadFromUrl(binding.ivImgFavDetail, detail.image)

        if (detail.imageBackground != "null") {
            loadFromUrl(binding.ivCoverFavDetail, detail.imageBackground)
        } else {
            loadFromUrl(binding.ivCoverFavDetail, detail.image)
        }

        binding.txtDateFavDetail.text = CommonUtils.toDateString(detail.date)
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
                    binding.txtGenreFavDetail.text.toString(),
                    binding.txtRateFavMovie.text.toString(),
                    it.date,
                    it.overview
                )
                detailFavoriteMovieViewModel.getInsertFavoriteMovieContract(movieEntity)
            }
        } else {
            detailFavorite.let {
                val tvShowEntity = TVShowsDB(
                    it.id,
                    it.title,
                    it.originalTitle,
                    it.image,
                    it.imageBackground,
                    binding.txtGenreFavDetail.text.toString(),
                    binding.txtRateFavMovie.text.toString(),
                    it.date,
                    it.overview
                )
                detailFavoriteMovieViewModel.getInsertFavoriteTVSHowContract(tvShowEntity)
            }
        }
    }

    private fun removeFavorite(movie: MoviesDB? = null, tvShow: TVShowsDB? = null) {
        if (movieId != 0) {
            movie?.let {
                detailFavoriteMovieViewModel.getDeleteFavoriteMovieContract(it)
            }
        } else {
            tvShow?.let {
                detailFavoriteMovieViewModel.getDeleteFavoriteTVShowContract(it)
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
        const val DETAIL_EXTRA_FAV_MOVIE = "extra_fav_movie"
        const val DETAIL_EXTRA_FAV_TV_SHOW = "extra_fav_tv_show"
    }
}