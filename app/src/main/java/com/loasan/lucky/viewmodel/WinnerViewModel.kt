package com.loasan.lucky.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.network.Repository

class WinnerViewModel :ViewModel(){

    private val winnerLiveData = MutableLiveData<String>()

    val drawList = ArrayList<LuckDog>()

    val winnerLiveDataforObseve = Transformations.switchMap(winnerLiveData){
        Repository.submitSession()
    }

    fun submitSession(){
        winnerLiveData.value = winnerLiveData.value
    }
}