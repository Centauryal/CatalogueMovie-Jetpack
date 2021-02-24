package com.centaury.cataloguemovie.search.adapter

import android.widget.ImageView

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 2/20/21.
 */
interface SearchCallback {
    fun onItemClick(searchId: Int, image: ImageView, title: String)
}