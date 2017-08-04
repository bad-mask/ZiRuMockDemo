package com.badmask_zly.zirumockdemo.recyclerview

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badmask_zly.zirumockdemo.BR
import com.badmask_zly.zirumockdemo.base.OnItemClickListener
import com.badmask_zly.zirumockdemo.base.ZiRuActivity
import com.badmask_zly.zirumockdemo.base.ZiRuViewModel
import com.badmask_zly.zirumockdemo.utils.LogUtil
import java.lang.ref.WeakReference

/**
 * Created by badmask_zly on 2017/7/24.
 * @description 简单的 RecyclerAdapter ，不支持下拉刷新，不支持上拉加载更多
 */
class ZiRuRecyclerSimpleAdapter<T>()
    : RecyclerView.Adapter<ZiRuRecyclerSimpleAdapter.RecyclerSimpleViewHolder<T>>() {

    val TAG: String = "ZiRuRecyclerSimpleAdapter"
    lateinit var beans: ObservableArrayList<T>
    var onItemClickListener: OnItemClickListener? = null
    lateinit var itemVMFactory: ItemVMFactory<T>
    val callback: WeakReferenceOnListChangedCallback<T> = WeakReferenceOnListChangedCallback()

    /**
     * 如果一个类有主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可。
     */
    constructor(beans: ObservableArrayList<T>, itemVMFactory: ItemVMFactory<T>, onItemClickListener: OnItemClickListener? = null) : this() {
        this.beans = beans
        this.onItemClickListener = onItemClickListener
        this.itemVMFactory = itemVMFactory
    }

    override fun onBindViewHolder(holder: RecyclerSimpleViewHolder<T>, position: Int) {
        val itemVM: RecyclerItemVM<T> = holder.getItemVM()
        itemVM.setData(beans, beans[position], position)
        holder.mBinding.setVariable(BR.viewModel, itemVM)
        if (null != onItemClickListener) {
            holder.mBinding.root.isClickable = true
            holder.mBinding.root.setOnClickListener {
                //一个 lambda 表达式只有一个参数是很常见的。如果 kotlin 可以自己计算出签名，它允许我们不声明唯一的参数。并且隐含地为我们声明其名称为 it
                View.OnClickListener { onItemClickListener?.onItemClick(it, position) }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerSimpleViewHolder<T> {
        val itemVM: RecyclerItemVM<T> = itemVMFactory.getItemVM(viewType)
        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), itemVM.loadItemView(), parent, false)
        val holder: RecyclerSimpleViewHolder<T> = ZiRuRecyclerSimpleAdapter.RecyclerSimpleViewHolder(binding.root)
        holder.setBinding(binding)
        holder.setItemVM(itemVM)
        itemVM.setOnActivityActionListener(object : ZiRuViewModel.ActivityActionListener {
            override fun startActivityForResult(intent: Intent, requestCode: Int?) {
                val context: Context = parent.context
                /**
                 * kotlin 中用 is 与 !is 来代替 java 中的  instanceof
                 * In many cases, one does not need explicit cast operators in Kotlin, because the compiler tracks the is-checks for immutable
                 * values and inserts casts automatically when needed.
                 */
                if (context is ZiRuActivity<*, *>) {
                    if (null == requestCode) {
                        context.startActivity(intent)
                    } else {
                        context.startActivityForResult(intent, requestCode)
                    }
                } else {
                    LogUtil.d("please make the activity extend BaseActivity {@link BaseActivity}")
                }
            }

            override fun finishActivityForResult(resultCode: Int?, intent: Intent?) {
                val context: Context = parent.context
                if (context is ZiRuActivity<*, *>) {
                    if (null == intent && null != resultCode) {
                        context.setResult(resultCode)
                    } else if (null != intent && null != resultCode) {
                        context.setResult(resultCode, intent)
                    }

                    if (!context.isFinishing) {
                        context.finish()
                    }
                } else {
                    LogUtil.d("please make the activity extend BaseActivity {@link BaseActivity}")
                }
            }
        })
        return holder
    }

    override fun getItemCount(): Int = beans?.size

    override fun getItemViewType(position: Int): Int {
        return itemVMFactory.getItemViewType(position)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        //添加数据改变监听，当数据改变时更新 view
        beans.addOnListChangedCallback(callback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        beans.removeOnListChangedCallback(callback)
    }


    /**
     * kotlin 中用「对象表达式」和「对象声明」来对应 java 中 匿名内部类情况
     * 「对象声明」不能在局部作用域（即，直接嵌套在函数内部），但是他们可以嵌套到其他对象声明或非内部类中
     * 伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然时真实对象的实例成员，如本类中的 weakReferenceOnListChangedCallback 对象
     */
    class WeakReferenceOnListChangedCallback<T> : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        val adapterRef: WeakReference<ZiRuRecyclerSimpleAdapter<T>> = WeakReference<ZiRuRecyclerSimpleAdapter<T>>(ZiRuRecyclerSimpleAdapter())

        /**
         * 列表中一段数据发生改变
         */
        override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter: ZiRuRecyclerSimpleAdapter<T>? = adapterRef.get()
            adapter?.notifyItemRangeChanged(positionStart, itemCount)
        }

        /**
         * 列表中移除一段数据
         */
        override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter: ZiRuRecyclerSimpleAdapter<T>? = adapterRef.get()
            adapter?.notifyItemRangeRemoved(positionStart, itemCount)
        }

        /**
         * 数据改变
         */
        override fun onChanged(sender: ObservableList<T>) {
            val adapter: ZiRuRecyclerSimpleAdapter<T>? = adapterRef.get()
            adapter?.notifyDataSetChanged()
        }

        /**
         * 列表中移动一段数据
         */
        override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
            val adapter: ZiRuRecyclerSimpleAdapter<T>? = adapterRef.get()

            for (i in 0..itemCount) {
                adapter?.notifyItemMoved(fromPosition + i, toPosition + i)
            }
        }

        /**
         * 列表中插入一段数据
         */
        override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter: ZiRuRecyclerSimpleAdapter<T>? = adapterRef.get()
            adapter?.notifyItemRangeInserted(positionStart, itemCount)
        }

    }

    class RecyclerSimpleViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var mBinding: ViewDataBinding
        lateinit var mItemVM: RecyclerItemVM<T>

        fun getBinding() = mBinding

        fun setBinding(binding: ViewDataBinding) {
            mBinding = binding
        }

        fun getItemVM() = mItemVM

        fun setItemVM(itemVM: RecyclerItemVM<T>) {
            mItemVM = itemVM
        }

        fun getItemView() = itemView

    }

}