package com.loasan.lucky

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.loasan.lucky.viewmodel.LastLuckViewModel
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "Lucky:MainActivity"

class MainActivity : AppCompatActivity() {

    private val luckViewModel by lazy { ViewModelProviders.of(this)[LastLuckViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData.setOnClickListener {
            luckViewModel.startSession()
        }
        luckViewModel.lastLuckLiveDataForObserve.observe(this, Observer { result ->
            val luckList = result.getOrNull()
            textView.text = luckList?.get(0)?.name
        })


    }
}