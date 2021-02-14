package com.centaury.cataloguemovie.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ItemMovieListBinding
import com.centaury.cataloguemovie.ui.main.ItemClickCallback
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.movies.model.Movie

/**
 * Created by Centaury on 10/7/2019.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MovieAdapter(
    private val movies: List<Movie>,
    private val genres: List<Genre>,
    private val callback: ItemClickCallback
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder.inflate(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position], genres, callback)

    override fun getItemCount(): Int = movies.size

    class MovieViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.txtTitleMovieList
        private val titleBackground = binding.txtTitleBackground
        private val poster = binding.ivMovieList
        private val genreView = binding.txtGenreMovieList
        private val overview = binding.txtDescMovieList
        private val dateView = binding.txtDateMovieList

        companion object {
            fun inflate(parent: ViewGroup): MovieViewHolder {
                return MovieViewHolder(
                    ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }

        fun bind(movie: Movie, genres: List<Genre>, callback: ItemClickCallback) {
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
                genreView.text = CommonUtils.getGenresString(genres, movie.genre)
            }

            dateView.text = CommonUtils.toDateString(movie.date)
            loadFromUrl(poster, movie.image)

            ViewCompat.setTransitionName(poster, movie.title)

            binding.setClickListener {
                callback.onItemClick(movie.id, poster, movie.title)
            }
        }
    }

}