package com.badmask_zly.zirumockdemo.recyclerview

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.badmask_zly.zirumockdemo.BR
import java.lang.ref.WeakReference

/**
 * Created by badmask_zly on 2017/7/24.
 */
class ZiRuRecyclerAdapter<T>()
    : RecyclerView.Adapter<ZiRuRecyclerAdapter.RecyclerViewHolder<T>>() {

    val TAG: String = "ZiRuRecyclerSimpleAdapter"

    //headerView
    private val VIEW_HEADER = 1001
    //footerView
    private val VIEW_FOOTER = 1002
    //loadMoreView
    private val VIEW_LOAD_MORE = 1003

    lateinit var viewModel: ZiRuRecyclerVM<T>
    lateinit var mContext: Context
    lateinit var recyclerView: RecyclerView
    var headerViewRes: Int = 0
    var footerViewRes: Int = 0
    var loadMoreViewRes: Int = 0
    //防止列表数据还未填充时就被展示出来
    var showHeaderView: Boolean = false
    var showFooterView: Boolean = false
    var showLoadingMoreView: Boolean = false

    var mInLoading: Boolean = false

    //HeaderLayout 的 ViewDataBinding
    lateinit var headerBinding: ViewDataBinding
    //FooterLayout 的 ViewDataBinding
    lateinit var footerBinding: ViewDataBinding

    //header 个数，目前只支持单 header 模式
    private var headerCount = 0
    //footer 个数，目前只支持单 footer 模式
    private var footerCount = 0

    val callback: WeakReferenceOnListChangedCallback<T> = WeakReferenceOnListChangedCallback(this)

    /**
     * 如果一个类有主构造函数，每个次构造函数需要委托给主构造函数，可以直接委托或者通过别的次构造函数间接委托。委托到同一个类的另一个构造函数用 this 关键字即可。
     */
    constructor(context: Context, viewModel: ZiRuRecyclerVM<T>, recyclerView: RecyclerView) : this() {
        this.mContext = context
        this.viewModel = viewModel
        this.recyclerView = recyclerView

    }

    /**
     * 设置 Header layout
     */
    fun setHeaderViewResForRecyclerView(@LayoutRes layoutId: Int) {
        if (layoutId != 0) {
            headerViewRes = layoutId
            headerCount = 1
            showHeaderView = true
            headerBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), headerViewRes, null, false)
            viewModel.headerView = headerBinding.root
        }
    }

    /**
     * 设置 Footer layout
     */
    fun setFooterViewResForRecyclerView(@LayoutRes layoutId: Int) {
        if (layoutId != 0) {
            footerViewRes = layoutId
            footerCount = 1
            showFooterView = true
            footerBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), footerViewRes, null, false)
            viewModel.footerView = footerBinding.root
        }
    }

    /**
     * 是否在加载更多数据
     */
    fun setInLoading(isLoading: Boolean) {
        mInLoading = isLoading
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder<T>, position: Int) {
        val viewType: Int = getItemViewType(position)
        if (viewType != VIEW_HEADER && viewType != VIEW_FOOTER && viewType != VIEW_LOAD_MORE) {
            // 正常 item 的逻辑处理单独提出一个viewModel
            val itemVM: RecyclerItemVM<T> = holder.getItemVM()
            itemVM.setData(viewModel.beans, viewModel.beans!![position], position)
            holder.mBinding.setVariable(BR.itemViewModel, itemVM)
            //为正常 item 增加点击监听
            holder.mBinding.root.isClickable = true
            holder.mBinding.root.setOnClickListener { viewModel.onItemClick(WeakReference(it), viewModel.beans!![position], position) }
        } else {
            //header、footer、loadMore 的 view 处理都在外层 viewModel 中处理
            holder.mBinding.setVariable(BR.viewModel, viewModel)
        }
        holder.mBinding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<T> {
        val holder: RecyclerViewHolder<T>
        when (viewType) {
            VIEW_HEADER -> {
                //header
                holder = RecyclerViewHolder(headerBinding.root)
                holder.setBinding(headerBinding)
            }
            VIEW_FOOTER -> {
                //footer
                holder = RecyclerViewHolder(footerBinding.root)
                holder.setBinding(footerBinding)
            }
            else -> {
                //normal
                val itemVM: RecyclerItemVM<T> = viewModel.getItemVM(viewType)
                itemVM.setOnActivityActionListener(viewModel.mActivityActionListener)
                val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), itemVM.loadItemView(), parent, false)
                holder = RecyclerViewHolder(binding.root)
                holder.setBinding(binding)
                holder.setItemVM(itemVM)
            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        var itemCount = if (null == viewModel.beans) 0 else (viewModel.beans!!).size
        if (0 != headerViewRes && showHeaderView) itemCount++
        if (0 != footerViewRes && showFooterView) itemCount++
        if (0 != loadMoreViewRes && showLoadingMoreView) itemCount++
        return itemCount
    }

    override fun getItemViewType(position: Int): Int {

        if (0 == position && 0 != headerViewRes) {
            //headerView
            return VIEW_HEADER
        }

        if (position == (viewModel.beans?.size ?: 0) + headerCount && 0 != footerViewRes) {
            //footerView
            return VIEW_FOOTER
        }

        if (position == (viewModel.beans?.size ?: 0) + headerCount + footerCount && 0 != loadMoreViewRes) {
            //loadMoreView
            return VIEW_LOAD_MORE
        }

        return viewModel.getItemViewType(position)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
        //添加数据改变监听，当数据改变时更新 view
        viewModel.beans!!.addOnListChangedCallback(callback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView?) {
        //移除监听
        viewModel.beans?.removeOnListChangedCallback(callback)
    }


    /**
     * kotlin 中用「对象表达式」和「对象声明」来对应 java 中 匿名内部类情况
     * 「对象声明」不能在局部作用域（即，直接嵌套在函数内部），但是他们可以嵌套到其他对象声明或非内部类中
     * 伴生对象的成员看起来像其他语言的静态成员，在运行时他们仍然时真实对象的实例成员，如本类中的 weakReferenceOnListChangedCallback 对象
     *
     * 这里使用到了范型。所以不能使用对象声明的方式
     */
    class WeakReferenceOnListChangedCallback<T>(ziRuRecyclerAdapter: ZiRuRecyclerAdapter<T>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        val adapterRef: WeakReference<ZiRuRecyclerAdapter<T>> = WeakReference(ziRuRecyclerAdapter)

        /**
         * 列表中一段数据发生改变
         */
        override fun onItemRangeChanged(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            adapter?.notifyItemRangeChanged(positionStart, itemCount)
        }

        /**
         * 列表中移除一段数据
         */
        override fun onItemRangeRemoved(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            adapter?.notifyItemRangeRemoved(positionStart, itemCount)
        }

        /**
         * 数据改变
         */
        override fun onChanged(sender: ObservableList<T>) {
            val adapter = adapterRef.get()
            adapter?.notifyDataSetChanged()
        }

        /**
         * 列表中移动一段数据
         */
        override fun onItemRangeMoved(sender: ObservableList<T>?, fromPosition: Int, toPosition: Int, itemCount: Int) {
            val adapter = adapterRef.get()

            for (i in 0..(itemCount - 1)) {
                adapter?.notifyItemMoved(fromPosition + i, toPosition + i)
            }
        }

        /**
         * 列表中插入一段数据
         */
        override fun onItemRangeInserted(sender: ObservableList<T>?, positionStart: Int, itemCount: Int) {
            val adapter = adapterRef.get()
            adapter!!.notifyItemRangeInserted(positionStart + 1, itemCount)
        }

    }

    class RecyclerViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

        fun getItemView() = mItemVM

    }

}