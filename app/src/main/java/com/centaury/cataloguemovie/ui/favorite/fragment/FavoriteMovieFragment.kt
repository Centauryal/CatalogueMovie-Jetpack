package com.centaury.cataloguemovie.ui.favorite.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.data.local.entity.MovieEntity
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteFragmentCallback
import com.centaury.cataloguemovie.ui.favorite.adapter.FavoriteMovieAdapter
import com.centaury.cataloguemovie.ui.favorite.viewmodel.FavoriteMovieViewModel
import com.centaury.cataloguemovie.utils.CommonUtils.TopItemDecoration
import java.util.concurrent.ExecutionException

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment(), FavoriteFragmentCallback {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            favoriteMovieViewModel = obtainViewModel(activity)
            val favoriteMovieAdapter =
                FavoriteMovieAdapter(activity, this)
            favoriteMovieViewModel.getFavoriteMovie().observe(activity, { favoriteMovie ->
                if (favoriteMovie != null) {
                    mShimmerViewContainer.stopShimmer()
                    mShimmerViewContainer.setVisibility(View.GONE)
                    toggleEmptyMovies(favoriteMovie.size())
                    favoriteMovieAdapter.submitList(favoriteMovie)
                    favoriteMovieAdapter.notifyDataSetChanged()
                }
            })
            mRvFavMovie.setLayoutManager(LinearLayoutManager(context))
            mRvFavMovie.setHasFixedSize(true)
            mRvFavMovie.setAdapter(favoriteMovieAdapter)
            mRvFavMovie.addItemDecoration(TopItemDecoration(55))
        }
    }

    private fun showDialogDeleteFavorite(movieId: Int) {
        val movieEntity: MovieEntity
        try {
            movieEntity = favoriteMovieViewModel.getDetailFavMovie(movieId)
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.item_alert_dialog, null)
            val builder =
                AlertDialog.Builder(context!!)
            builder.setView(view)
            val title = view.findViewById<TextView>(R.id.alert_title)
            title.text = getString(R.string.txt_title_delete_dialog)
            builder.setCancelable(false)
                .setPositiveButton(
                    getString(R.string.btn_delete)
                ) { dialog: DialogInterface, which: Int ->
                    favoriteMovieViewModel.deleteFavoriteMovie(movieEntity)
                    dialog.dismiss()
                    Toast.makeText(
                        context,
                        getString(R.string.txt_movie_remove),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(
                    getString(R.string.btn_cancel)
                ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
            val alertDialog = builder.create()
            alertDialog.show()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): FavoriteMovieViewModel {
        return ViewModelProviders.of(activity, factory).get(FavoriteMovieViewModel::class.java)
    }

    private fun toggleEmptyMovies(size: Int) {
        if (size > 0) {
            mEmptyState.setVisibility(View.GONE)
            mRvFavMovie.setVisibility(View.VISIBLE)
        } else {
            mRvFavMovie.setVisibility(View.GONE)
            mEmptyState.setVisibility(View.VISIBLE)
        }
    }

    override fun onResume() {
        super.onResume()
        mShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        mShimmerViewContainer.stopShimmer()
        super.onPause()
    }

    override fun onDeleteItemClick(movieId: Int) {
        if (activity != null) {
            showDialogDeleteFavorite(movieId)
        }
    }
}