package com.centaury.cataloguemovie.ui.tvshow

import android.annotation.SuppressLint
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
import com.centaury.cataloguemovie.databinding.FragmentTvshowBinding
import com.centaury.cataloguemovie.di.component.DaggerDiscoverTVShowComponent
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity
import com.centaury.cataloguemovie.ui.main.ItemClickCallback
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.showToast
import com.centaury.cataloguemovie.utils.timberE
import com.centaury.domain.model.Genre
import com.centaury.domain.model.TVShow
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment(), ItemClickCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val tvShowViewModel: TVShowViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentTvshowBinding

    private var tvShowData = arrayListOf<TVShow>()
    private var genreData = arrayListOf<Genre>()
    private val tvShowAdapter: TVShowAdapter by lazy {
        TVShowAdapter(tvShowData, genreData, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvshowBinding.inflate(inflater, container, false)
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
        DaggerDiscoverTVShowComponent.builder()
            .appComponent((requireActivity().application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

    private fun initView(binding: FragmentTvshowBinding) {
        with(binding.rvTvShow) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = tvShowAdapter
        }

        initObserver(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObserver(binding: FragmentTvshowBinding) {
        tvShowViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasTVShows = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasTVShows = false
                }
            }
        }

        tvShowViewModel.result.observe(viewLifecycleOwner) { result ->
            tvShowData.clear()
            tvShowData.addAll(result)
            CommonUtils.toggleEmptyState(result.size, binding.emptyState, binding.rvTvShow)
            tvShowAdapter.notifyDataSetChanged()
        }

        tvShowViewModel.error.observe(viewLifecycleOwner) { error ->
            timberE(error)
            context?.showToast(error)
        }

        tvShowViewModel.resultGenre.observe(viewLifecycleOwner) { resultGenre ->
            genreData.clear()
            genreData.addAll(resultGenre)
            tvShowAdapter.notifyDataSetChanged()
        }

        tvShowViewModel.errorGenre.observe(viewLifecycleOwner) { errorGenre ->
            timberE(errorGenre)
            context?.showToast(errorGenre)
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

    private fun showTransitionImage(movieId: Int, image: ImageView, title: String) {
        val intent = Intent(activity, DetailMovieActivity::class.java).apply {
            putExtra(DetailMovieActivity.DETAIL_EXTRA_TV_SHOW, movieId)
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