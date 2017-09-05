package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/9/4.
 */
class ItemContentLifeFourVMItem : RecyclerItemVM<MinSuAndLifeContentItem>() {

    override fun loadItemView(): Int = R.layout.item_life_content_four_item

    fun getImageUrl() = bean?.pic

    fun getTitle() = bean?.title

    fun getSubTitle() = bean?.subtitle

    fun getImageHeight() = ScreenUtil.getScreenHeight() * 1 / 3

    fun getItemWidth() = ScreenUtil.getScreenWidth() - ScreenUtil.dip2Px(30f)



}