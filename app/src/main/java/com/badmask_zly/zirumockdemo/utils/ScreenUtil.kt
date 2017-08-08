package com.badmask_zly.zirumockdemo.utils

import android.content.Context
import com.badmask_zly.zirumockdemo.base.BaseApplication
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


        fun dp2Px(dp: Float) = dp * BaseApplication.instance.appContext.resources.displayMetrics.density

        fun px2Dp(px: Float) = px / BaseApplication.instance.appContext.resources.displayMetrics.density

        fun dip2Px(dip: Float) = (dp2Px(dip) + 0.5f).toInt()

        fun px2DpCeilInt(px: Float) = (px2Dp(px) + 0.5f).toInt()
    }
}