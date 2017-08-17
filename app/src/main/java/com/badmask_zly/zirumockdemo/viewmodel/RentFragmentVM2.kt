package com.badmask_zly.zirumockdemo.viewmodel

import android.support.v7.widget.RecyclerView
import com.badmask_zly.zirumockdemo.bean.*
import com.badmask_zly.zirumockdemo.http.Api
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerVM
import com.google.gson.Gson
import java.lang.ref.WeakReference

/**
 * Created by badmask_zly on 2017/7/20.
 */
class RentFragmentVM2 : ZiRuRecyclerVM<RentContentItem>() {


    companion object {
        val TYPE_ONE = 1
        val TYPE_TWO = 2
        val TYPE_THREE = 3
        val TYPE_FOUR = 4
        val TYPE_FIVE = 5
        val TYPE_SIX = 6
        val TYPE_SEVEN = 7
    }

    override fun getItemVM(viewType: Int): RecyclerItemVM<RentContentItem> = when (viewType) {
        TYPE_ONE -> ItemContentRentOneVM()
        TYPE_TWO -> ItemContentRentTwoVM()
        TYPE_THREE -> ItemContentRentThreeVM(beans?.get(2)!!.content)
        TYPE_FOUR -> ItemContentRentFourVM(beans?.get(3)!!)
        TYPE_FIVE -> ItemContentRentFiveVM()
        TYPE_SIX -> ItemContentRentSixVM(beans?.get(5)!!)
        TYPE_SEVEN -> ItemContentRentSevenVM(beans?.get(6)!!)
        else -> ItemContentRentOneVM()
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

    override fun getItemDecoration(recyclerViewRef: WeakReference<RecyclerView>): RecyclerView.ItemDecoration? = null


    override fun handleData(result: String, status: Boolean) {

        val gson = Gson()
        val rentHome = gson.fromJson(result, RentHome::class.java)
        //data class RentHomeData(val select_name: RentContentItem, val entrust: RentContentItem, val introduce: RentContentItem, val about_ziroom: RentContentItem, val video: RentContentItem, val ziroom_product: RentContentItem, val story: RentContentItem)
        val minsuHomeList: ArrayList<RentContentItem> = arrayListOf(rentHome.data.select_name, rentHome.data.entrust, rentHome.data.introduce, rentHome.data.about_ziroom, rentHome.data.video, rentHome.data.ziroom_product, rentHome.data.story)
        addAll(minsuHomeList)
    }

    override fun loadApiService(): String = Api.Host + Api.getRentHomeDetail


}