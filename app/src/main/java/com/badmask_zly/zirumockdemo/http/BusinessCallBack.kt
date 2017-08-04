package com.badmask_zly.zirumockdemo.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result

/**
 * Created by badmask_zly on 2017/7/26.
 * 封装的业务回掉
 */

abstract class BusinessCallBack(request: Int) {

    val requestCode: Int

    init {
        requestCode = request
    }

    abstract fun onSuccess(result: Result<String, FuelError>)
    abstract fun onError(result: Result<String, FuelError>)

}



























