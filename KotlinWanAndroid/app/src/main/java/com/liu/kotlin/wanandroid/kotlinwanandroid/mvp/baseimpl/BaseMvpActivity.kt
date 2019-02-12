package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl

import android.view.View
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseActivity
import org.greenrobot.eventbus.EventBus

/**
 * author: liu
 * date: 2019/1/14 15:10
 * BaseMvpActivity
 */
abstract class BaseMvpActivity<P : IPresenter<V>, V : IView> : BaseActivity(), IView {
    protected lateinit var mPresenter: P

    override fun initView() {
        mPresenter = getPresenter()
        if (mPresenter != null) {
            mPresenter.attachView(this as V)
        }
        init()
    }

    abstract fun init()

    abstract fun getPresenter(): P

    abstract override fun initData()

    override fun showLoading() {
        loadingDialog.show()
    }

    override fun hideLoading() {
        loadingDialog.dismiss()
    }

    override fun finishActivity() {
        finish()
    }

}