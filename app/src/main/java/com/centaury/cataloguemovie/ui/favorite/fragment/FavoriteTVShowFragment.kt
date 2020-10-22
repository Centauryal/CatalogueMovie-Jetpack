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
import com.centaury.cataloguemovie.di.component.DaggerFavoriteTVShowComponent
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteTVShowAdapter
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel
import com.centaury.cataloguemovie.utils.*
import com.centaury.domain.tvshow.model.TVShowsEntity
import kotlinx.android.synthetic.main.fragment_favorite_tvshow.*
import kotlinx.android.synthetic.main.item_empty_state_placeholder.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVShowFragment : Fragment(), FavoriteFragmentCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favoriteTVShowViewModel: FavoriteTVShowViewModel

    private var tvShowFavoriteData = arrayListOf<TVShowsEntity>()
    private val favoriteTVShowAdapter: FavoriteTVShowAdapter by lazy {
        FavoriteTVShowAdapter(tvShowFavoriteData, this)
    }

    private lateinit var titleDialog: TextView
    private var tvShow: TVShowsEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tvshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView()
    }

    private fun initView() {
        favoriteTVShowViewModel =
            ViewModelProvider(this, viewModelFactory)[FavoriteTVShowViewModel::class.java]

        with(rv_fav_tv_show) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = favoriteTVShowAdapter
        }

        initObserver()
    }

    private fun initObserver() {
        favoriteTVShowViewModel.state.observe(viewLifecycleOwner, { state ->
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

        favoriteTVShowViewModel.result.observe(viewLifecycleOwner, { result ->
            tvShowFavoriteData.clear()
            tvShowFavoriteData.addAll(result)
            CommonUtils.toggleEmptyState(result.size, empty_state, rv_fav_tv_show)
            favoriteTVShowAdapter.notifyDataSetChanged()
        })

        favoriteTVShowViewModel.error.observe(viewLifecycleOwner, { error ->
            context?.showToast(error)
        })

        favoriteTVShowViewModel.resultTVShowById.observe(viewLifecycleOwner, { resultTVShowById ->
            tvShow = resultTVShowById
        })

        favoriteTVShowViewModel.errorTVShowById.observe(viewLifecycleOwner, { errorTVShowById ->
            context?.showToast(errorTVShowById)
        })

        favoriteTVShowViewModel.errorDeleteTVShow.observe(viewLifecycleOwner, { errorDeleteTVShow ->
            context?.showToast(errorDeleteTVShow)
        })
    }

    private fun initInjector() {
        DaggerFavoriteTVShowComponent.builder()
            .appComponent((activity?.application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

    private fun showDialogDeleteFavorite(tvShowId: Int) {
        favoriteTVShowViewModel.getFavoriteTVShowByIdContract(tvShowId)

        val customDialog: AlertDialog.Builder? = context?.let { AlertDialog.Builder(it) }
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_alert_dialog, null)

        titleDialog = view.findViewById(R.id.alert_title)
        titleDialog.text = context?.getString(R.string.txt_title_delete_dialog)

        customDialog?.apply {
            setView(view)
            setCancelable(false)
            setPositiveButton(R.string.btn_delete) { dialog, _ ->
                tvShow?.let { favoriteTVShowViewModel.getDeleteFavoriteTVShowContract(context, it) }
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