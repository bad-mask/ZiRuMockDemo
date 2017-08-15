package com.badmask_zly.zirumockdemo.recyclerview

import com.badmask_zly.zirumockdemo.base.ZiRuViewModel

/**
 * Created by badmask_zly on 2017/7/24.
 */
open abstract class RecyclerItemVM<T> : ZiRuViewModel() {
    var bean: T? = null
    var position = 0
    var beans: List<T>? = null

    open fun setData(beans: List<T>?, bean: T, position: Int) {
        this.beans = beans
        this.bean = bean
        this.position = position
    }

    abstract fun loadItemView(): Int

    open fun item() = bean

}