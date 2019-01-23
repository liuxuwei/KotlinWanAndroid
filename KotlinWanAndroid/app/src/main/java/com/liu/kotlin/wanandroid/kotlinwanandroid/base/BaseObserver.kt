package com.liu.kotlin.wanandroid.kotlinwanandroid.base

import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * author: liu
 * date: 2019/1/22 10:23
 * RxJava  baseObserver
 */
abstract class BaseObserver<T> : Observer<BaseResModel<T>> {

    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(t: BaseResModel<T>) {
        if (t.errorCode == 0) {
            onSuccess(t.data)
        } else {
            onFailed(t.errorMsg)
        }
    }

    override fun onError(e: Throwable) {
        Logger.d("BaseObserver  onError: ${e.message}")
        onFailed("服务器异常：${e.message}")
    }

    abstract fun onSuccess(bean: T)

    abstract fun onFailed(msg: String)

}