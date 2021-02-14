package com.centaury.cataloguemovie.ui.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.databinding.FragmentMovieBinding
import com.centaury.cataloguemovie.di.component.DaggerDiscoverMovieComponent
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity
import com.centaury.cataloguemovie.ui.main.ItemClickCallback
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.timberE
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.movies.model.Movie
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment(), ItemClickCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieViewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentMovieBinding

    private var movieData = arrayListOf<Movie>()
    private var genreData = arrayListOf<Genre>()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(movieData, genreData, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)
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
        DaggerDiscoverMovieComponent.builder()
            .appComponent((requireActivity().application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

    private fun initView(binding: FragmentMovieBinding) {
        with(binding.rvMovie) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = movieAdapter
        }

        initObserver(binding)
    }

    private fun initObserver(binding: FragmentMovieBinding) {
        movieViewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasMovies = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasMovies = false
                }
            }
        })

        movieViewModel.result.observe(viewLifecycleOwner, { result ->
            movieData.clear()
            movieData.addAll(result)
            movieAdapter.notifyDataSetChanged()
        })

        movieViewModel.error.observe(viewLifecycleOwner, { error ->
            timberE(error)
        })

        movieViewModel.resultGenre.observe(viewLifecycleOwner, { resultGenre ->
            genreData.clear()
            genreData.addAll(resultGenre)
            movieAdapter.notifyDataSetChanged()
        })

        movieViewModel.errorGenre.observe(viewLifecycleOwner, { errorGenre ->
            timberE(errorGenre)
        })
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        binding.shimmerViewContainer.stopShimmer()
        super.onPause()
    }

    private fun showTransitionImage(movieId: Int, image: ImageView, title: String) {
        val intent = Intent(activity, DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.DETAIL_EXTRA_MOVIE, movieId)
        }

        activity?.let {
            it.startActivity(
                intent,
                CommonUtils.pairOptionsTransition(it, image, title).toBundle()
            )
        }
    }

    override fun onItemClick(movieId: Int, image: ImageView, title: String) {
        showTransitionImage(movieId, image, title)
    }
}