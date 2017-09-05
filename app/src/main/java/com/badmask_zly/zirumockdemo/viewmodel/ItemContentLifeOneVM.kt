package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import android.support.v7.widget.LinearLayoutManager
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.BaseApplication
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM

/**
 * Created by badmask_zly on 2017/9/4.
 */
class ItemContentLifeOneVM(mybean: List<MinSuAndLifeContentItem>?) : RecyclerItemVM<List<MinSuAndLifeContentItem>?>() {

    val layoutManager: LinearLayoutManager = LinearLayoutManager(BaseApplication.instance.appContext, LinearLayoutManager.HORIZONTAL, false)
    var recyclerviewData: ObservableArrayList<MinSuAndLifeContentItem> = ObservableArrayList()
    val itemVMFactory: LifeItemVMFactory = LifeItemVMFactory()

    init {
        recyclerviewData.addAll(mybean!!)//seem to be a bug , this code must be there
    }

    override fun loadItemView(): Int = R.layout.item_life_content_one


    class LifeItemVMFactory : ItemVMFactory<MinSuAndLifeContentItem>() {
        override fun getItemVM(viewType: Int): RecyclerItemVM<MinSuAndLifeContentItem> = ItemContentLifeOneVMItem()
    }
}