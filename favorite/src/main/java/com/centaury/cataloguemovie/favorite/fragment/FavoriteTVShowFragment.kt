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
import com.centaury.cataloguemovie.favorite.adapter.FavoriteTVShowAdapter
import com.centaury.cataloguemovie.favorite.databinding.FragmentFavoriteTvshowBinding
import com.centaury.cataloguemovie.favorite.detail.DetailFavoriteMovieActivity
import com.centaury.cataloguemovie.favorite.di.component.DaggerFavoriteTVShowComponent
import com.centaury.cataloguemovie.favorite.viewmodel.FavoriteTVShowViewModel
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.LoaderState
import com.centaury.cataloguemovie.utils.showToast
import com.centaury.cataloguemovie.utils.timberE
import com.centaury.domain.tvshow.model.TVShowsDB
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVShowFragment : Fragment(), FavoriteFragmentCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val favoriteTVShowViewModel: FavoriteTVShowViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var binding: FragmentFavoriteTvshowBinding

    private var tvShowFavoriteData = arrayListOf<TVShowsDB>()
    private val favoriteTVShowAdapter: FavoriteTVShowAdapter by lazy {
        FavoriteTVShowAdapter(tvShowFavoriteData, this)
    }

    private lateinit var titleDialog: TextView
    private var tvShow: TVShowsDB? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteTvshowBinding.inflate(inflater, container, false)
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
        DaggerFavoriteTVShowComponent.builder()
            .appComponent((requireActivity().application as MovieCatalogueApp).appComponent)
            .build()
            .inject(this)
    }

    private fun initView(binding: FragmentFavoriteTvshowBinding) {
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
            timberE(error)
        })

        favoriteTVShowViewModel.resultTVShowById.observe(viewLifecycleOwner, { resultTVShowById ->
            tvShow = resultTVShowById
        })

        favoriteTVShowViewModel.errorTVShowById.observe(viewLifecycleOwner, { errorTVShowById ->
            timberE(errorTVShowById)
        })

        favoriteTVShowViewModel.resultDeleteTVShow.observe(viewLifecycleOwner, {
            context?.showToast(R.string.txt_movie_remove)
        })

        favoriteTVShowViewModel.errorDeleteTVShow.observe(viewLifecycleOwner, { errorDeleteTVShow ->
            timberE(errorDeleteTVShow)
        })
    }

    private fun showDialogDeleteFavorite(tvShowId: Int, position: Int) {
        favoriteTVShowViewModel.getFavoriteTVShowByIdContract(tvShowId)

        val customDialog = context?.let { AlertDialog.Builder(it) }
        val view = LayoutInflater.from(context).inflate(R.layout.item_alert_dialog, null)

        titleDialog = view.findViewById(R.id.alert_title)
        titleDialog.text = context?.getString(R.string.txt_title_delete_dialog)

        customDialog?.apply {
            setView(view)
            setCancelable(false)
            setPositiveButton(R.string.btn_delete) { dialog, _ ->
                tvShow?.let { favoriteTVShowViewModel.getDeleteFavoriteTVShowContract(it) }
                favoriteTVShowAdapter.notifyItemRemoved(position)
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