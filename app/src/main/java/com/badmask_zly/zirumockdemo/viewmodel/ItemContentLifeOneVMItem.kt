package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/9/4.
 */
class ItemContentLifeOneVMItem : RecyclerItemVM<MinSuAndLifeContentItem>() {

    override fun loadItemView(): Int = R.layout.item_life_content_one_item

    fun getImageUrl() = bean?.pic

    fun getImageHeight() = (ScreenUtil.getScreenWidth() - ScreenUtil.dip2Px(64f)) / 3

}