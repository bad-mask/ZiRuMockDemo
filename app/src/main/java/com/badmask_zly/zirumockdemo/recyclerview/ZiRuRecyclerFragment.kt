package com.badmask_zly.zirumockdemo.recyclerview

import android.databinding.Observable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import com.badmask_zly.zirumockdemo.R
import com.badmask_zly.zirumockdemo.base.ZiRuFragment
import com.badmask_zly.zirumockdemo.databinding.ZrRecyclerViewBinding
import java.lang.ref.WeakReference

/**
 * Created by badmask_zly on 2017/8/2.
 *
 * recyclerView 的 Fragment
 * 使用规范：此fragment 呈现的数据需要访问 fragment 页面对应的数据
 */
open class ZiRuRecyclerFragment<T> : ZiRuFragment<ZiRuRecyclerVM<T>, ZrRecyclerViewBinding>() {

    lateinit var adapter: ZiRuRecyclerAdapter<T>
    var cacheHeaderLayout: Int = 0
    var cacheFooterLayout: Int = 0

    /**
     * 设置 viewModel
     */
    fun setViewModel(recyclerVM: ZiRuRecyclerVM<T>): ZiRuRecyclerFragment<T> {

        viewModel = recyclerVM

        /**
         * 此时还没有初始化 adapter，先将 HeaderLayout 缓存起来
         */
        if (0 != viewModel.headerLayout.get()) {
            cacheHeaderLayout = viewModel.headerLayout.get()
        }
        /**
         * 此时还没有初始化 adapter，先将 FooterLayout 缓存起来
         */
        if (0 != viewModel.footerLayout.get()) {
            cacheFooterLayout = viewModel.footerLayout.get()
        }

        viewModel.headerLayout.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                if (null != adapter) adapter?.setHeaderViewResForRecyclerView(viewModel.headerLayout.get()) else cacheHeaderLayout = viewModel.headerLayout.get()
            }
        })
        viewModel.footerLayout.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(p0: Observable?, p1: Int) {
                if (null != adapter) adapter?.setFooterViewResForRecyclerView(viewModel.footerLayout.get()) else cacheFooterLayout = viewModel.footerLayout.get()
            }
        })
        return this
    }

    override fun loadViewModel(): ZiRuRecyclerVM<T> = viewModel

    override fun loadLayoutId(): Int = R.layout.zr_recycler_view

    override fun initialize() {
        adapter = ZiRuRecyclerAdapter(activity, viewModel, viewDataBinding.zrRecyclerView)
        if (0 != cacheHeaderLayout) {
            adapter?.setHeaderViewResForRecyclerView(cacheHeaderLayout)
        }
        if (0 != cacheFooterLayout) {
            adapter?.setFooterViewResForRecyclerView(cacheHeaderLayout)
        }

        val manager: RecyclerView.LayoutManager = viewModel.getLayoutManager(WeakReference<RecyclerView>(viewDataBinding.zrRecyclerView))
        // 设置 LayoutManager ，控制 item 的展示样式
        viewDataBinding.zrRecyclerView.layoutManager = manager
        // 设置 ItemDecoration (控制 item 的展示样式)
        val decoration: RecyclerView.ItemDecoration? = viewModel.getItemDecoration(WeakReference(viewDataBinding.zrRecyclerView))
        if (decoration != null) {
            viewDataBinding.zrRecyclerView.addItemDecoration(decoration)
        }
        //设置下拉刷新组件
        viewDataBinding.zrRecyclerSwipeRefresh.setColorSchemeResources(R.color.colorPrimary)
        viewDataBinding.zrRecyclerSwipeRefresh.setSize(SwipeRefreshLayout.LARGE)
        viewDataBinding.zrRecyclerView.itemAnimator = null
        viewDataBinding.zrRecyclerSwipeRefresh.setOnRefreshListener {
            viewDataBinding.zrRecyclerSwipeRefresh.isRefreshing = true
        }
        //获取数据
        viewModel.fetchData()
        viewDataBinding.zrRecyclerView.adapter = adapter


    }
}


























