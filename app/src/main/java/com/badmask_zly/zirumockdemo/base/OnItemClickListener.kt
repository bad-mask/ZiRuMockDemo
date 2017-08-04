package com.badmask_zly.zirumockdemo.base

import android.view.View

/**
 * Created by badmask_zly on 2017/7/24.
 * 列表数据 item 点击事件接口
 */
interface OnItemClickListener {
    fun onItemClick(view: View, position: Int)
}