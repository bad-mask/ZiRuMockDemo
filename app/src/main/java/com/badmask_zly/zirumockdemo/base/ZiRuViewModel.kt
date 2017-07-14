package com.badmask_zly.zirumockdemo.base

import android.content.Intent
import android.databinding.BaseObservable

/**
 * Created by badmask_zly on 2017/7/11.
 * 基类 viewModel
 */
open class ZiRuViewModel : BaseObservable() {


    /**
     * 注意 lateinit 与 lazy 的区别
     */
    private lateinit var mActivityActionListener: ActivityActionListener


    companion object {
        // var requestArray = emptyArray<Call>()
    }

    fun setOnActivityActionListener(actionListener: ActivityActionListener) {
        this.mActivityActionListener = actionListener
    }



    /**
     * 启动 Activity 且带参数并接收返回值
     */
    fun startActivity(intent: Intent, requestCode: Int) {
        if (null != mActivityActionListener) {
            requestCode
            mActivityActionListener.startActivityForResult(intent, requestCode)
        }
    }

    /**
     * 关闭 Activity 不返回数据
     */
    fun finishActivityForResult(requestCode: Int) {
        if (null != mActivityActionListener) {
            mActivityActionListener.finishActivityForResult(requestCode)
        }
    }

    /**
     * 关闭 Activity 返回数据
     */
    fun finishActivityForResult(requestCode: Int?, intent: Intent?) {
        if (null != mActivityActionListener) {

            mActivityActionListener.finishActivityForResult(requestCode, intent)
        }
    }

    /**
     * 描述 activity 动作的接口
     */
    interface ActivityActionListener {

        fun startActivityForResult(intent: Intent, requestCode: Int? = null)

        fun finishActivityForResult(resultCode: Int? = null, intent: Intent? = null)
    }
}
































