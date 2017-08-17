package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.RentContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/17.
 */
class ItemContentRentFiveVM : RecyclerItemVM<RentContentItem>() {

    val mImgHeight = ScreenUtil.getScreenHeight() * 1 / 3

    override fun loadItemView(): Int = R.layout.item_rent_content_five

    override fun item(): RentContentItem? = bean

    fun getImageUrl() = bean?.content!![0].img
}