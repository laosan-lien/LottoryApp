package com.loasan.lucky.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "Luck:ServiceCreator"

object ServiceCreator {

    private const val BASE_URL = "http://IP:port/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass :Class<T>) :T = retrofit.create(serviceClass)
    inline fun <reified  T> create():T = create(T::class.java)
}