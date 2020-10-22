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

    fun toggleEmptyState(size: Int, emptyState: View, recyclerView: RecyclerView) {
        if (size > 0) {
            emptyState.gone()
            recyclerView.visible()
        } else {
            recyclerView.gone()
            emptyState.visible()
        }
    }

    fun getGenresString(genreData: List<String>, txtGenre: TextView) {
        if (genreData.size == 1) {
            txtGenre.text = TextUtils.join(", ", genreData)
        } else {
            val strings = genreData.subList(0, 2)
            txtGenre.text = TextUtils.join(", ", strings)
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