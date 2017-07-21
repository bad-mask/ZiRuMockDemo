package com.badmask_zly.zirumockdemo.base

import android.content.Context
import com.badmask_zly.zirumockdemo.utils.ScreenUtil


/**
 * Created by badmask_zly on 2017/7/21.
 *
 * 关于构造函数：
 * 默认情况下，所有的构造函数都是 public 的，这实际上等于类可见的地方它就可见
 */
class InitializeManager private constructor() {

    lateinit var mContext: Context

    companion object {
        fun get(): InitializeManager {
            return Inner.instance
        }
    }

    private object Inner {
        val instance = InitializeManager()
    }

    fun setApplicationContext(context: Context) {
        mContext = context
        ScreenUtil.initData(context)
    }
}