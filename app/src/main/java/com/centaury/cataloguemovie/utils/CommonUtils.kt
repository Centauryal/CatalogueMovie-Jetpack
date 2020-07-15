package com.centaury.cataloguemovie.utils

import android.graphics.Rect
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.centaury.domain.genre.model.Genre
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Centaury on 10/7/2019.
 */
object CommonUtils {

    fun inputDate(): DateFormat {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }

    fun outputDate(): DateFormat {
        return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    }

    fun getGenresString(itemList: List<Genre>, genreData: List<Int>, txtGenre: TextView) {
        val genreMovies: MutableList<String> = ArrayList()

        try {
            if (genreData.size == 1) {
                for (genreId in genreData) {
                    for (genresItem in itemList) {
                        if (genresItem.id == genreId) {
                            genreMovies.add(genresItem.name)
                        }
                    }
                    txtGenre.text = TextUtils.join(", ", genreMovies)
                }
            } else {
                val integers = genreData.subList(0, 2)
                for (genreId in integers) {
                    for (genresItem in itemList) {
                        if (genresItem.id == genreId) {
                            genreMovies.add(genresItem.name)
                        }
                    }
                    txtGenre.text = TextUtils.join(", ", genreMovies)
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun getGenresString(itemList: List<Genre>, genreList: List<Int>): String {
        val genreMovies: MutableList<String> = ArrayList()

        try {
            if (genreList.size == 1) {
                for (genreId in genreList) {
                    for (genresItem in itemList) {
                        if (genresItem.id == genreId) {
                            genreMovies.add(genresItem.name)
                        }
                    }
                }
            } else {
                val integers = genreList.subList(0, 2)
                for (genreId in integers) {
                    for (genresItem in itemList) {
                        if (genresItem.id == genreId) {
                            genreMovies.add(genresItem.name)
                        }
                    }
                }
            }
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
        return TextUtils.join(", ", genreMovies)
    }

    class TopItemDecoration(private val spacing: Int) : ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val position = parent.getChildAdapterPosition(view) // item position
            if (position == 0) {
                outRect.top = spacing
            }
        }

    }
}