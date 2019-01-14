package com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`

/**
 * author: liu
 * date: 2019/1/14 14:17
 * description
 */
interface IPresenter<T: IView> {

    fun onStart()

    fun onDestroy()

    fun attachView(view: T)

    fun getView(): T

}