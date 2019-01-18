package com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir

import com.kingja.loadsir.callback.Callback
import com.liu.kotlin.wanandroid.kotlinwanandroid.R

/**
 * author: liu
 * date: 2019/1/18 14:19
 * description
 */
class EmptyDataCallback: Callback() {
    override fun onCreateView(): Int {
        return R.layout.empty_data_callback
    }
}