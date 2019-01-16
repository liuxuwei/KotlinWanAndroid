package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl

import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseLazyLoadFragment

/**
 * author: liu
 * date: 2019/1/16 17:00
 * description
 */
abstract class BaseMvpFragment<P: IPresenter<V>,V: IView>: BaseLazyLoadFragment(),IView {
    protected lateinit var mPresenter: P

    override fun initEvent() {
        mPresenter = getPresenter()
        if (mPresenter != null) {
            mPresenter.attachView(this as V)
        }
        init()
    }

    abstract fun init()

    abstract fun getPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) {
            mPresenter.onDestroy()
        }
    }


    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    override fun finishActivity() {
        activity?.finish()
    }


}