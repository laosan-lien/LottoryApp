package com.loasan.lucky.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.network.Repository

class LastLuckViewModel : ViewModel() {

    private val lastLuckLiveData = MutableLiveData<String>()

    //保存Ui上现有的数据，防止转屏丢失
    val luckList = ArrayList<LuckDog>()
    val lastLuckLiveDataForObserve = Transformations.switchMap(lastLuckLiveData) {
        Repository.startSession()
    }

    fun startSession() {
        lastLuckLiveData.value = lastLuckLiveData.value
    }
}