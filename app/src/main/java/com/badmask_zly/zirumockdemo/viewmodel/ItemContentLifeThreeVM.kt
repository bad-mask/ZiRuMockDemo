package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.BaseApplication
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/9/4.
 */
class ItemContentLifeThreeVM(mybean: List<MinSuAndLifeContentItem>?) : RecyclerItemVM<List<MinSuAndLifeContentItem>?>() {


    val layoutManager: GridLayoutManager = GridLayoutManager(BaseApplication.instance.appContext,2)
    var recyclerviewData: ObservableArrayList<MinSuAndLifeContentItem> = ObservableArrayList()
    val itemVMFactory: LifeItemVMFactory = LifeItemVMFactory()

    init {
        recyclerviewData.addAll(mybean!!)//seem to be a bug , this code must be there
    }

    override fun loadItemView(): Int = R.layout.item_life_content_three


    class LifeItemVMFactory : ItemVMFactory<MinSuAndLifeContentItem>() {
        override fun getItemVM(viewType: Int): RecyclerItemVM<MinSuAndLifeContentItem> = ItemContentLifeThreeVMItem()
    }
}