package com.loasan.lucky.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.network.Repository

class ProbViewModel :ViewModel(){

    private val drawLiveData = MutableLiveData<String>()

    val drawList = ArrayList<LuckDog>()

    val drawLiveDataforObseve = Transformations.switchMap(drawLiveData){
        Repository.getProb()
    }

    fun getProb(){
        drawLiveData.value = drawLiveData.value
    }
}