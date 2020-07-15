package com.centaury.cataloguemovie.ui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.movies.model.Movie
import kotlinx.android.synthetic.main.item_movie_list.view.*
import java.text.ParseException

/**
 * Created by Centaury on 10/7/2019.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MovieAdapter(
    private val movies: List<Movie>,
    private val genres: List<Genre>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position], genres)
    }

    class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.txt_title_movie_list
        private val titleBackground = view.txt_title_background
        private val poster = view.iv_movie_list
        private val genreView = view.txt_genre_movie_list
        private val overview = view.txt_desc_movie_list
        private val dateView = view.txt_date_movie_list

        companion object {
            private const val DETAIL_EXTRA_MOVIE = "extra_movie"

            fun inflate(parent: ViewGroup): MovieViewHolder {
                return MovieViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie_list, parent, false)
                )
            }
        }

        fun bind(movie: Movie, genres: List<Genre>) {
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
                genreView.text = CommonUtils.getGenresString(genres, movie.genre)
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

            poster.loadFromUrl(
                poster,
                movie.image,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )
            itemView.setOnClickListener {
                val intent = Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(DETAIL_EXTRA_MOVIE, movie.id)
                }
                context.startActivity(intent)
            }
        }
    }

}