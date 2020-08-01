package com.centaury.cataloguemovie.ui.favorite.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerFavoriteMovieComponent
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteMovieAdapter
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModel
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.gone
import com.centaury.cataloguemovie.utils.visible
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
        FavoriteMovieAdapter(movieFavoriteData)
    }

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
        favoriteMovieViewModel.state.observe(viewLifecycleOwner, Observer { state ->
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

        favoriteMovieViewModel.result.observe(viewLifecycleOwner, Observer { result ->
            toggleEmptyMovies(result.size)
            movieFavoriteData.addAll(result)
            favoriteMovieAdapter.notifyDataSetChanged()
        })
    }

    private fun toggleEmptyMovies(size: Int) {
        if (size > 0) {
            empty_state.gone()
            rv_fav_movie.visible()
        } else {
            rv_fav_movie.gone()
            empty_state.visible()
        }
    }

    private fun initInjector() {
        DaggerFavoriteMovieComponent.builder()
            .appComponent((activity?.application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)

    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            favoriteMovieViewModel = obtainViewModel(activity)
            val favoriteMovieAdapter =
                FavoriteMovieAdapter(activity, this)
            favoriteMovieViewModel.getFavoriteMovie().observe(activity, { favoriteMovie ->
                if (favoriteMovie != null) {
                    mShimmerViewContainer.stopShimmer()
                    mShimmerViewContainer.setVisibility(View.GONE)
                    toggleEmptyMovies(favoriteMovie.size())
                    favoriteMovieAdapter.submitList(favoriteMovie)
                    favoriteMovieAdapter.notifyDataSetChanged()
                }
            })
            mRvFavMovie.setLayoutManager(LinearLayoutManager(context))
            mRvFavMovie.setHasFixedSize(true)
            mRvFavMovie.setAdapter(favoriteMovieAdapter)
            mRvFavMovie.addItemDecoration(TopItemDecoration(55))
        }
    }*/

    /*private fun showDialogDeleteFavorite(movieId: Int) {
        val movieEntity: MovieEntity
        try {
            movieEntity = favoriteMovieViewModel.getDetailFavMovie(movieId)
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.item_alert_dialog, null)
            val builder =
                AlertDialog.Builder(context!!)
            builder.setView(view)
            val title = view.findViewById<TextView>(R.id.alert_title)
            title.text = getString(R.string.txt_title_delete_dialog)
            builder.setCancelable(false)
                .setPositiveButton(
                    getString(R.string.btn_delete)
                ) { dialog: DialogInterface, which: Int ->
                    favoriteMovieViewModel.deleteFavoriteMovie(movieEntity)
                    dialog.dismiss()
                    Toast.makeText(
                        context,
                        getString(R.string.txt_movie_remove),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(
                    getString(R.string.btn_cancel)
                ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
            val alertDialog = builder.create()
            alertDialog.show()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }*/

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }

    override fun onDeleteItemClick(movieId: Int) {
        /*if (activity != null) {
            showDialogDeleteFavorite(movieId)
        }*/
    }
}