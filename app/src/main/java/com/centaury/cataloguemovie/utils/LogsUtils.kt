package com.centaury.cataloguemovie.utils

import timber.log.Timber

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 10/15/2020.
 */
object LogsUtils {

    fun d(message: () -> String) {
        if (Timber.treeCount() > 0) {
            try {
                Timber.d(message())
            } catch (ex: Exception) {
                Timber.e(ex)
            }
        }
    }

    fun e(throwable: () -> Throwable) {
        if (Timber.treeCount() > 0) {
            try {
                Timber.d(throwable())
            } catch (ex: Exception) {
                Timber.e(ex)
            }
        }
    }
}