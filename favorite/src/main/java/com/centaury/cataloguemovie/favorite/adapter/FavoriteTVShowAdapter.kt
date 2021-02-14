package com.centaury.cataloguemovie.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.favorite.databinding.ItemFavoriteMovielistBinding
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.tvshow.model.TVShowsDB

/**
 * Created by Centaury on 11/23/2019.
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FavoriteTVShowAdapter(
    private val tvShowsFavorite: List<TVShowsDB>,
    private val callback: FavoriteFragmentCallback
) : RecyclerView.Adapter<FavoriteTVShowAdapter.FavoriteTVShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowViewHolder =
        FavoriteTVShowViewHolder.inflate(parent)

    override fun onBindViewHolder(holder: FavoriteTVShowViewHolder, position: Int) =
        holder.bind(tvShowsFavorite[position], position, callback)

    override fun getItemCount(): Int = tvShowsFavorite.size

    class FavoriteTVShowViewHolder(private val binding: ItemFavoriteMovielistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.txtTitleMovieFavList
        private val titleBackground = binding.txtTitleFavBackground
        private val poster = binding.ivMovieFavList
        private val genreView = binding.txtGenreMovieFavList
        private val overview = binding.txtDescMovieFavList
        private val dateView = binding.txtDateMovieFavList

        companion object {
            fun inflate(parent: ViewGroup): FavoriteTVShowViewHolder {
                return FavoriteTVShowViewHolder(
                    ItemFavoriteMovielistBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(tvShow: TVShowsDB, position: Int, callback: FavoriteFragmentCallback) {
            val context = binding.root.context
            title.text = tvShow.title
            titleBackground.text = tvShow.titleBackground
            if (tvShow.overview.isEmpty() || tvShow.overview == "") {
                overview.text = context.getString(R.string.txt_no_desc)
            } else {
                overview.text = tvShow.overview
            }
            if (tvShow.genre.isEmpty()) {
                genreView.text = context.getString(R.string.txt_no_genre)
            } else {
                genreView.text = tvShow.genre
            }

            dateView.text = CommonUtils.toDateString(tvShow.date)
            loadFromUrl(poster, tvShow.image)

            ViewCompat.setTransitionName(poster, tvShow.title)

            binding.setClickListener {
                callback.onItemClick(tvShow.id, poster, tvShow.title)
            }
            binding.setClickDeleteListener {
                callback.onDeleteItemClick(tvShow.id, position)
            }
        }

    }
}