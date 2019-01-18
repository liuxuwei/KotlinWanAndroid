package com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir

import com.kingja.loadsir.callback.Callback
import com.liu.kotlin.wanandroid.kotlinwanandroid.R

/**
 * author: liu
 * date: 2019/1/18 11:21
 * description
 */
class ServerErrorCallback: Callback() {
    override fun onCreateView(): Int {
        return R.layout.server_error_callback
    }
}