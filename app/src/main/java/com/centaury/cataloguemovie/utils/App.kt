package com.centaury.cataloguemovie.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import coil.load
import com.centaury.cataloguemovie.BuildConfig
import com.centaury.cataloguemovie.R
import timber.log.Timber

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

fun timberE(message: String) {
    Timber.e(message)
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun Activity.showSystemUI() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        window.insetsController?.setSystemBarsAppearance(
            APPEARANCE_LIGHT_STATUS_BARS,
            APPEARANCE_LIGHT_STATUS_BARS
        )
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                    )
        }
    }
}

fun loadFromUrl(imageView: ImageView, url: String) {
    imageView.load(BuildConfig.IMAGE_URL + url) {
        placeholder(R.drawable.ic_loading)
        error(R.drawable.ic_error)
    }
}

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}

private fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities = cm.activeNetwork ?: return false
    val activeNet = cm.getNetworkCapabilities(networkCapabilities) ?: return false

    return when {
        activeNet.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNet.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        activeNet.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
        else -> false
    }
}

private fun showNoInternetDialog(context: Context) {
    val customDialog = AlertDialog.Builder(context)
    val view = LayoutInflater.from(context).inflate(R.layout.item_alert_dialog, null)

    val titleDialog = view.findViewById<TextView>(R.id.alert_title)
    titleDialog.text = context.getString(R.string.txt_no_internet)

    customDialog.apply {
        setView(view)
        setPositiveButton(R.string.btn_ok) { dialog, _ ->
            dialog.dismiss()
            if (!isNetworkConnected(context)) showNoInternetDialog(context)
        }
    }

    val alertDialog = customDialog.create()
    alertDialog.show()
}

fun checkConnection(context: Context) {
    if (!isNetworkConnected(context)) showNoInternetDialog(context)
}