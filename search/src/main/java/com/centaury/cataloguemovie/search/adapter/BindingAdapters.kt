package com.centaury.cataloguemovie.search.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.centaury.cataloguemovie.utils.gone
import com.centaury.cataloguemovie.utils.visible

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 11/2/2020.
 */

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    if (isGone) {
        view.gone()
    } else {
        view.visible()
    }
}