package com.loasan.lucky

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.viewmodel.LastLuckViewModel
import com.loasan.lucky.viewmodel.LuckProbViewModel
import com.loasan.lucky.viewmodel.SubmitViewModel
import com.loasan.lucky.viewmodel.UpdateViewModel
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
            val addMemberIntent = Intent(this, AddMemberActivity::class.java)
            startActivity(addMemberIntent)
        }

        //开始抽奖
        beginToDraw.setOnClickListener {
            val winnerIntent = Intent(this, WinnersActivity::class.java)
            startActivity(winnerIntent)
        }
    }
}