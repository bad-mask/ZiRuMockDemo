package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/4.
 */
class ItemContentRentSixVMItem : RecyclerItemVM<ContentItem>() {

    override fun loadItemView(): Int = R.layout.item_rent_content_six_item

    fun getImageHeight() = ScreenUtil.getScreenHeight() * 2 / 5

    override fun item() = bean
}