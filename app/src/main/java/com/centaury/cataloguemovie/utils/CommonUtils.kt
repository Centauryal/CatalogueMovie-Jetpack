package com.centaury.cataloguemovie.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Created by Centaury on 10/7/2019.
 */
object CommonUtils {
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