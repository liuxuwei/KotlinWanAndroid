package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseResModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.Android
import org.reactivestreams.Subscriber

/**
 * author: liu
 * date: 2019/1/14 15:52
 * 登录Presenter
 */
class LoginPresenter(val context: Context) : BasePresenter<ContractUser.LoginView>(), ContractUser.LoginModel {

    override fun login(userName: String, passWord: String) {

        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .login(userName, passWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.d(it.data.username)
                    mRootView.showLoginSuccess(it.data.username!!)
                }
    }


}