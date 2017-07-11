package com.badmask_zly.zirumockdemo.base

import android.app.Application
import com.badmask_zly.zirumockdemo.extensions.DelegateExt

/**
 * Created by badmask_zly on 2017/7/6.
 */
class BaseApplication : Application() {
    companion object {
        var instance: BaseApplication by DelegateExt.NotNullSingleVauleVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}