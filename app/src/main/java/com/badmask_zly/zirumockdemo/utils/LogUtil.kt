package com.badmask_zly.zirumockdemo.utils

import android.util.Log

/**
 * Created by badmask_zly on 2017/7/11.
 *
 * kotlin 中没有 static 类型的 fun
 */

/**
 *  伴生对象的初始化是在相应的类被加载时，与 Java 静态初始化器的语义相匹配。
 *  即使伴生对象的成员看起来像其他语言的静态成员，在运行时它们仍然是真实对象的实例成员。
 *  在 JVM 平台，如果使用 @JvmStatic 注解，你可以将伴生对象的成员生成为真正的静态方法和字段
 */
class LogUtil {

    companion object {
        var TAG = "ZiRu"
        val isDebug = false
        fun d(msg: String, tag: String = TAG) {
            if (isDebug) {
                Log.d(tag, msg)
            }
        }

        fun w(msg: String, tag: String = TAG) {
            if (isDebug) {
                Log.w(tag, msg)
            }
        }

        fun e(msg: String, tag: String = TAG) {
            if (isDebug) {
                Log.e(tag, msg)
            }
        }
    }


}