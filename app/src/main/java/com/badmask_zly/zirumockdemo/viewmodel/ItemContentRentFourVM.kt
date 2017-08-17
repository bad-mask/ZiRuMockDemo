package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.BaseApplication
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.bean.RentContentItem
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM

/**
 * Created by badmask_zly on 2017/8/4.
 */
class ItemContentRentFourVM(mybeans: RentContentItem) : RecyclerItemVM<RentContentItem>() {

    override fun loadItemView(): Int = R.layout.item_rent_content_four

    val layoutManager: LinearLayoutManager = LinearLayoutManager(BaseApplication.instance.appContext, LinearLayout.HORIZONTAL, false)

    var recyclerviewData: ObservableArrayList<ContentItem> = ObservableArrayList()
    val itemVMFactory: RentItemVMFactory = RentItemVMFactory()

    init {
        recyclerviewData.addAll(mybeans.content)//seem to be a bug , this code must be there
    }

    override fun item(): RentContentItem? = bean

    class RentItemVMFactory : ItemVMFactory<ContentItem>() {
        override fun getItemVM(viewType: Int): RecyclerItemVM<ContentItem> = ItemContentRentFourVMItem()
    }
}