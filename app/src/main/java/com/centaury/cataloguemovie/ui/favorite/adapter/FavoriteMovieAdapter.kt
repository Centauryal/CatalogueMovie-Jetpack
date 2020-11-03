package com.centaury.cataloguemovie.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ItemFavoriteMovielistBinding
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.movies.model.MoviesEntity

/**
 * Created by Centaury on 11/23/2019.
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FavoriteMovieAdapter(
    private val moviesFavorite: List<MoviesEntity>,
    private val callback: FavoriteFragmentCallback
) : RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        return FavoriteMovieViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int = moviesFavorite.size

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        holder.bind(moviesFavorite[position], position, callback)
    }

    class FavoriteMovieViewHolder(private val binding: ItemFavoriteMovielistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.txtTitleMovieFavList
        private val titleBackground = binding.txtTitleFavBackground
        private val poster = binding.ivMovieFavList
        private val genreView = binding.txtGenreMovieFavList
        private val overview = binding.txtDescMovieFavList
        private val dateView = binding.txtDateMovieFavList

        companion object {
            fun inflate(parent: ViewGroup): FavoriteMovieViewHolder {
                return FavoriteMovieViewHolder(
                    ItemFavoriteMovielistBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

        fun bind(movie: MoviesEntity, position: Int, callback: FavoriteFragmentCallback) {
            val context = binding.root.context
            title.text = movie.title
            titleBackground.text = movie.titleBackground
            if (movie.overview.isEmpty() || movie.overview == "") {
                overview.text = context.getString(R.string.txt_no_desc)
            } else {
                overview.text = movie.overview
            }
            if (movie.genre.isEmpty()) {
                genreView.text = context.getString(R.string.txt_no_genre)
            } else {
                genreView.text = movie.genre
            }

            dateView.text = CommonUtils.toDateString(movie.date)
            loadFromUrl(poster, movie.image)

            ViewCompat.setTransitionName(poster, movie.title)

            binding.setClickListener {
                callback.onItemClick(movie.id, poster, movie.title)
            }
            binding.setClickDeleteListener {
                callback.onDeleteItemClick(movie.id, position)
            }
        }

    }
}
