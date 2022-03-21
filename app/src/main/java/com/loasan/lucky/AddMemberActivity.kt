package com.loasan.lucky

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProviders
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.viewmodel.SubmitViewModel
import com.loasan.lucky.viewmodel.UpdateViewModel
import kotlinx.android.synthetic.main.activity_add_member.*

private const val TAG = "Luck:AddMemberActivity"

class AddMemberActivity : AppCompatActivity() {

    private val updateViewModel by lazy { ViewModelProviders.of(this)[UpdateViewModel::class.java] }
    private val submitViewModel by lazy { ViewModelProviders.of(this)[SubmitViewModel::class.java] }
    private  var name: String? = null
    private  var workNum: String? = null
    private var weight: Int = -2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        inputName.addTextChangedListener {
            name = it.toString()
            Log.d(TAG, "onCreate: intput name = $name")
        }
        inputWorkNum.addTextChangedListener {
            workNum = it.toString()
            Log.d(TAG, "onCreate: inputNum = $workNum")
        }
        inputWeight.addTextChangedListener {
            try {
                weight = it.toString().toInt()
                Log.d(TAG, "onCreate: inputWeigth = $weight")
            } catch (e: NumberFormatException) {
                weight = -2
            }
        }

        submitAdd.setOnClickListener {
            //TODO:提交功能，功能未完善
            if(name == null){
                Toast.makeText(this, "请输入正确的名字", Toast.LENGTH_SHORT).show()
            }else if(workNum == null){
                Toast.makeText(this, "请输入正确的工号", Toast.LENGTH_SHORT).show()
            }else if (weight ==  -2){
                Toast.makeText(this, "请输入正确的权重", Toast.LENGTH_SHORT).show()
            }else{
                updateViewModel.updateSession(LuckDog("8023412", "loasan", 1))
                updateViewModel.updateLiveDataForObserve.observe(this, { result ->
                    val updateList = result.getOrNull()
                    Log.d(TAG, "onCreate: luckWithProbList =$updateList")
                    if (!updateList.isNullOrEmpty()) {
                        Toast.makeText(this, "${updateList[0]}成员已添加", Toast.LENGTH_SHORT).show()
                        submitViewModel.submitSession()
                    }else{
                        Toast.makeText(this, "成员添加失败", Toast.LENGTH_SHORT).show()
                    }
                })
                this.finish()
            }
        }

        cancelSubmit.setOnClickListener{
            this.finish()
        }
    }
}