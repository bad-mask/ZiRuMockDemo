package com.badmask_zly.zirumockdemo.viewmodel

import android.databinding.ObservableField
import android.view.View
import com.badmask_zly.zirumockdemo.base.ZiRuViewModel
import com.badmask_zly.zirumockdemo.ui.MainActivity

/**
 * Created by badmask_zly on 2017/7/11.
 */

/**
 * 如果该类有一个主构造函数，那么其基类型必须使用基类型的主构造参数就地初始化
 */
class HomeTabVm : ZiRuViewModel() {
    val selectedTab: ObservableField<String> = ObservableField()

    fun clickRent(view: View) {
        selectedTab.set(MainActivity.RENT)
    }

    fun clickZiruyu(view: View) {
        selectedTab.set(MainActivity.ZIRUYU)
    }

    fun clickMinsu(view: View) {
        selectedTab.set(MainActivity.MINSU)
    }

    fun clickLife(view: View) {
        selectedTab.set(MainActivity.LIFE)
    }
}