package com.badmask_zly.zirumockdemo.utils

import android.content.Context
import java.lang.ref.WeakReference

/**
 * Created by badmask_zly on 2017/7/21.
 * 与屏幕相关的一些工具类方法
 */
class ScreenUtil {

    companion object {
        lateinit var mContextRef: WeakReference<Context>

        fun initData(context: Context) {
            mContextRef = WeakReference(context.applicationContext)
        }

        /**
         * 获取屏幕高度
         */
        fun getScreenHeight() = if (null != mContextRef) mContextRef.get()!!.resources.displayMetrics.heightPixels else 0

        /**
         * 获取屏幕宽度
         */
        fun getScreenWidth() = if (null != mContextRef) mContextRef.get()!!.resources.displayMetrics.widthPixels else 0


    }
}