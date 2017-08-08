package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/4.
 */
class ItemContentRentOneVM : RecyclerItemVM<ContentItem>() {

    override fun loadItemView(): Int = R.layout.item_rent_content_one


    fun getImageHeight() = ScreenUtil.getScreenWidth() * 1 / 5

    fun getImageWidth() = ScreenUtil.getScreenWidth() * 1 / 5

    fun getTextWidth() = ScreenUtil.getScreenWidth() * 2 / 5

    override fun item() = bean
}