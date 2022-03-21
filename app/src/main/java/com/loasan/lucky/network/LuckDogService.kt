package com.loasan.lucky.network

import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.beans.LuckyResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LuckDogService {
    //开启一个抽奖绘画
    @GET("start_session")
    fun startSession(): Call<LuckyResponse>

    //获取中奖概率
    @GET("get_prob")
    fun getProb(): Call<LuckyResponse>

    //获取抽奖结果
    @GET("get_draw_result")
    fun getDrawResult(): Call<LuckyResponse>

    //提交抽奖结果
    @POST("update_people")
    fun updatePeople(@Body luckDog: LuckDog): Call<LuckyResponse>

    @GET("submit_session")
    fun submitSession():Call<LuckyResponse>
}