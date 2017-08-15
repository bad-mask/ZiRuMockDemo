package com.badmask_zly.zirumockdemo.base

import android.content.Intent
import android.databinding.BaseObservable
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.text.TextUtils
import com.badmask_zly.zirumockdemo.http.ApiService
import com.badmask_zly.zirumockdemo.http.BusinessCallBack
import com.badmask_zly.zirumockdemo.utils.LogUtil
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

/**
 * Created by badmask_zly on 2017/7/11.
 * 基类 viewModel
 */
open class ZiRuViewModel : BaseObservable() {

    val requestArray: MutableList<String> = mutableListOf()

    /**
     * 注意 lateinit 与 lazy 的区别
     */
    lateinit var mActivityActionListener: ActivityActionListener


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
     * 数据请求
     * @param  requestCode 请求码
     */
    fun fetchRemoteData(requestCode: Int = 0, api: String, parameters: List<Pair<String, Any?>>? = null, isSilence: Boolean = false) {
        ApiService.instance().doGet(api, parameters, object : BusinessCallBack(requestCode) {
            override fun onSuccess(result: Result<String, FuelError>) {
                LogUtil.e("result     =    " + (result.getAs<String>() as String))
                requestArray.remove(api)
                FetchDataViewModel.resultCode.set(FetchDataViewModel.CODE_SUCCESS)
                when (result.getAs<String>()) {
                    is String -> handleData(requestCode, result.getAs<String>() as String, true)
                    else -> handleData(requestCode, "", false)
                }
            }

            override fun onError(result: Result<String, FuelError>) {
                requestArray.remove(api)
                handleData(requestCode, "", false)
            }

        })
    }

    /**
     * 处理数据
     * @param requestCode 定义的请求码，当请求数据返回后根据返回码处理对应的逻辑
     */
    open fun handleData(requestCode: Int, result: String, status: Boolean) {

    }

    /**
     * 描述 activity 动作的接口
     */
    interface ActivityActionListener {

        fun startActivityForResult(intent: Intent, requestCode: Int? = null)

        fun finishActivityForResult(resultCode: Int? = null, intent: Intent? = null)
    }


}
































