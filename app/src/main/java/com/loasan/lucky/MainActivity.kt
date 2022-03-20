package com.loasan.lucky

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        luckProbAdapter = LuckProbAdapter(this,luckProbViewModel.drawList)
        luckProbList.adapter = luckProbAdapter

        beginToDraw.setOnClickListener {
            lastLuckViewModel.startSession()
            luckProbViewModel.getProb()
        }
        lastLuckViewModel.lastLuckLiveDataForObserve.observe(this, Observer { result ->
            val luckList = result.getOrNull()
//            textView.text = luckList?.get(0)?.name
//            textView.text = luckList.toString()
        })


    }
}