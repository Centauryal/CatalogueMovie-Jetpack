package com.centaury.cataloguemovie.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.di.component.DaggerDiscoverTVShowComponent
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.tvshow.model.TVShow
import kotlinx.android.synthetic.main.fragment_tvshow.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var tvShowViewModel: TVShowViewModel

    private var tvShowData = arrayListOf<TVShow>()
    private var genreData = arrayListOf<Genre>()
    private val tvShowAdapter: TVShowAdapter by lazy {
        TVShowAdapter(tvShowData, genreData)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        tvShowViewModel = ViewModelProvider(this, viewModelFactory)[TVShowViewModel::class.java]

        with(rv_tv_show) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = tvShowAdapter
        }

        initObserver()
    }

    private fun initObserver() {
        tvShowViewModel.state.observe(viewLifecycleOwner, { state ->
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

        tvShowViewModel.result.observe(viewLifecycleOwner, { result ->
            tvShowData.clear()
            tvShowData.addAll(result)
            tvShowAdapter.notifyDataSetChanged()
        })

        tvShowViewModel.error.observe(viewLifecycleOwner, { error ->
            context?.showToast(error)
        })

        tvShowViewModel.resultGenre.observe(viewLifecycleOwner, { resultGenre ->
            genreData.clear()
            genreData.addAll(resultGenre)
            tvShowAdapter.notifyDataSetChanged()
        })

        tvShowViewModel.errorGenre.observe(viewLifecycleOwner, { errorGenre ->
            context?.showToast(errorGenre)
        })

    }

    private fun initInjector() {
        DaggerDiscoverTVShowComponent.builder()
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