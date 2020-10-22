package com.centaury.cataloguemovie.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.movies.model.MoviesEntity
import kotlinx.android.synthetic.main.item_favorite_movielist.view.*
import java.text.ParseException

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
        holder.bind(moviesFavorite[position], callback)
    }

    class FavoriteMovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.txt_title_movie_fav_list
        private val titleBackground = view.txt_title_fav_background
        private val poster = view.iv_movie_fav_list
        private val genreView = view.txt_genre_movie_fav_list
        private val overview = view.txt_desc_movie_fav_list
        private val dateView = view.txt_date_movie_fav_list
        private val btnDelete = view.btn_delete

        companion object {
            fun inflate(parent: ViewGroup): FavoriteMovieViewHolder {
                return FavoriteMovieViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_favorite_movielist, parent, false)
                )
            }
        }

        fun bind(movie: MoviesEntity, callback: FavoriteFragmentCallback) {
            val context = view.context
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

            try {
                val date = CommonUtils.inputDate().parse(movie.date)
                var releaseDate: String
                date.let {
                    releaseDate = CommonUtils.outputDate().format(it)
                }
                dateView.text = releaseDate
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            loadFromUrl(
                poster,
                movie.image,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )

            ViewCompat.setTransitionName(poster, movie.title)

            itemView.setOnClickListener {
                callback.onItemClick(movie.id, poster, movie.title)
            }
            btnDelete.setOnClickListener {
                callback.onDeleteItemClick(movie.id)
            }
        }

    }
}
