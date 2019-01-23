package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.presenter

import android.content.Context
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseObserver
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BasePresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * author: liu
 * date: 2019/1/15 8:13
 * description
 */
class RegisterPresenter(val context: Context): BasePresenter<ContractUser.RegisterView>(),ContractUser.RegisterModel {

    override fun registerUser(userName: String, passWord: String, rePassword: String) {
        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .register(userName, passWord, rePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : BaseObserver<User>(){
                    override fun onSuccess(bean: User) {
                        mRootView.registerSuccessOrFailed(0,"注册成功！")
                    }

                    override fun onFailed(msg: String) {
                        mRootView.registerSuccessOrFailed(-1,msg)
                    }

                })

    }

}