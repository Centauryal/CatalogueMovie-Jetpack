package com.centaury.cataloguemovie.utils

import android.app.Activity
import android.graphics.Rect
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.centaury.domain.genre.model.Genre
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Centaury on 10/7/2019.
 */
object CommonUtils {

    private fun inputDate(): DateFormat {
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    }

    private fun outputDate(): DateFormat {
        return SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun toDateString(input: String): String {
        val release: String

        return try {
            val date = inputDate().parse(input)
            release = outputDate().format(date)
            release
        } catch (e: ParseException) {
            e.printStackTrace().toString()
        }
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

    fun pairOptionsTransition(
        activity: Activity,
        image: ImageView,
        title: String
    ): ActivityOptionsCompat {
        val pairImage = Pair<View, String>(image, title)

        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairImage)
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