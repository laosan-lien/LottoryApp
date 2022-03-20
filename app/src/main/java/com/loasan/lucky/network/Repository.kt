package com.loasan.lucky.network

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers


object Repository {

    fun startSession() = liveData(Dispatchers.IO) {
        val result = try {
            val luckyResponse = LuckNetwork.startSession()
            if (luckyResponse.status == "SUCCESS") {
                val luckDogList = luckyResponse.luckDogList
                Result.success(luckDogList)
            } else {
                Result.failure(RuntimeException("response status is ${luckyResponse.status}"))
            }
            //TODO:可以缩小一下异常范围
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }


    fun getProb() = liveData(Dispatchers.IO) {
        val result = try {
            val luckyResponse = LuckNetwork.getProb()
            if (luckyResponse.status == "SUCCESS") {
                val luckDogList = luckyResponse.luckDogList
                Result.success(luckDogList)
            } else {
                Result.failure(RuntimeException("response status is ${luckyResponse.status}"))
            }
            //TODO:可以缩小一下异常范围
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }

    fun getDrawResult() = liveData(Dispatchers.IO) {
        val result = try {
            val luckyResponse = LuckNetwork.getDrawResult()
            if (luckyResponse.status == "SUCCESS") {
                val luckDogList = luckyResponse.luckDogList
                Result.success(luckDogList)
            } else {
                Result.failure(RuntimeException("response status is ${luckyResponse.status}"))
            }
            //TODO:可以缩小一下异常范围
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }


    fun submitSession() = liveData(Dispatchers.IO) {
        val result = try {
            val luckyResponse = LuckNetwork.submitSession()
            if (luckyResponse.status == "SUCCESS") {
                Result.success("SUCCESS")
            } else {
                Result.failure(RuntimeException("response status is ${luckyResponse.status}"))
            }
            //TODO:可以缩小一下异常范围
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)

    }
}