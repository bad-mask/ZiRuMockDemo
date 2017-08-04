package com.badmask_zly.zirumockdemo.recyclerview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by badmask_zly on 2017/8/3.
 */
class DividerItemDecoration(context: Context, orientation: Int) : RecyclerView.ItemDecoration() {

    companion object {
        val ATTRS: IntArray = intArrayOf(android.R.attr.listDivider)
        val HORIZONTAL_LIST: Int = LinearLayoutManager.HORIZONTAL
        val VERTICAL_LIST: Int = LinearLayoutManager.VERTICAL
    }

    val mDivider: Drawable
    var mOrientation: Int = 0

    init {
        val typeArray: TypedArray = context.obtainStyledAttributes(ATTRS)
        mDivider = typeArray.getDrawable(0)
        typeArray.recycle()
        setOrientation(orientation)
    }

    fun setOrientation(orientation: Int) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw IllegalArgumentException("invalid orientation")
        }
        this.mOrientation = orientation
    }


    override fun onDraw(c: Canvas?, parent: RecyclerView?) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawHorizontal(c: Canvas?, parent: RecyclerView?) {
        val top: Int = parent!!.paddingTop
        val bottom: Int = parent.height - parent.paddingBottom
        val childCount: Int = parent.childCount
        for (i in 0..(childCount - 1)) {
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left = child.right + params.rightMargin
            val right = left + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c!!)
        }

    }

    private fun drawVertical(c: Canvas?, parent: RecyclerView?) {
        val left: Int = parent!!.paddingLeft
        val right: Int = parent.width - parent.paddingRight
        val childCount = parent.childCount
        for (i in 0..(childCount - 1)) {
            val child: View = parent.getChildAt(i)
            val params: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + mDivider.intrinsicHeight
            mDivider.setBounds(left, top, right, bottom)
            mDivider.draw(c!!)
        }
    }

    override fun getItemOffsets(outRect: Rect?, itemPosition: Int, parent: RecyclerView?) {
        if (mOrientation == VERTICAL_LIST) {
            outRect!!.set(0, 0, 0, mDivider.intrinsicHeight)
        } else {
            outRect!!.set(0, 0, mDivider.intrinsicWidth, 0)
        }
    }
}





















