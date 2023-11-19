package com.example.githubapps.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiClient {
    private val okhttp = OkHttpClient.Builder()
        .apply {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loggingInterceptor)

        }
        .readTimeout(25, TimeUnit.SECONDS)
        .writeTimeout(380, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(okhttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubService = retrofit.create<GithubService>()

}