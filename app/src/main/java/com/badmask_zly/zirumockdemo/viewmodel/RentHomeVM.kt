package com.badmask_zly.zirumockdemo.viewmodel

import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.bean.RentHome
import com.badmask_zly.zirumockdemo.http.Api
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.recyclerview.ZiRuRecyclerVM
import com.google.gson.Gson

/**
 * Created by badmask_zly on 2017/8/2.
 */
class RentHomeVM : ZiRuRecyclerVM<ContentItem>() {

    override fun getItemVM(viewType: Int): RecyclerItemVM<ContentItem> = ItemContentRentVM()

    override fun handleData(result: String, status: Boolean) {

        if (status) {
            val gson = Gson()
            val rentHome = gson.fromJson(result, RentHome::class.java)
            addAll(rentHome.data.story.content)
        }

    }

    override fun loadApiService(): String = Api.Host + Api.getRentHomeDetail
}