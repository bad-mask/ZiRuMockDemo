package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/8/1.
 * 自如「合租／整租」首页中，「自如业主俱乐部」模块对应的 item
 */
class ItemContentRentFourVM : RecyclerItemVM<ContentItem>() {

//    主构造函数不能包含任何的代码。初始化的代码可以放到以 init 关键字作为前缀的初始化块「initializer blocks」中
//    init { }

    override fun loadItemView(): Int = R.layout.item_rent_content

    fun getImageHeight() = ScreenUtil.getScreenHeight() * 1 / 5

    fun getImageWidth() = ScreenUtil.getScreenWidth() * 1 / 2

    fun getTextWidth() = ScreenUtil.getScreenWidth() * 1 / 3

    fun getImageUrl() = bean?.img

    fun getTitle() = bean?.title

}