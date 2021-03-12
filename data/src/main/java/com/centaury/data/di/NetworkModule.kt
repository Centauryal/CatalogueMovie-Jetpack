package com.centaury.data.di

import android.content.Context
import com.centaury.data.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * @Author Centaury (alfa.arnialfa@gmail.com)
 * Created by Centaury on 7/3/2020.
 */
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val file = File(context.filesDir, DEFAULT_CACHE_FILE_NAME)
        return Cache(file, DEFAULT_CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        cache: Cache
    ): OkHttpClient {
        val hostname = BuildConfig.HOSTNAME
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()

        return OkHttpClient.Builder()
            .connectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.MILLISECONDS)
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { addQueryParam(it) }
            .certificatePinner(certificatePinner)
            .build()
    }


    private fun addQueryParam(it: Interceptor.Chain): Response {
        var request = it.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", languageTag())
            .build()
        request = request
            .newBuilder()
            .url(url)
            .build()
        return it.proceed(request)
    }

    private fun languageTag(): String = Locale.getDefault().toLanguageTag()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)

    companion object {
        private const val DEFAULT_CACHE_FILE_NAME = "okHttp-cache"
        private const val DEFAULT_CACHE_SIZE: Long = 1024 * 1024
        private const val DEFAULT_CONNECT_TIME_OUT: Long = 30 * 1000
        private const val DEFAULT_READ_TIME_OUT: Long = 30 * 1000
    }
}