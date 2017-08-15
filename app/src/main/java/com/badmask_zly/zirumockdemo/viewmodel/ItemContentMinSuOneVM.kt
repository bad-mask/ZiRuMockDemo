package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/15.
 */
class ItemContentMinSuOneVM : RecyclerItemVM<List<MinSuContentItem>?>() {

    override fun loadItemView(): Int = R.layout.item_minsu_content_one

    fun getImageUrl(): String? = bean?.get(0)?.pic

    fun getImageHeight() = ScreenUtil.getScreenHeight() * 1 / 3
}