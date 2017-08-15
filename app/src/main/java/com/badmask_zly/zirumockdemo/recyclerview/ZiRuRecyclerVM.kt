package com.badmask_zly.zirumockdemo.recyclerview

import android.databinding.ObservableArrayList
import android.databinding.ObservableInt
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.badmask_zly.zirumockdemo.base.FetchDataViewModel
import java.lang.ref.WeakReference

/**
 * Created by badmask_zly on 2017/7/26.
 * @description RecyclerView 的 ViewModel
 */
abstract class ZiRuRecyclerVM<T> : FetchDataViewModel() {

    //列表数据
    var beans: ObservableArrayList<T>? = ObservableArrayList()
    //可以在任意位置为 headerLayout 赋值，赋值后该 view 会出现在列表中
    var headerLayout: ObservableInt = ObservableInt()
    //可以在任意位置为 footerLayout 赋值，赋值后该 view 会出现在列表中
    var footerLayout: ObservableInt = ObservableInt()
    //HeaderView
    lateinit var headerView: View
    //FooterView
    lateinit var footerView: View

    //列表点击事件
    open fun onItemClick(viewRef: WeakReference<View>, bean: T, pisition: Int) {}

    //追加数据
    fun addAll(data: List<T>) {
        beans?.addAll(data)
    }

    open fun getItemViewType(position: Int) = 0

    abstract fun getItemVM(viewType: Int): RecyclerItemVM<T>

    fun getLayoutManager(recyclerViewRef: WeakReference<RecyclerView>): RecyclerView.LayoutManager = LinearLayoutManager(recyclerViewRef.get()!!.context)

    fun getItemDecoration(recyclerViewRef: WeakReference<RecyclerView>): RecyclerView.ItemDecoration = DividerItemDecoration(recyclerViewRef.get()!!.context, DividerItemDecoration.VERTICAL_LIST)

}




















