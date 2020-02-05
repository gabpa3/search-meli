package com.gabcode.core.di

import com.gabcode.core.BuildConfig
import com.gabcode.core.data.remote.ApiService
import com.gabcode.core.data.remote.RestClient
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
object DataModule {

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @JvmStatic
    @Provides
    fun provideHttpClient(): OkHttpClient = RestClient.createHttpClient()

    @JvmStatic
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        RestClient.createRetrofit(BuildConfig.API_HOST, client)

    @JvmStatic
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}