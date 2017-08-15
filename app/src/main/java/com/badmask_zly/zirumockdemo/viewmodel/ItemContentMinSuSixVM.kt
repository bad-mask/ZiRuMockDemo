package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.BaseApplication
import com.badmask_zly.zirumockdemo.bean.MinSuContentItem
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM

/**
 * Created by badmask_zly on 2017/8/15.
 */
class ItemContentMinSuSixVM(mybeans: List<MinSuContentItem>) : RecyclerItemVM<List<MinSuContentItem>?>() {

    val layoutManager: LinearLayoutManager = LinearLayoutManager(BaseApplication.instance.appContext, LinearLayout.HORIZONTAL, false)
    var recyclerviewData: ObservableArrayList<MinSuContentItem> = ObservableArrayList()
    val itemVMFactory: MinSuItemVMFactory = MinSuItemVMFactory()

    init {
        recyclerviewData.addAll(mybeans)
    }

    override fun loadItemView(): Int = R.layout.item_minsu_content_six

    class MinSuItemVMFactory : ItemVMFactory<MinSuContentItem>() {
        override fun getItemVM(viewType: Int): RecyclerItemVM<MinSuContentItem> = ItemContentMinSuFiveVMItem()
    }

}

