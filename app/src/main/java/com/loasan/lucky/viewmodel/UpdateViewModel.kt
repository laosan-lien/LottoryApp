package com.loasan.lucky.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.network.Repository

private const val TAG = "Luck:UpdateViewModel"

class UpdateViewModel : ViewModel() {
    private val updateLiveData = MutableLiveData<String>()
    private lateinit var luckDog:LuckDog

    val updateList = ArrayList<LuckDog>()

    val updateLiveDataForObserve = Transformations.switchMap(updateLiveData){
        Repository.updatePeople(this.luckDog)
    }

    fun updateSession(luckDog: LuckDog) {
        this.luckDog = luckDog
        updateLiveData.value = updateLiveData.value
    }

}