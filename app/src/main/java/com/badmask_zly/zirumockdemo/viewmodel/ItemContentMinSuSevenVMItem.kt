package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/15.
 */
class ItemContentMinSuSevenVMItem : RecyclerItemVM<MinSuAndLifeContentItem>() {

    override fun loadItemView(): Int = R.layout.item_minsu_content_seven_item

    fun getImageUrl(): String? = bean!!.pic


    fun getImageWidth() = ScreenUtil.getScreenWidth() / 4

}