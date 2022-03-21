package com.loasan.lucky

import android.annotation.SuppressLint
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
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "Luck:WinnersActivity"

class WinnersActivity : AppCompatActivity() {

    private val winnerViewModel by lazy { ViewModelProviders.of(this)[WinnerViewModel::class.java] }
    private val submitViewModel by lazy { ViewModelProviders.of(this)[SubmitViewModel::class.java] }
    private lateinit var drawResultList: List<LuckDog>

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_result)

        val winnerLayoutManager = LinearLayoutManager(this)
        winnerList.layoutManager = winnerLayoutManager
        drawResultList = winnerViewModel.winnerList
        val winnerListAdapter = WinnerListAdapter(this, drawResultList)
        winnerList.adapter = winnerListAdapter
        winnerListAdapter.notifyDataSetChanged()

        winnerViewModel.getWinnerList()
        winnerViewModel.winnerLiveDataForObserve.observe(this, { result ->
            val luckWithProbList = result.getOrNull()
            Log.d(TAG, "onCreate: luckWithProbList =$luckWithProbList")
            if (!luckWithProbList.isNullOrEmpty()) {
                winnerViewModel.winnerList.clear()
                winnerViewModel.winnerList.addAll(luckWithProbList)
                winnerListAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this ,"抽奖成员名单获取失败",Toast.LENGTH_SHORT).show()
            }
        })

        val drawResultAdapter = WinnerListAdapter(this, winnerViewModel.winnerList)
        winnerList.adapter = drawResultAdapter

        submitRes.setOnClickListener {
            //TODO:提交功能，功能未完善
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

}