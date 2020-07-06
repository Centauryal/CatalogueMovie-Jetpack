package com.centaury.cataloguemovie.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.utils.CommonUtils.TopItemDecoration
import com.centaury.domain.movies.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var movieViewModel: MovieViewModel

    private var movieData = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieViewModel = obtainViewModel(activity)
            val language = Locale.getDefault().toLanguageTag()
            val movieAdapter = MovieAdapter(activity)
            movieViewModel.getMovies().observe(this, { movieResultsItems ->
                movieAdapter.submitList(movieResultsItems)
                movieAdapter.notifyDataSetChanged()
            })
            movieViewModel.getGenreMovie(language).observe(this, { genresItemList ->
                movieAdapter.setListGenreMovie(genresItemList)
                movieAdapter.notifyDataSetChanged()
            })
            movieViewModel.getLoadingState().observe(this, { loadingState ->
                if (loadingState) {
                    mShimmerViewContainer.startShimmer()
                    mShimmerViewContainer.setVisibility(View.VISIBLE)
                } else {
                    mShimmerViewContainer.stopShimmer()
                    mShimmerViewContainer.setVisibility(View.GONE)
                }
            })
            movieViewModel.getLoadMoreLoadingState().observe(this, { loadMore ->
                if (loadMore) {
                    mTxtLoadMore.setVisibility(View.VISIBLE)
                } else {
                    mTxtLoadMore.setVisibility(View.GONE)
                }
            })
            mRvMovie.setLayoutManager(LinearLayoutManager(context))
            mRvMovie.setHasFixedSize(true)
            mRvMovie.setAdapter(movieAdapter)
            mRvMovie.addItemDecoration(TopItemDecoration(55))
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieViewModel {
        return ViewModelProviders.of(activity, factory).get(MovieViewModel::class.java)
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