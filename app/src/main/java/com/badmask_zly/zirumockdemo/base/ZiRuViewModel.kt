package com.badmask_zly.zirumockdemo.base

import android.content.Intent
import android.databinding.BaseObservable

/**
 * Created by badmask_zly on 2017/7/11.
 */
class ZiRuViewModel : BaseObservable() {


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
     * 描述 activity 动作的接口
     */
    interface ActivityActionListener {

        fun startActivity(intent: Intent)

        fun startActivityForResult(intent: Intent, requestCode: Int)

        fun finishActivity()

        fun finishActivityForResult(resultCode: Int)

        fun finishActivityForResult(resultCode: Int, intent: Intent)
    }
}
































