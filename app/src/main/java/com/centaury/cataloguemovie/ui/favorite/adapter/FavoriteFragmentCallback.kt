package com.centaury.cataloguemovie.ui.favorite.adapter

import android.widget.ImageView

/**
 * Created by Centaury on 11/27/2019.
 */
interface FavoriteFragmentCallback {
    fun onDeleteItemClick(movieId: Int, position: Int)

    fun onItemClick(movieId: Int, image: ImageView, title: String)
}