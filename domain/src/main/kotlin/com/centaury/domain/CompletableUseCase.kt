package com.centaury.domain


/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */

/// for operations with no return value
abstract class CompletableUseCase<in Params> {
    abstract suspend fun execute(params: Params)
    class None
}
