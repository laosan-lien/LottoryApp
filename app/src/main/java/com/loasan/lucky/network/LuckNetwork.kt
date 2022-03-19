package com.loasan.lucky.network

import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object LuckNetwork {

    private val luckService = ServiceCreator.create(LuckDogService::class.java)

    suspend fun startSession() = luckService.startSession().await()

    suspend fun getProb() = luckService.getProb().await()

    suspend fun getDrawResult() = luckService.getDrawResult().await()

    suspend fun submitSession() = luckService.submitSession().await()


    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
            })
        }
    }

}