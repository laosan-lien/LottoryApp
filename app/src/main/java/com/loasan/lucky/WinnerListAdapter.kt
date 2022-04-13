package com.loasan.lucky

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.loasan.lucky.beans.LuckDog
import com.loasan.lucky.ui.HeadPortraitList

private const val TAG = "Luck:DrawResultAdapter"

class WinnerListAdapter(private val context: Context, private val luckDogList: List<LuckDog>) :
    RecyclerView.Adapter<WinnerListAdapter.ViewHolder>() {

    private val headPortraitList = arrayListOf(R.drawable.look_head)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headPortrait: ImageView = view.findViewById(R.id.headPortrait)
        val name: TextView = view.findViewById(R.id.name)
        val workNum: TextView = view.findViewById(R.id.workNum)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.luck_dog_item, parent, false)
        return ViewHolder(view)

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.headPortrait.setImageDrawable(context.resources.getDrawable(HeadPortraitList.getHeadPortrait(luckDogList[position].name)))
        holder.name.text  = luckDogList[position].name
        holder.workNum.text = luckDogList[position].workNum
    }

    override fun getItemCount(): Int = luckDogList.size

}