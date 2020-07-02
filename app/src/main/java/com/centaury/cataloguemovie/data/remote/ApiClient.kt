package com.centaury.cataloguemovie.data.remote

import com.centaury.cataloguemovie.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Centaury on 7/1/2020.
 */
object ApiClient {
    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

        return retrofit.create(ApiInterface::class.java)
    }
}