package com.liu.kotlin.wanandroid.kotlinwanandroid.utils

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * author: liu
 * date: 2019/1/24 16:26
 * RecyclerView装饰类
 */
class MyItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = 20
    }
}