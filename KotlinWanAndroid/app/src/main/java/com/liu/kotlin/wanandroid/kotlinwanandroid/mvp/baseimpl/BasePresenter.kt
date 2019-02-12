package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl

import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView

/**
 * author: liu
 * date: 2019/1/14 15:41
 * BasePresenter
 */
open class BasePresenter<V: IView>: IPresenter<V> {
    protected lateinit var mRootView: V

    override fun onStart() {

    }

    override fun onDestroy() {
        this.mRootView == null
    }

    override fun attachView(view: V) {
        this.mRootView = view
    }

    override fun getView(): V {
        return mRootView
    }
}