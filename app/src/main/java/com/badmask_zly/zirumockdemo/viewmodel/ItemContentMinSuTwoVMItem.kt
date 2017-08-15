package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/15.
 */
class ItemContentMinSuTwoVMItem : RecyclerItemVM<MinSuContentItem>() {

    override fun loadItemView(): Int = R.layout.item_minsu_content_two_item

    fun getImageUrl(): String? = bean!!.pic

    fun getImageHeight() = getItemWidth() * 5 / 4

    fun getItemWidth() = ScreenUtil.getScreenWidth() * 1 / 3

    fun getText() = bean!!.title
}