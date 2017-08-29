package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/15.
 */
class ItemContentMinSuFiveVMItem : RecyclerItemVM<MinSuAndLifeContentItem>() {

    override fun loadItemView(): Int = R.layout.item_minsu_content_five_item

    fun getImageUrl(): String? = bean!!.pic

    fun getImageHeight() = ScreenUtil.getScreenHeight() * 1 / 3

    fun getItemWidth() = ScreenUtil.getScreenWidth() - ScreenUtil.dip2Px(30f)

}