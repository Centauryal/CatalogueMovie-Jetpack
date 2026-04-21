package com.centaury.domain

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/2/2020.
 */
/// for one-shot operations
abstract class UseCase<out T, in Params> {

    abstract suspend fun execute(params: Params): T

    class None
}
