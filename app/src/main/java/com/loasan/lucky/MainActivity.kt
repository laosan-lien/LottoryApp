package com.loasan.lucky

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.viewmodel.LastLuckViewModel
import com.loasan.lucky.viewmodel.LuckProbViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "Lucky:MainActivity"

class MainActivity : AppCompatActivity() {

    private val lastLuckViewModel by lazy { ViewModelProviders.of(this)[LastLuckViewModel::class.java] }
    private val luckProbViewModel by lazy { ViewModelProviders.of(this)[LuckProbViewModel::class.java] }
    private lateinit var lastLuckAdapter: LastLuckAdapter
    private lateinit var luckProbAdapter: LuckProbAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //显示上次抽中的列表
        val lastLuckLayoutManager = LinearLayoutManager(this)
        lastLuckList.layoutManager = lastLuckLayoutManager
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckViewModel.luckList.add(LuckDog("80151788", "李恩", 0.1))
        lastLuckAdapter = LastLuckAdapter(this, lastLuckViewModel.luckList)
        lastLuckList.adapter = lastLuckAdapter

        lastLuckViewModel.startSession()
        lastLuckViewModel.lastLuckLiveDataForObserve.observe(this, Observer { result ->
            val luckList = result.getOrNull()
            Log.d(TAG, "onCreate: luckList = $luckList")
            if (!luckList.isNullOrEmpty()) {
                lastLuckViewModel.luckList.clear()
                lastLuckViewModel.luckList.addAll(luckList)
                lastLuckAdapter.notifyDataSetChanged()
            }
        })

        //显示奖池中参与抽奖的同事以及概率列表
        val luckProbLayoutManager = LinearLayoutManager(this)
        luckProbList.layoutManager = luckProbLayoutManager
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbViewModel.drawList.add(LuckDog("80151788", "李恩", 0.1))
        luckProbAdapter = LuckProbAdapter(this, luckProbViewModel.drawList)
        luckProbList.adapter = luckProbAdapter
        luckProbAdapter.notifyDataSetChanged()

        luckProbViewModel.getProb()
        luckProbViewModel.drawLiveDataForObserve.observe(this, { result ->
            val luckWithProbList = result.getOrNull()
            Log.d(TAG, "onCreate: luckWithProbList =$luckWithProbList")
            if (!luckWithProbList.isNullOrEmpty()) {
                luckProbViewModel.drawList.clear()
                luckProbViewModel.drawList.addAll(luckWithProbList)
                luckProbAdapter.notifyDataSetChanged()
            }
        })


        //添加成员
        addMember.setOnClickListener {

        }

        //开始抽奖
        beginToDraw.setOnClickListener {
            val winnerIntent = Intent(this, WinnersActivity::class.java)
            startActivity(winnerIntent)
        }
    }
}