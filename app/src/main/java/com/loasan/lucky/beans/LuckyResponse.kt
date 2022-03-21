package com.loasan.lucky.beans

data class LuckyResponse(val status: String, val luckDogList: List<LuckDog>)

data class LuckDog(val workNum: String, val name: String, val winProb: Int = 1)

