package com.centaury.cataloguemovie.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import coil.api.load
import com.centaury.cataloguemovie.BuildConfig

/**
 * Created by Centaury on 3/26/2020.
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.showToast(@StringRes res: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, this.getText(res), duration).show()
}

fun Context.showToast(res: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, res, duration).show()
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadFromUrl(imageView: ImageView, url: String) {
    imageView.load(BuildConfig.IMAGE_URL + url)
}

fun ImageView.loadFromUrl(
    imageView: ImageView,
    url: String,
    @DrawableRes drawableResId: Int,
    @DrawableRes drawableErrorResId: Int
) {
    imageView.load(BuildConfig.IMAGE_URL + url) {
        placeholder(drawableResId)
        error(drawableErrorResId)
    }
}

fun ImageView.loadFromUrl(
    imageView: ImageView,
    url: String,
    drawable: Drawable,
    drawableError: Drawable
) {
    imageView.load(BuildConfig.IMAGE_URL + url) {
        placeholder(drawable)
        error(drawableError)
    }
}