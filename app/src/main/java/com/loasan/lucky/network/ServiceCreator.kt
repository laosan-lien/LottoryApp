package com.loasan.lucky.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://192.168.101.15:5000/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass :Class<T>) :T = retrofit.create(serviceClass)
    inline fun <reified  T> create():T = create(T::class.java)
}