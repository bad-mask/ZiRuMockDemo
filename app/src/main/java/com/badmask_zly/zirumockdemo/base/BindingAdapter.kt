package com.badmask_zly.zirumockdemo.base

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.view.View
import android.widget.ImageView
import com.badmask_zly.zirumockdemo.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

/**
 * Created by badmask_zly on 2017/8/3.
 */

/**
 * 等到功能完成，尚需完善的地方是图片的缓存
 *
 * 在 xml 中设置 ImageView 的 url，其中 bind:error 设置图片加载失败后的默认图
 */
@BindingAdapter(value = *arrayOf("app:imageUrl", "app:placeHolder", "app:error", "app:round", "app:radius", "app:stroke_width", "app:stroke_color"), requireAll = false)
fun loadImageWithPicasso(view: ImageView, url: String?, placeHolder: Drawable? = BaseApplication.instance.getDrawable(R.mipmap.default_big), error: Drawable?, round: Boolean?, radius: Int?, stroke_width: Int?, @ColorInt stroke_color: Int?) {
    val picasso: Picasso = Picasso.with(view.context)
    val requestCreator: RequestCreator = picasso.load(url)
    //设置默认图
    requestCreator.placeholder(placeHolder)
    //设置加载失败后的图
    if (null != error) {
        requestCreator.error(error)
    }
    requestCreator.into(view)
}

/**
 * 设置 view 的高度
 */
@BindingAdapter(value = *arrayOf("app:resizeHeight"))
fun setViewHeight(view: View, height: Int) {
    view.layoutParams.height = height
}

/**
 * 设置 view 的宽度
 */
@BindingAdapter(value = *arrayOf("app:resizeWidth"))
fun setViewWidth(view: View, width: Int) {
    view.layoutParams.width = width
}

























