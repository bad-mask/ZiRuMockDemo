package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.RentContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM

/**
 * Created by badmask_zly on 2017/8/17.
 */
class ItemContentRentTwoVM : RecyclerItemVM<RentContentItem>() {

    override fun loadItemView(): Int = R.layout.item_rent_content_two

    fun getTitle(): String? = bean?.title
    fun getSubtitle(): String? = bean?.subtitle
}