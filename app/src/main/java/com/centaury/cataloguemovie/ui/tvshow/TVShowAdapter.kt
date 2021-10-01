package com.centaury.cataloguemovie.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ItemMovieListBinding
import com.centaury.cataloguemovie.ui.main.ItemClickCallback
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.model.Genre
import com.centaury.domain.model.TVShow

/**
 * Created by Centaury on 10/7/2019.
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TVShowAdapter(
    private val tvShows: List<TVShow>,
    private val genres: List<Genre>,
    private val callback: ItemClickCallback
) : RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder =
        TVShowViewHolder.inflate(parent)

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) =
        holder.bind(tvShows[position], genres, callback)

    override fun getItemCount(): Int = tvShows.size

    class TVShowViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.txtTitleMovieList
        private val titleBackground = binding.txtTitleBackground
        private val poster = binding.ivMovieList
        private val genreView = binding.txtGenreMovieList
        private val overview = binding.txtDescMovieList
        private val dateView = binding.txtDateMovieList

        companion object {
            fun inflate(parent: ViewGroup): TVShowViewHolder {
                return TVShowViewHolder(
                    ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }

        fun bind(tvShow: TVShow, genres: List<Genre>, callback: ItemClickCallback) {
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
                genreView.text = CommonUtils.getGenresString(genres, tvShow.genre)
            }

            if (tvShow.date.isEmpty()) {
                dateView.text = context.getString(R.string.txt_no_date)
            } else {
                dateView.text = CommonUtils.toDateString(tvShow.date)
            }

            poster.loadFromUrl(tvShow.image)

            ViewCompat.setTransitionName(poster, tvShow.title)

            binding.setClickListener {
                callback.onItemClick(tvShow.id, poster, tvShow.title)
            }
        }
    }
}
