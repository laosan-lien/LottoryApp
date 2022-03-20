package com.loasan.lucky

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.viewmodel.SubmitViewModel
import com.loasan.lucky.viewmodel.WinnerViewModel
import kotlinx.android.synthetic.main.activity_draw_result.*

private const val TAG = "Luck:WinnersActivity"


class WinnersActivity : AppCompatActivity() {

    private val winnerViewModel by lazy { ViewModelProviders.of(this)[WinnerViewModel::class.java] }
    private val submitViewModel by lazy { ViewModelProviders.of(this)[SubmitViewModel::class.java] }
    private lateinit var drawResultList: List<LuckDog>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_result)

        val winnerLayoutManager = LinearLayoutManager(this)
        winnerList.layoutManager = winnerLayoutManager
        drawResultList = winnerViewModel.winnerList
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))
        winnerViewModel.winnerList.add(LuckDog("80151788", "李恩", 0.1))

        val drawResultAdapter = DrawResultAdapter(this, winnerViewModel.winnerList)
        winnerList.adapter = drawResultAdapter


        submitRes.setOnClickListener {
            submitViewModel.submitSession()
            submitViewModel.submitLiveDataForObserve.observe(this, { result ->
                val luckWithProbList = result.getOrNull()
                Log.d(TAG, "onCreate: luckWithProbList =$luckWithProbList")
                if (!luckWithProbList.isNullOrEmpty()) {
                    Toast.makeText(this, "结果已保存",Toast.LENGTH_SHORT).show()
                }
            })
        }


    }
    9/5000
    Added the function of submitting results

}