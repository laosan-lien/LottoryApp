package com.loasan.lucky.network

import android.widget.Toast
import androidx.lifecycle.liveData
import com.loasan.lucky.LuckyApplication
import com.loasan.lucky.beans.LuckDog
import kotlinx.coroutines.Dispatchers

private const val TAG = "Luck:Repository"


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

    fun updatePeople(luckDog: LuckDog) = liveData(Dispatchers.IO) {
        val result = try {
            val luckyResponse = LuckNetwork.updateSession(luckDog)
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
}