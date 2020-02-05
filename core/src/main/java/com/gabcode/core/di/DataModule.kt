package com.gabcode.core.di

import com.gabcode.core.BuildConfig
import com.gabcode.core.data.remote.ApiService
import com.gabcode.core.data.remote.RestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient = RestClient.createHttpClient()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String = BuildConfig.API_HOST, client: OkHttpClient): Retrofit =
        RestClient.createRetrofit(baseUrl, client)

    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit, service: Class<ApiService> = ApiService::class.java): ApiService =
            RestClient.createService(retrofit, service)
}