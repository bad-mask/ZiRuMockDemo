package com.badmask_zly.zirumockdemo.base

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import com.badmask_zly.zirumockdemo.BR
import com.badmask_zly.zirumockdemo.utils.LogUtil

/**
 * Created by badmask_zly on 2017/7/11.
 * 基类 activity
 */
abstract class ZiRuActivity<VM : ZiRuViewModel, VDB : ViewDataBinding> : FragmentActivity() {

    /**
     * Activity 对应的 ViewModel
     */
    lateinit var viewModel: VM

    /**
     * 系统自动生成的 ViewDataBinding ，当创建 layout 编译后会自动生成。
     * 此对象中持有了 layout 中设置 id 的 view 对象
     */
    lateinit var viewDataBinding: VDB

    companion object {
        lateinit var mContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zrInit()
    }

    fun zrInit() {
        LogUtil.d("currentClass ===>> " + this::class.java.simpleName)
        val layoutId: Int = loadLayoutId()

        //加载 viewModel
        viewModel = loadViewModel()

        //设置 Activity 的动作监听
        viewModel.setOnActivityActionListener(object : ZiRuViewModel.ActivityActionListener {
            override fun startActivityForResult(intent: Intent, requestCode: Int?) {
                if (null == requestCode) {
                    startActivity(intent)
                } else {
                    startActivityForResult(intent, requestCode)
                }
            }


            override fun finishActivityForResult(resultCode: Int?, intent: Intent?) {
                if (null == intent && null != resultCode) {
                    setResult(resultCode)
                } else if (null != intent && null != resultCode) {
                    setResult(resultCode, intent)
                }

                if (!isFinishing) {
                    finish()
                }
            }
        })

        if (0 != layoutId && null != viewModel) {
            viewDataBinding = DataBindingUtil.setContentView(this, loadLayoutId())
            viewDataBinding.setVariable(BR.viewModel, viewModel)
        }
        initialize()
    }

    abstract fun initialize()

    /**
     * 获取 ViewModel
     */
    abstract fun loadViewModel(): VM

    abstract fun loadLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * replaceFragment ,可加入回退栈中
     * 此处使用了 kotlin 中的默认参数用法
     */
    fun replaceFragmentByTag(@IdRes layoutId: Int, fragment: Fragment, tag: String, backStackName: String = "") {

        //fragmentManager  是手动导 v4 的包的，否则直接使用 activity 中的 getFragmentManager() 得到变量
        val fragmentManager: FragmentManager = this.supportFragmentManager
        val transition = fragmentManager.beginTransaction()
        transition.replace(layoutId, fragment, tag)
        if (!TextUtils.isEmpty(backStackName)) {
            transition.addToBackStack(backStackName)
        }
        transition.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()

    }

}