package com.centaury.cataloguemovie.favorite.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.favorite.adapter.FavoriteFragmentCallback
import com.centaury.cataloguemovie.favorite.adapter.FavoriteMovieAdapter
import com.centaury.cataloguemovie.favorite.databinding.FragmentFavoriteMovieBinding
import com.centaury.cataloguemovie.favorite.detail.DetailFavoriteMovieActivity
import com.centaury.cataloguemovie.favorite.di.component.DaggerFavoriteMovieComponent
import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteMovieViewModel
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.showToast
import com.centaury.cataloguemovie.utils.timberE
import com.centaury.domain.movies.model.MoviesDB
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment(), FavoriteFragmentCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val favoriteMovieViewModel: FavoriteMovieViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentFavoriteMovieBinding

    private var movieFavoriteData = arrayListOf<MoviesDB>()
    private val favoriteMovieAdapter: FavoriteMovieAdapter by lazy {
        FavoriteMovieAdapter(movieFavoriteData, this)
    }

    private lateinit var titleDialog: TextView
    private var movie: MoviesDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(binding)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        initInjector()
    }

    private fun initInjector() {
        DaggerFavoriteMovieComponent.builder()
            .appComponent((requireActivity().application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)

    }

    private fun initView(binding: FragmentFavoriteMovieBinding) {
        with(binding.rvFavMovie) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = favoriteMovieAdapter
        }

        initObserver(binding)
    }

    private fun initObserver(binding: FragmentFavoriteMovieBinding) {
        favoriteMovieViewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasFavoriteMovies = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasFavoriteMovies = false
                }
            }
        })

        favoriteMovieViewModel.result.observe(viewLifecycleOwner, { result ->
            movieFavoriteData.clear()
            movieFavoriteData.addAll(result)
            CommonUtils.toggleEmptyState(result.size, binding.emptyState, binding.rvFavMovie)
            favoriteMovieAdapter.notifyDataSetChanged()
        })

        favoriteMovieViewModel.error.observe(viewLifecycleOwner, { error ->
            timberE(error)
        })

        favoriteMovieViewModel.resultMovieById.observe(viewLifecycleOwner, { resultMovieById ->
            movie = resultMovieById
        })

        favoriteMovieViewModel.errorMovieById.observe(viewLifecycleOwner, { errorMovieById ->
            timberE(errorMovieById)
        })

        favoriteMovieViewModel.resultDeleteMovie.observe(viewLifecycleOwner, {
            context?.showToast(R.string.txt_movie_remove)
        })

        favoriteMovieViewModel.errorDeleteMovie.observe(viewLifecycleOwner, { errorDeleteMovie ->
            timberE(errorDeleteMovie)
        })
    }

    private fun showDialogDeleteFavorite(movieId: Int, position: Int) {
        favoriteMovieViewModel.getFavoriteMovieByIdContract(movieId)

        val customDialog = context?.let { AlertDialog.Builder(it) }
        val view = LayoutInflater.from(context).inflate(R.layout.item_alert_dialog, null)

        titleDialog = view.findViewById(R.id.alert_title)
        titleDialog.text = context?.getString(R.string.txt_title_delete_dialog)

        customDialog?.apply {
            setView(view)
            setCancelable(false)
            setPositiveButton(R.string.btn_delete) { dialog, _ ->
                movie?.let { favoriteMovieViewModel.getDeleteFavoriteMovieContract(it) }
                favoriteMovieAdapter.notifyItemRemoved(position)
                context.showToast(R.string.txt_movie_remove)
                dialog.dismiss()
            }
            setNegativeButton(R.string.btn_cancel) { dialog, _ ->
                dialog.dismiss()
            }

        }
        val alertDialog = customDialog?.create()
        alertDialog?.show()
    }

    private fun showTransitionImage(movieId: Int, image: ImageView, title: String) {
        val intent = Intent(activity, DetailFavoriteMovieActivity::class.java).apply {
            putExtra(DetailFavoriteMovieActivity.DETAIL_EXTRA_FAV_MOVIE, movieId)
        }
        activity?.let {
            it.startActivity(
                intent,
                CommonUtils.pairOptionsTransition(it, image, title).toBundle()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    override fun onDeleteItemClick(movieId: Int, position: Int) {
        showDialogDeleteFavorite(movieId, position)
    }

    override fun onItemClick(movieId: Int, image: ImageView, title: String) {
        showTransitionImage(movieId, image, title)
    }
}