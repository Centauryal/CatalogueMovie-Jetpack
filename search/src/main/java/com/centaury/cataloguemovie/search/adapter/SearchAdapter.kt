package com.centaury.cataloguemovie.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.databinding.ItemMovieListBinding
import com.centaury.cataloguemovie.utils.CommonUtils
import com.centaury.cataloguemovie.utils.loadFromUrl
import com.centaury.domain.genre.model.Genre
import com.centaury.domain.search.model.Search

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/20/21.
 */
class SearchAdapter(
    private val searchList: List<Search>,
    private val genreList: List<Genre>,
    private val callback: SearchCallback
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder.inflate(parent)

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) =
        holder.bind(searchList[position], genreList, callback)

    override fun getItemCount(): Int = searchList.size

    class SearchViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val title = binding.txtTitleMovieList
        private val titleBackground = binding.txtTitleBackground
        private val poster = binding.ivMovieList
        private val genreView = binding.txtGenreMovieList
        private val overview = binding.txtDescMovieList
        private val dateView = binding.txtDateMovieList

        companion object {
            fun inflate(parent: ViewGroup): SearchViewHolder {
                return SearchViewHolder(
                    ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }

        fun bind(search: Search, genres: List<Genre>, callback: SearchCallback) {
            val context = binding.root.context
            title.text = search.title
            titleBackground.text = search.titleBackground
            if (search.overview.isEmpty() || search.overview == "") {
                overview.text = context.getString(R.string.txt_no_desc)
            } else {
                overview.text = search.overview
            }
            if (search.genre.isEmpty()) {
                genreView.text = context.getString(R.string.txt_no_genre)
            } else {
                genreView.text = CommonUtils.getGenresString(genres, search.genre)
            }

            dateView.text = CommonUtils.toDateString(search.date)
            loadFromUrl(poster, search.image)

            ViewCompat.setTransitionName(poster, search.title)

            binding.setClickListener {
                callback.onItemClick(search.id, poster, search.title)
            }
        }
    }
}