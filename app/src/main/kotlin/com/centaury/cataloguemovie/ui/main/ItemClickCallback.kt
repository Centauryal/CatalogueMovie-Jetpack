package com.centaury.cataloguemovie.ui.main

import android.widget.ImageView

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 10/22/2020.
 */
interface ItemClickCallback {
    fun onItemClick(movieId: Int, image: ImageView, title: String)
}