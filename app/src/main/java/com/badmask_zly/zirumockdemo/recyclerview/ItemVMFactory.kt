package com.badmask_zly.zirumockdemo.recyclerview

/**
 * Created by badmask_zly on 2017/7/24.
 * ItemViewModel
 */
open abstract class ItemVMFactory<T> {

    abstract fun getItemVM(viewType: Int): RecyclerItemVM<T>

    fun getItemViewType(position: Int) = 0
}