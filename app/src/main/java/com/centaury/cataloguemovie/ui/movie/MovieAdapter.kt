package com.centaury.cataloguemovie.ui.movie

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.centaury.cataloguemovie.BuildConfig
import com.centaury.cataloguemovie.R
import com.centaury.cataloguemovie.ui.detail.DetailMovieActivity
import com.centaury.cataloguemovie.ui.movie.MovieAdapter.MovieViewHolder
import com.centaury.cataloguemovie.utils.AppConstants
import com.centaury.cataloguemovie.utils.GlideApp
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Centaury on 10/7/2019.
 */
class MovieAdapter(private val activity: Activity) :
    PagedListAdapter<MovieResultsItem?, MovieViewHolder?>(DIFF_CALLBACK) {
    private val genresItemList: MutableList<GenresItem> =
        ArrayList<GenresItem>()

    fun setListGenreMovie(genreMovie: List<GenresItem?>?) {
        if (genreMovie == null) return
        genresItemList.clear()
        genresItemList.addAll(genreMovie)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return MovieViewHolder(view)
    }

    fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MovieResultsItem = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra(AppConstants.DETAIL_EXTRA_MOVIE, movie.getId())
            activity.startActivity(intent)
        }
    }

    inner class MovieViewHolder(itemView: View?) :
        RecyclerView.ViewHolder(itemView!!) {
        @JvmField
        @BindView(R.id.txt_title_background)
        var mTxtTitlebackground: TextView? = null

        @JvmField
        @BindView(R.id.iv_movie_list)
        var mIvMovielist: ImageView? = null

        @JvmField
        @BindView(R.id.txt_genre_movie_list)
        var mTxtGenremovielist: TextView? = null

        @JvmField
        @BindView(R.id.txt_title_movie_list)
        var mTxtTitlemovielist: TextView? = null

        @JvmField
        @BindView(R.id.txt_desc_movie_list)
        var mTxtDescmovielist: TextView? = null

        @JvmField
        @BindView(R.id.txt_date_movie_list)
        var mTxtDatemovielist: TextView? = null
        fun bind(movie: MovieResultsItem) {
            mTxtTitlemovielist.setText(movie.getTitle())
            mTxtTitlebackground.setText(movie.getOriginalTitle())
            if (movie.getOverview() == null || movie.getOverview().equals("")) {
                mTxtDescmovielist!!.text = activity.getString(R.string.txt_no_desc)
            } else {
                mTxtDescmovielist.setText(movie.getOverview())
            }
            if (movie.getGenreIds().size() === 0) {
                mTxtGenremovielist!!.text = activity.getString(R.string.txt_no_genre)
            } else {
                mTxtGenremovielist!!.text = getGenres(movie.getGenreIds())
            }
            val inputDate: DateFormat =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputDate: DateFormat =
                SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            try {
                val date = inputDate.parse(movie.getReleaseDate())
                var releaseDate: String? = null
                if (date != null) {
                    releaseDate = outputDate.format(date)
                }
                mTxtDatemovielist!!.text = releaseDate
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            GlideApp.with(itemView.context)
                .load(BuildConfig.IMAGE_URL + AppConstants.SIZE_IMAGE + movie.getPosterPath())
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error)
                )
                .into(mIvMovielist)
        }

        private fun getGenres(genreList: List<Int>): String {
            val genreMovies: MutableList<String?> =
                ArrayList()
            try {
                if (genreList.size == 1) {
                    for (genreId in genreList) {
                        for (genresItem in genresItemList) {
                            if (genresItem.getId() === genreId) {
                                genreMovies.add(genresItem.getName())
                            }
                        }
                    }
                } else {
                    val integers = genreList.subList(0, 2)
                    for (genreId in integers) {
                        for (genresItem in genresItemList) {
                            if (genresItem.getId() === genreId) {
                                genreMovies.add(genresItem.getName())
                            }
                        }
                    }
                }
            } catch (e: IndexOutOfBoundsException) {
                println("Exception thrown : $e")
            } catch (e: IllegalArgumentException) {
                println("Exception thrown : $e")
            }
            return TextUtils.join(", ", genreMovies)
        }

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieResultsItem> =
            object : DiffUtil.ItemCallback<MovieResultsItem>() {
                override fun areItemsTheSame(
                    oldItem: MovieResultsItem,
                    newItem: MovieResultsItem
                ): Boolean {
                    return oldItem.getId() === newItem.getId()
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: MovieResultsItem,
                    newItem: MovieResultsItem
                ): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }

}