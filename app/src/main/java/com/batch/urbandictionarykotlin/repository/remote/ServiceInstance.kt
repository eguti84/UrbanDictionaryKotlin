package com.batch.urbandictionarykotlin.repository.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceInstance {
    private const val BASE_URL =
        "https://mashape-community-urban-dictionary.p.rapidapi.com"

    val urbanService: UrbanService
        get() = RetrofitInstanceHolder.INSTANCE.create(UrbanService::class.java)

    private object RetrofitInstanceHolder {
        val INSTANCE: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}