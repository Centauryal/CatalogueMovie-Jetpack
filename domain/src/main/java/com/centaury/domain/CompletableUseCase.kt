package com.centaury.domain

import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
abstract class CompletableUseCase<T, Params>(
    private val jobScheduler: Scheduler = Schedulers.io(),
    private val postScheduler: Scheduler = AndroidSchedulers.mainThread()
) {
    private var disposable: Disposable? = null

    abstract fun buildUseCase(params: Params): Completable

    fun execute(params: Params, onSuccess: () -> Unit, onError: (Throwable) -> Unit) {
        disposable = buildUseCase(params)
            .subscribeOn(jobScheduler)
            .observeOn(postScheduler)
            .subscribe(
                { onSuccess() },
                { onError(it) }
            )
    }

    private fun dispose() {
        disposable?.dispose()
    }

    class None
}