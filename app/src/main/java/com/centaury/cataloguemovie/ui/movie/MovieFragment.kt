package com.centaury.cataloguemovie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerDiscoverMovieComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieViewModel: MovieViewModel

    private var movieData = arrayListOf<Movie>()
    private var genreData = arrayListOf<Genre>()
    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(movieData, genreData)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        movieViewModel = ViewModelProvider(this, viewModelFactory)[MovieViewModel::class.java]

        with(rv_movie) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = movieAdapter
        }

        initObserver()
    }

    private fun initObserver() {
        movieViewModel.state.observe(viewLifecycleOwner, { state ->
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

        movieViewModel.result.observe(viewLifecycleOwner, { result ->
            movieData.clear()
            movieData.addAll(result)
            movieAdapter.notifyDataSetChanged()
        })

        movieViewModel.error.observe(viewLifecycleOwner, { error ->
            context?.showToast(error)
        })

        movieViewModel.resultGenre.observe(viewLifecycleOwner, { resultGenre ->
            genreData.clear()
            genreData.addAll(resultGenre)
            movieAdapter.notifyDataSetChanged()
        })

        movieViewModel.errorGenre.observe(viewLifecycleOwner, { errorGenre ->
            context?.showToast(errorGenre)
        })
    }

    private fun initInjector() {
        DaggerDiscoverMovieComponent.builder()
            .appComponent((activity?.application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }
}