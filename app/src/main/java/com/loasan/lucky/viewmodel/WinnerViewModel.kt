package com.loasan.lucky.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.network.Repository

private const val TAG = "Luck:WinnerViewModel"

class WinnerViewModel :ViewModel(){

    private val winnerLiveData = MutableLiveData<String>()

    val winnerList = ArrayList<LuckDog>()

    val winnerLiveDataForObserve = Transformations.switchMap(winnerLiveData){
        Repository.getDrawResult()
    }

    fun getWinnerList(){
        winnerLiveData.value = winnerLiveData.value
    }
}