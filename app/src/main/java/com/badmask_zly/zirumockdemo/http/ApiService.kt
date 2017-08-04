package com.badmask_zly.zirumockdemo.http

import com.badmask_zly.zirumockdemo.utils.HttpMethod
import com.badmask_zly.zirumockdemo.utils.HttpStatusCode
import com.badmask_zly.zirumockdemo.utils.LogUtil
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by badmask_zly on 2017/7/24.
 */


//typealias SuccessCallback = (String?) -> Unit
//typealias FailedCallback = (String?) -> Unit

class ApiService private constructor() {

    val TAG: String? = ApiService::class.java.canonicalName

    private object Holder {
        val INSTANCE = ApiService()
    }

    companion object {
        fun instance(): ApiService {
            return Holder.INSTANCE
        }
    }

    fun doGet(url: String, parameters: List<Pair<String, Any?>>? = null, callBack: BusinessCallBack) {
        return doRequest(url, HttpMethod.Get, parameters, callBack)
    }

    fun doPost(url: String, parameters: List<Pair<String, Any?>>? = null, callBack: BusinessCallBack) {
        return doRequest(url, HttpMethod.Post, parameters, callBack)
    }

    fun doRequest(url: String, method: HttpMethod, parameters: List<Pair<String, Any?>>?, callBack: BusinessCallBack) {
        LogUtil.d(url, TAG)
        doAsync {
            when (method) {
                HttpMethod.Get -> {
                    Fuel.get(url, parameters).responseString { _, response, result ->
                        uiThread {
                            handleResponse(response, result, callBack)
                        }
                    }
                }
                HttpMethod.Post -> {
                    url.httpPost(parameters).responseString { _, response, result ->
                        uiThread {
                            handleResponse(response, result, callBack)
                        }
                    }
                }

            }
        }
    }

    fun handleResponse(response: Response, result: Result<String, FuelError>, callBack: BusinessCallBack) {
//        val statusCode = response.httpStatusCode
//        when (statusCode) {
//            HttpStatusCode.Success.code -> callBack.onSuccess(result)
//            else -> callBack.onError()
//        }
        when (result) {
            is Result.Failure -> callBack.onError(result)
            is Result.Success -> callBack.onSuccess(result)
        }
    }


}

























