package com.badmask_zly.zirumockdemo.base

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR


/**
 * Created by badmask_zly on 2017/7/13.
 * 基类 fragment
 */
open abstract class ZiRuFragment<VM : ZiRuViewModel, VDB : ViewDataBinding> : Fragment() {

    companion object {
        /**
         *  kotlin 中双冒号操作符表示把一个方法当作一个参数，传递懂啊另一个方法中进行使用
         *  为了防止作用域混淆，「::」调用的函数如果是类的成员函数或者是扩展函数，必须使用限定符，比如 this
         */
        var TAG: String = this::class.java.simpleName

        lateinit var mContext: Context

        lateinit var mRootView: View

    }

    /**
     * Fragment 对应的 ViewModel
     */
    lateinit var viewModel: VM

    /**
     * 系统自动生成的 ViewDataBinding，当创建 layout 编译后会自动生成
     * 此对象中持有了 layout 中设置 id 的 view 对象
     */
    lateinit var viewDataBinding: VDB

    lateinit var mOnViewLoadListener: OnViewLoadListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TAG = this::class.java.simpleName
        mContext = activity
    }


    fun replaceFragmentByTag(@IdRes layoutId: Int, fragment: Fragment, tag: String, backStackName: String) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(layoutId, fragment, tag)
        if (!TextUtils.isEmpty(backStackName)) {
            transaction.addToBackStack(backStackName)
        }
        transaction.commitAllowingStateLoss()
        childFragmentManager.executePendingTransactions()

    }

    fun setOnViewLoadListener(onViewLoadListener: OnViewLoadListener): ZiRuFragment<VM, VDB> {
        mOnViewLoadListener = onViewLoadListener
        return this
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //开始加载数据
        if (null != mOnViewLoadListener) {
            mOnViewLoadListener.onLoadStarted()
        }
        // View 做缓存处理
        if (null == mRootView) {

            // 加载 layout
            val layoutId = loadLayoutId()
            //加载 ViewModel
            viewModel = loadViewModel()
            if (0 != layoutId && null != viewModel) {
                viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
                viewDataBinding.setVariable(BR.viewModel, viewModel)
            }
            //获取根 view
            mRootView = viewDataBinding.root
            viewModel.setOnActivityActionListener(
                    object : ZiRuViewModel.ActivityActionListener {

                        override fun startActivityForResult(intent: Intent, requestCode: Int?) {
                            if (null == requestCode) {
                                startActivity(intent)
                            } else {
                                startActivityForResult(intent, requestCode)
                            }
                        }


                        override fun finishActivityForResult(resultCode: Int?, intent: Intent?) {
                            if (null == intent && null != resultCode) {
                                activity.setResult(resultCode)
                            } else if (null != intent && null != resultCode) {
                                activity.setResult(resultCode, intent)
                            }
                            activity.finish()
                        }
                    }
            )
            //初始化，可以做逻辑处理
            initialize()
            // 数据加载完毕
            if (null != mOnViewLoadListener) {
                mOnViewLoadListener.onLoadFinished()
            }
        }
        (mRootView.parent as ViewGroup)?.removeView(mRootView)
        return mRootView
    }

    /**
     * 获取 ViewModel
     */
    abstract fun loadViewModel(): VM


    /**
     * 加载 View
     */
    abstract fun loadLayoutId(): Int

    /**
     * 初始化
     */
    abstract fun initialize()

    /**
     *  Fragment 的 view 加载状态监听
     */
    interface OnViewLoadListener {

        /**
         * 开始加载 view ，此时还不能获取 view 的对象
         */
        fun onLoadStarted()

        /**
         * 加载 view 完毕，此时可以获取 view 的对象
         */
        fun onLoadFinished()

    }

}
































