package com.centaury.cataloguemovie.utils

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/10/2020.
 */
sealed class LoaderState {
    object ShowLoading : LoaderState()
    object HideLoading : LoaderState()
}