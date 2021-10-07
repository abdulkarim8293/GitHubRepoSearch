package com.iamkamrul.githubreposearch.di.module

import com.iamkamrul.githubreposearch.data.GithubService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {

    @Singleton
    @Provides
    fun provideGitHubService(retrofit: Retrofit):GithubService{
        return retrofit.create(GithubService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpLoginInterceptor():HttpLoggingInterceptor{
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}