package com.centaury.domain

import kotlinx.coroutines.flow.Flow

/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/27/2020.
 */
/// for Streams and Paging
abstract class FlowableUseCase<out T, in Params> {

    abstract fun execute(params: Params): Flow<T>

    class None
}
