package com.loasan.lucky.ui

import com.loasan.lucky.R
import java.util.*
import kotlin.collections.ArrayList


class HeadPortraitList {
    companion object{
        val headPortraitList = ArrayList<Int>()
        init {
            headPortraitList.add(R.drawable.avatar_allan_munger)
            headPortraitList.add(R.drawable.avatar_amanda_brady)
            headPortraitList.add(R.drawable.avatar_ashley_mccarthy)
            headPortraitList.add(R.drawable.avatar_carlos_slattery)
            headPortraitList.add(R.drawable.avatar_carole_poland)
            headPortraitList.add(R.drawable.avatar_cecil_folk)
            headPortraitList.add(R.drawable.avatar_celeste_burton)
            headPortraitList.add(R.drawable.avatar_charlotte_waltson)
            headPortraitList.add(R.drawable.avatar_colin_ballinger)
            headPortraitList.add(R.drawable.avatar_daisy_phillips)
            headPortraitList.add(R.drawable.avatar_elliot_woodward)
            headPortraitList.add(R.drawable.avatar_tim_deboer)
            headPortraitList.add(R.drawable.avatar_elvia_atkins)
            headPortraitList.add(R.drawable.avatar_erik_nason)
            headPortraitList.add(R.drawable.avatar_henry_brill)
            headPortraitList.add(R.drawable.avatar_isaac_fielder)
            headPortraitList.add(R.drawable.avatar_johnie_mcconnell)
            headPortraitList.add(R.drawable.avatar_kat_larsson)
            headPortraitList.add(R.drawable.avatar_katri_ahokas)
            headPortraitList.add(R.drawable.avatar_kevin_sturgis)
            headPortraitList.add(R.drawable.avatar_lydia_bauer)
            headPortraitList.add(R.drawable.avatar_mauricio_august)
            headPortraitList.add(R.drawable.avatar_mona_kane)
            headPortraitList.add(R.drawable.avatar_robert_tolbert)
            headPortraitList.add(R.drawable.avatar_robin_counts)
            headPortraitList.add(R.drawable.avatar_tim_deboer)
            headPortraitList.add(R.drawable.avatar_wanda_howard)
        }

        fun getHeadPortrait(): Int {
            return headPortraitList[Random().nextInt(headPortraitList.size)]
        }
    }

}