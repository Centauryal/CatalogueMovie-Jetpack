package com.centaury.cataloguemovie.ui.tvshow

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
import com.centaury.domain.tvshow.model.TVShow
import kotlinx.android.synthetic.main.item_movie_list.view.*
import java.text.ParseException

/**
 * Created by Centaury on 10/7/2019.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TVShowAdapter(
    private val tvShows: List<TVShow>,
    private val genres: List<Genre>
) : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        return TVShowViewHolder.inflate(parent)
    }

    override fun getItemCount(): Int = tvShows.size

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(tvShows[position], genres)
    }

    class TVShowViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.txt_title_movie_list
        private val titleBackground = view.txt_title_background
        private val poster = view.iv_movie_list
        private val genreView = view.txt_genre_movie_list
        private val overview = view.txt_desc_movie_list
        private val dateView = view.txt_date_movie_list

        companion object {
            private const val DETAIL_EXTRA_TV_SHOW = "extra_tv_show"

            fun inflate(parent: ViewGroup): TVShowViewHolder {
                return TVShowViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_movie_list, parent, false)
                )
            }
        }

        fun bind(tvShow: TVShow, genres: List<Genre>) {
            val context = view.context
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
                genreView.text = CommonUtils.getGenresString(genres, tvShow.genre)
            }

            try {
                val date = CommonUtils.inputDate().parse(tvShow.date)
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
                tvShow.image,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )
            itemView.setOnClickListener {
                val intent = Intent(context, DetailMovieActivity::class.java).apply {
                    putExtra(DETAIL_EXTRA_TV_SHOW, tvShow.id)
                }
                context.startActivity(intent)
            }
        }
    }
}
