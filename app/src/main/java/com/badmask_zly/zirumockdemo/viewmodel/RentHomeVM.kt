package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import android.support.v7.widget.GridLayoutManager
import com.badmask_zly.zirumockdemo.base.BaseApplication
import com.badmask_zly.zirumockdemo.base.FetchDataViewModel
import com.badmask_zly.zirumockdemo.bean.ContentItem
import com.badmask_zly.zirumockdemo.bean.RentHome
import com.badmask_zly.zirumockdemo.http.Api
import com.badmask_zly.zirumockdemo.recyclerview.ItemVMFactory
import com.badmask_zly.zirumockdemo.recyclerview.RecyclerItemVM
import com.badmask_zly.zirumockdemo.utils.ScreenUtil
import com.google.gson.Gson

/**
 * Created by badmask_zly on 2017/8/2.
 *
 * 记录一下遗留问题：
 *
 */
class RentHomeVM : FetchDataViewModel() {

    val mScrollViewHeight = ScreenUtil.getScreenHeight() - ScreenUtil.dip2Px(100f)
    var recyclerviewData1: ObservableArrayList<ContentItem> = ObservableArrayList()
    val itemVMFactory: MyItemVMFactory = MyItemVMFactory()
    val layoutManager: GridLayoutManager = GridLayoutManager(BaseApplication.instance.appContext, 2)

    var img: ObservableField<String> = ObservableField()
    var mTitleEntrust: ObservableField<String> = ObservableField()
    var mSubtitleEntrust: ObservableField<String> = ObservableField()
    val mHeaderImgHeight = ScreenUtil.getScreenHeight() * 1 / 3


    init {
        fetchData()
    }

    override fun handleData(result: String, status: Boolean) {
        if (status) {
            //数据获取成功
            val gson = Gson()
            val rentHome = gson.fromJson(result, RentHome::class.java)
            img.set(rentHome.data.select_name.content[0].img)
            mTitleEntrust.set(rentHome.data.entrust.title)
            mSubtitleEntrust.set(rentHome.data.entrust.subtitle)
            recyclerviewData1.addAll(rentHome!!.data.introduce.content)

        }
    }

    override fun loadApiService(): String = Api.Host + Api.getRentHomeDetail

    class MyItemVMFactory : ItemVMFactory<ContentItem>() {
        override fun getItemVM(viewType: Int): RecyclerItemVM<ContentItem> = ItemContentRentOneVM()
    }
}