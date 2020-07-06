package com.centaury.cataloguemovie.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.utils.CommonUtils.TopItemDecoration
import kotlinx.android.synthetic.main.fragment_tvshow.*
import java.util.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {
    @JvmField
    @Inject
    var factory: ViewModelProvider.Factory? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val tvShowViewModel = obtainViewModel(activity)
            val language = Locale.getDefault().toLanguageTag()
            val tvShowAdapter = TVShowAdapter(activity)
            tvShowViewModel.tVShows!!.observe(
                this,
                Observer { tvShowResultsItems: Any? ->
                    tvShowAdapter.submitList(tvShowResultsItems)
                    tvShowAdapter.notifyDataSetChanged()
                }
            )
            tvShowViewModel.getGenreTVShow(language).observe(
                this,
                Observer<List<error.NonExistentClass?>> { genresItemList: List<error.NonExistentClass?>? ->
                    tvShowAdapter.setListGenreTVShow(genresItemList)
                    tvShowAdapter.notifyDataSetChanged()
                }
            )
            tvShowViewModel.loadingState!!.observe(
                this,
                Observer { loadingState: Boolean ->
                    if (loadingState) {
                        shimmer_view_container.startShimmer()
                        shimmer_view_container.setVisibility(View.VISIBLE)
                    } else {
                        shimmer_view_container.stopShimmer()
                        shimmer_view_container.setVisibility(View.GONE)
                    }
                }
            )
            tvShowViewModel.loadMoreLoadingState!!.observe(
                this,
                Observer { loadMore: Boolean ->
                    if (loadMore) {
                        mTxtLoadMore.setVisibility(View.VISIBLE)
                    } else {
                        mTxtLoadMore.setVisibility(View.GONE)
                    }
                }
            )
            rv_tv_show.setLayoutManager(LinearLayoutManager(context))
            rv_tv_show.setHasFixedSize(true)
            rv_tv_show.setAdapter(tvShowAdapter)
            rv_tv_show.addItemDecoration(TopItemDecoration(55))
        }
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