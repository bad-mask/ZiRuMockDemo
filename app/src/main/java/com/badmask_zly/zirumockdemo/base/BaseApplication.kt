package com.badmask_zly.zirumockdemo.base

import android.app.Application
import android.content.Context
import com.badmask_zly.zirumockdemo.extensions.DelegateExt

/**
 * Created by badmask_zly on 2017/7/6.
 */
class BaseApplication : Application() {
    lateinit var appContext: Context

    companion object {
        var instance: BaseApplication by DelegateExt.notNullSingleVauleVar()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appContext = this
        InitializeManager.get().setApplicationContext(instance)

    }
}