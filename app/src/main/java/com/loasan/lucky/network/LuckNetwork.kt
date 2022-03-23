package com.loasan.lucky.network

import android.util.Log
import com.loasan.lucky.beans.LuckDog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

private const val TAG = "Luck:LuckNetwork"

object LuckNetwork {

    private val luckService = ServiceCreator.create(LuckDogService::class.java)

    suspend fun startSession() = luckService.startSession().await()

    suspend fun getProb() = luckService.getProb().await()

    suspend fun getDrawResult() = luckService.getDrawResult().await()

    suspend fun updateSession(luckDog: LuckDog) = luckService.updatePeople(luckDog).await()

    suspend fun submitSession() = luckService.submitSession().await()


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    Log.d(TAG, "onResponse:${response.body()}")
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
            })
        }
    }

}