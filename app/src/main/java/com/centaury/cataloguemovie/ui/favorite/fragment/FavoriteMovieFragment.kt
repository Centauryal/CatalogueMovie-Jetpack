package com.centaury.cataloguemovie.ui.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerFavoriteMovieComponent
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteMovieAdapter
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModel
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.movies.model.MoviesEntity
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import kotlinx.android.synthetic.main.item_empty_state_placeholder.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment(), FavoriteFragmentCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favoriteMovieViewModel: FavoriteMovieViewModel

    private var movieFavoriteData = arrayListOf<MoviesEntity>()
    private val favoriteMovieAdapter: FavoriteMovieAdapter by lazy {
        FavoriteMovieAdapter(movieFavoriteData, this)
    }

    private lateinit var titleDialog: TextView
    private var movie: MoviesEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        favoriteMovieViewModel =
            ViewModelProvider(this, viewModelFactory)[FavoriteMovieViewModel::class.java]

        with(rv_fav_movie) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = favoriteMovieAdapter
        }

        initObserver()

    }

    private fun initObserver() {
        favoriteMovieViewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    shimmer_view_container.startShimmer()
                    shimmer_view_container.visible()
                }
                is LoaderState.HideLoading -> {
                    shimmer_view_container.stopShimmer()
                    shimmer_view_container.gone()
                }
            }
        })

        favoriteMovieViewModel.result.observe(viewLifecycleOwner, { result ->
            movieFavoriteData.clear()
            movieFavoriteData.addAll(result)
            CommonUtils.toggleEmptyState(result.size, empty_state, rv_fav_movie)
            favoriteMovieAdapter.notifyDataSetChanged()
        })

        favoriteMovieViewModel.error.observe(viewLifecycleOwner, { error ->
            context?.showToast(error)
        })

        favoriteMovieViewModel.resultMovieById.observe(viewLifecycleOwner, { resultMovieById ->
            movie = resultMovieById
        })

        favoriteMovieViewModel.errorMovieById.observe(viewLifecycleOwner, { errorMovieById ->
            context?.showToast(errorMovieById)
        })

        favoriteMovieViewModel.errorDeleteMovie.observe(viewLifecycleOwner, { errorDeleteMovie ->
            context?.showToast(errorDeleteMovie)
        })
    }

    private fun initInjector() {
        DaggerFavoriteMovieComponent.builder()
            .appComponent((activity?.application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)

    }

    private fun showDialogDeleteFavorite(movieId: Int) {
        favoriteMovieViewModel.getFavoriteMovieByIdContract(movieId)

        val customDialog: AlertDialog.Builder? = context?.let { AlertDialog.Builder(it) }
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_alert_dialog, null)

        titleDialog = view.findViewById(R.id.alert_title)
        titleDialog.text = context?.getString(R.string.txt_title_delete_dialog)

        customDialog?.apply {
            setView(view)
            setCancelable(false)
            setPositiveButton(R.string.btn_delete) { dialog, _ ->
                movie?.let { favoriteMovieViewModel.getDeleteFavoriteMovieContract(context, it) }
                dialog.dismiss()
                context.showToast(R.string.txt_movie_remove)
            }
            setNegativeButton(R.string.btn_cancel) { dialog, _ ->
                dialog.dismiss()
            }

        }
        val alertDialog = customDialog?.create()
        alertDialog?.show()
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }

    override fun onDeleteItemClick(movieId: Int) {
        showDialogDeleteFavorite(movieId)
    }
}