package com.loasan.lucky.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.network.Repository

class SubmitViewModel:ViewModel() {
    private val submitLiveData = MutableLiveData<String>()

    val drawList = ArrayList<LuckDog>()

    val submitLiveDataforObseve = Transformations.switchMap(submitLiveData){
        Repository.submitSession()
    }

    fun submitSession(){
        submitLiveData.value = submitLiveData.value
    }

}