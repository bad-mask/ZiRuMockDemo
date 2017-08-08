package com.badmask_zly.zirumockdemo.base

import android.databinding.ObservableInt
import com.badmask_zly.zirumockdemo.http.ApiService
import com.badmask_zly.zirumockdemo.http.BusinessCallBack
import com.badmask_zly.zirumockdemo.utils.LogUtil
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

/**
 * Created by badmask_zly on 2017/7/26.
 * 当页面初始化需要数据请求的可以继承此类。
 */
abstract class FetchDataViewModel : ZiRuViewModel() {

    companion object {
        /**
         * 初始化数据请求状态
         */
        val CODE_INIT: Int = -1
        /**
         * 数据加载中
         */
        val CODE_LOADING: Int = 0
        /**
         * 数据加载成功
         */
        val CODE_SUCCESS: Int = 1
        /**
         * 数据加载返回值为空
         */
        val CODE_EMPTY: Int = 2
        /**
         * 数据加载失败
         */
        val CODE_ERROR: Int = 3
        /**
         * 数据请求状态码
         */
        val resultCode: ObservableInt = ObservableInt(CODE_INIT)
    }

    /**
     * 获取数据
     */
    fun fetchData() {
        /**
         * kotlin 中 let 函数：
         * 调用某对象的 let 函数，则该对象为函数的参数；在函数块内可以通过 it 指代该对象；返回值为函数块的最后一行或指定 return 表达式
         */
        if (loadApiService()?.let { requestArray.add(it) }) {
            loadData(CODE_INIT, loadApiService(), loadApiparameter())
        }
    }

    fun loadData(requestCode: Int, api: String, parameters: List<Pair<String, Any?>>? = null) {
        if (resultCode.get() != CODE_SUCCESS) {
            resultCode.set(CODE_LOADING)
        } else {
            resultCode.set(CODE_INIT)
        }
        LogUtil.e("loadData   api  =    " + api)

        ApiService.instance().doGet(api, parameters, object : BusinessCallBack(requestCode) {
            override fun onSuccess(result: Result<String, FuelError>) {
                LogUtil.e("result     =    " + (result.getAs<String>() as String))
                requestArray.remove(api)
                resultCode.set(CODE_SUCCESS)
                when (result.getAs<String>()) {
                    is String -> handleData(result.getAs<String>() as String, true)
                    else -> handleData("", false)
                }
            }

            override fun onError(result: Result<String, FuelError>) {
                requestArray.remove(api)
                handleData("", false)
            }

        })
    }

    open fun handleData(result: String, status: Boolean) {}

    /**
     * 获取 ApiService
     */
    open fun loadApiService(): String = ""

    /**
     * 获取参数
     */
    open fun loadApiparameter(): List<Pair<String, Any?>>? = null

    /**
     * 数据请求结果码
     */
    fun getResultCode() = resultCode

}


























