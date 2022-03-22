package com.loasan.lucky

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.viewmodel.SubmitViewModel
import com.loasan.lucky.viewmodel.WinnerViewModel
import kotlinx.android.synthetic.main.activity_winner_result.*

private const val TAG = "Luck:WinnersActivity"

class WinnersActivity : AppCompatActivity() {

    private val winnerViewModel by lazy { ViewModelProviders.of(this)[WinnerViewModel::class.java] }
    private val submitViewModel by lazy { ViewModelProviders.of(this)[SubmitViewModel::class.java] }
    private lateinit var drawResultList: List<LuckDog>
    private lateinit var winnerListAdapter: WinnerListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner_result)

        val winnerLayoutManager = LinearLayoutManager(this)
        winnerList.layoutManager = winnerLayoutManager
        drawResultList = winnerViewModel.winnerList
        winnerListAdapter = WinnerListAdapter(this, winnerViewModel.winnerList)
        winnerList.adapter = winnerListAdapter

        winnerViewModel.getWinnerList()
        winnerViewModel.winnerLiveDataForObserve.observe(this, { result ->
            val winnerResultList = result.getOrNull()
            if (!winnerResultList.isNullOrEmpty()) {
                Log.d(TAG, "onCreate: luckWithProbList =$winnerResultList")
                winnerViewModel.winnerList.clear()
                var i = 0
                val handler = Handler(Looper.getMainLooper())
                for (winner in winnerResultList) {
                    handler.postDelayed(Runnable {

                        winnerViewModel.winnerList.add(winner)
                        winnerListAdapter.notifyDataSetChanged()
                    }, (1000 * i).toLong())
                }
            } else {
                Toast.makeText(this, "抽奖结果获取失败", Toast.LENGTH_SHORT).show()
            }
        })

        submitRes.setOnClickListener {
            //TODO:提交功能，功能未完善
            submitViewModel.submitSession()
            submitViewModel.submitLiveDataForObserve.observe(this, { result ->
                val submitLuckList = result.getOrNull()
                Log.d(TAG, "onCreate: luckWithProbList =$submitLuckList")
                if (!submitLuckList.isNullOrEmpty()) {
                    Toast.makeText(this, "结果已保存", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "结果提交失败", Toast.LENGTH_SHORT).show()

                }
            })
        }
    }

}