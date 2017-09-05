package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/9/4.
 */
class ItemContentLifeThreeVMItem : RecyclerItemVM<MinSuAndLifeContentItem>() {

    override fun loadItemView(): Int = R.layout.item_life_content_three_item

    fun getImageUrl() = bean?.pic


}