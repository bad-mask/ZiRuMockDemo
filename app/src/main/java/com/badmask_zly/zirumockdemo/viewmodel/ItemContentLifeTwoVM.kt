package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.bean.MinSuAndLifeContentItem
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil

/**
 * Created by badmask_zly on 2017/9/4.
 */
class ItemContentLifeTwoVM(mybean: List<MinSuAndLifeContentItem>?) : RecyclerItemVM<List<MinSuAndLifeContentItem>?>() {

    var bannerImages: ObservableArrayList<String> = ObservableArrayList()

    fun getBannerHeight() = ScreenUtil.getScreenHeight() * 1 / 3

    init {
        mybean!!.forEach { bannerImages.add(it.pic) }
    }

    override fun loadItemView(): Int = R.layout.item_life_content_two

}