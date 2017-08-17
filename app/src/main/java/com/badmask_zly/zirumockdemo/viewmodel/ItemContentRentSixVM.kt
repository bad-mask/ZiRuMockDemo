package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import android.support.v7.widget.LinearLayoutManager
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.BaseApplication
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.bean.RentContentItem
import com.badmask_zly.zirumockdemo.recyclerview.DividerItemDecoration
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM

/**
 * Created by badmask_zly on 2017/8/4.
 */
class ItemContentRentSixVM(mybean: RentContentItem) : RecyclerItemVM<RentContentItem>() {

    val layoutManager: LinearLayoutManager = LinearLayoutManager(BaseApplication.instance.appContext)
    var recyclerviewData: ObservableArrayList<ContentItem> = ObservableArrayList()
    val itemVMFactory: RentItemVMFactory = RentItemVMFactory()
    val mItemDecoration: DividerItemDecoration = DividerItemDecoration(BaseApplication.instance.appContext, DividerItemDecoration.VERTICAL_LIST)

    init {
        recyclerviewData.addAll(mybean.content)//seem to be a bug , this code must be there
    }

    override fun loadItemView(): Int = R.layout.item_rent_content_six


    fun getTitle(): String? = bean?.title
    fun getSubtitle(): String? = bean?.subtitle

    class RentItemVMFactory : ItemVMFactory<ContentItem>() {
        override fun getItemVM(viewType: Int): RecyclerItemVM<ContentItem> = ItemContentRentSixVMItem()
    }

}