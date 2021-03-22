package com.example.skeletmvp.repository.retrofit

import com.example.skeletmvp.repository.retrofit.api.GitApi
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object SimpleRetrofit {
    private const val BASE_URL = "https://api.github.com"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    val api = retrofit.create(GitApi::class.java)
}