package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/15.
 */
class ItemContentRentFourVMItem : RecyclerItemVM<ContentItem>() {

    override fun loadItemView(): Int = R.layout.item_rent_content_four_item

    fun getImageHeight() = getImageWidth() * 3 / 2

    fun getImageWidth() = ScreenUtil.getScreenWidth() * 3 / 5

    override fun item() = bean
}