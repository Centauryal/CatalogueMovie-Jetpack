package com.centaury.cataloguemovie.ui.favorite.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.ui.detail.DetailFavoriteMovieActivity
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.tvshow.model.TVShowsEntity
import kotlinx.android.synthetic.main.item_favorite_movielist.view.*
import java.text.ParseException

/**
 * Created by Centaury on 11/23/2019.
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FavoriteTVShowAdapter(
    private var tvShowsFavorite: List<TVShowsEntity>,
    private val callback: FavoriteFragmentCallback
) : RecyclerView.Adapter<FavoriteTVShowAdapter.FavoriteTVShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTVShowViewHolder {
        return FavoriteTVShowViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: FavoriteTVShowViewHolder, position: Int) {
        holder.bind(tvShowsFavorite[position], callback)
    }

    override fun getItemCount(): Int = tvShowsFavorite.size

    class FavoriteTVShowViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.txt_title_movie_fav_list
        private val titleBackground = view.txt_title_fav_background
        private val poster = view.iv_movie_fav_list
        private val genreView = view.txt_genre_movie_fav_list
        private val overview = view.txt_desc_movie_fav_list
        private val dateView = view.txt_date_movie_fav_list
        private val btnDelete = view.btn_delete

        companion object {
            fun inflate(parent: ViewGroup): FavoriteTVShowViewHolder {
                return FavoriteTVShowViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_favorite_movielist, parent, false)
                )
            }
        }

        fun bind(tvShow: TVShowsEntity, callback: FavoriteFragmentCallback) {
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
                genreView.text = tvShow.genre
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

            loadFromUrl(
                poster,
                tvShow.image,
                R.drawable.ic_loading,
                R.drawable.ic_error
            )
            itemView.setOnClickListener {
                val intent = Intent(context, DetailFavoriteMovieActivity::class.java).apply {
                    putExtra(DetailFavoriteMovieActivity.DETAIL_EXTRA_FAV_TV_SHOW, tvShow.id)
                }
                context.startActivity(intent)
            }
            btnDelete.setOnClickListener {
                callback.onDeleteItemClick(tvShow.id)
            }
        }

    }
}