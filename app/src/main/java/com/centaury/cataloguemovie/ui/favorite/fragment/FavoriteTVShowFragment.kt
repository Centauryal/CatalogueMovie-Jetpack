package com.centaury.cataloguemovie.ui.favorite.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.MovieCatalogueApp
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.FragmentFavoriteTvshowBinding
import com.centaury.cataloguemovie.di.component.DaggerFavoriteTVShowComponent
import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieActivity
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteTVShowAdapter
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteTVShowViewModel
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.showToast
import com.centaury.domain.tvshow.model.TVShowsEntity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVShowFragment : Fragment(), FavoriteFragmentCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var favoriteTVShowViewModel: FavoriteTVShowViewModel

    private lateinit var binding: FragmentFavoriteTvshowBinding

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
        binding = FragmentFavoriteTvshowBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInjector()
        initView(binding)
    }

    private fun initView(binding: FragmentFavoriteTvshowBinding) {
        favoriteTVShowViewModel =
            ViewModelProvider(this, viewModelFactory)[FavoriteTVShowViewModel::class.java]

        with(binding.rvFavTvShow) {
            hasFixedSize()
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(CommonUtils.TopItemDecoration(55))
            adapter = favoriteTVShowAdapter
        }

        initObserver(binding)
    }

    private fun initObserver(binding: FragmentFavoriteTvshowBinding) {
        favoriteTVShowViewModel.state.observe(viewLifecycleOwner, { state ->
            when (state) {
                is LoaderState.ShowLoading -> {
                    binding.shimmerViewContainer.startShimmer()
                    binding.hasFavoriteTVShows = true
                }
                is LoaderState.HideLoading -> {
                    binding.shimmerViewContainer.stopShimmer()
                    binding.hasFavoriteTVShows = false
                }
            }
        })

        favoriteTVShowViewModel.result.observe(viewLifecycleOwner, { result ->
            tvShowFavoriteData.clear()
            tvShowFavoriteData.addAll(result)
            CommonUtils.toggleEmptyState(result.size, binding.emptyState, binding.rvFavTvShow)
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

    private fun showDialogDeleteFavorite(tvShowId: Int, position: Int) {
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
                favoriteTVShowAdapter.notifyItemRemoved(position)
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

    private fun showTransitionImage(movieId: Int, image: ImageView, title: String) {
        val intent = Intent(context, DetailFavoriteMovieActivity::class.java).apply {
            putExtra(DetailFavoriteMovieActivity.DETAIL_EXTRA_FAV_TV_SHOW, movieId)
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