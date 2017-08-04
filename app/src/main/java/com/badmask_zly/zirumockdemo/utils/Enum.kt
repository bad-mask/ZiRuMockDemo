package com.badmask_zly.zirumockdemo.utils

/**
 * Created by badmask_zly on 2017/7/24.
 */

enum class HttpStatusCode(val code: Int, val message: String) {
//    UnAccessible(-1,"")
    Success(200,""),
    Error(500,""),
    NotFound(404,""),
}

enum class HttpMethod {
    Get,
    Post,
    Put,
    Delete
}