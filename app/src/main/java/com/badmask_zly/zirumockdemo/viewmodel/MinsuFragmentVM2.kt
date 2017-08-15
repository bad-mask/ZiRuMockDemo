package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.bean.MinSuContentItem
import com.badmask_zly.zirumockdemo.bean.MinSuHome
import com.badmask_zly.zirumockdemo.http.Api
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerVM
import com.google.gson.Gson

/**
 * Created by badmask_zly on 2017/7/20.
 */
class MinsuFragmentVM2 : ZiRuRecyclerVM<List<MinSuContentItem>?>() {

    companion object {
        val TYPE_ONE = 1
        val TYPE_TWO = 2
        val TYPE_THREE = 3
        val TYPE_FOUR = 4
        val TYPE_FIVE = 5
        val TYPE_SIX = 6
        val TYPE_SEVEN = 7
    }

    override fun getItemVM(viewType: Int): RecyclerItemVM<List<MinSuContentItem>?> = when (viewType) {
        TYPE_ONE -> ItemContentMinSuOneVM()
        TYPE_TWO -> ItemContentMinSuTwoVM(beans?.get(1)!!)
        TYPE_THREE -> ItemContentMinSuThreeVM(beans?.get(2)!!)
        TYPE_FOUR -> ItemContentMinSuOneVM()//使用 banner ，稍后写
        TYPE_FIVE -> ItemContentMinSuFiveVM(beans?.get(4)!!)
        TYPE_SIX -> ItemContentMinSuSixVM(beans?.get(5)!!)
        TYPE_SEVEN -> ItemContentMinSuSevenVM(beans?.get(6)!!)
        else -> ItemContentMinSuOneVM()
    }


    override fun getItemViewType(position: Int): Int = when (position) {
        0 -> TYPE_ONE
        1 -> TYPE_TWO
        2 -> TYPE_THREE
        3 -> TYPE_FOUR
        4 -> TYPE_FIVE
        5 -> TYPE_SIX
        6 -> TYPE_SEVEN
        else -> TYPE_SEVEN
    }


//    init {
//        fetchData()
//    }

    override fun handleData(result: String, status: Boolean) {

        val gson: Gson = Gson()
        val minsuHome: MinSuHome = gson.fromJson(result, MinSuHome::class.java)
        val minsuHomeList: ArrayList<List<MinSuContentItem>?> = arrayListOf(minsuHome.data.syt, minsuHome.data.mdd, minsuHome.data.zry, minsuHome.data.jchd, minsuHome.data.hxzl, minsuHome.data.fdgs, minsuHome.data.ppg)
        addAll(minsuHomeList)
    }

    override fun loadApiService(): String = Api.Host + Api.getMinSuHomeDetail


}