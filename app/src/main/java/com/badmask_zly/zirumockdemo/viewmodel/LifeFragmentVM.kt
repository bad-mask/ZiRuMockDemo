package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.bean.LifeHome
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.http.Api
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerVM
import com.google.gson.Gson

/**
 * Created by badmask_zly on 2017/7/20.
 */
class LifeFragmentVM : ZiRuRecyclerVM<List<MinSuAndLifeContentItem>?>() {

    companion object {
        val TYPE_ONE = 1
        val TYPE_TWO = 2
        val TYPE_THREE = 3
        val TYPE_FOUR = 4
    }


    override fun handleData(result: String, status: Boolean) {
        super.handleData(result, status)
        val gson = Gson()
        val lifeHome: LifeHome = gson.fromJson(result, LifeHome::class.java)
        val lifeHomeList = arrayListOf<List<MinSuAndLifeContentItem>>(lifeHome.data.upinEntrance, lifeHome.data.lunboBanner, lifeHome.data.hotRecommend, lifeHome.data.severStory)
        addAll(lifeHomeList)
    }

    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TYPE_ONE
        1 -> TYPE_TWO
        2 -> TYPE_THREE
        3 -> TYPE_FOUR
        else -> TYPE_FOUR
    }


    override fun getItemVM(viewType: Int): RecyclerItemVM<List<MinSuAndLifeContentItem>?> = when (viewType) {
        TYPE_ONE -> ItemContentLifeOneVM(beans?.get(0)!!)
        TYPE_TWO -> ItemContentLifeTwoVM(beans?.get(1)!!)
        TYPE_THREE -> ItemContentLifeThreeVM(beans?.get(2)!!)

//        RentFragmentVM2.TYPE_TWO -> ItemContentRentTwoVM()
//        RentFragmentVM2.TYPE_THREE -> ItemContentRentThreeVM(beans?.get(2)!!.content)
//        RentFragmentVM2.TYPE_FOUR -> ItemContentRentFourVM(beans?.get(3)!!)
        else -> ItemContentLifeOneVM(beans?.get(0)!!)
    }

    override fun loadApiService(): String = Api.Host + Api.getLifeHomeDetail
}


















