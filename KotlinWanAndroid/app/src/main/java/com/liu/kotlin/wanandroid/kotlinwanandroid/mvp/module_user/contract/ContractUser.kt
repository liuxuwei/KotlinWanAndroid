package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract

import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.`interface`.IView

/**
 * author: liu
 * date: 2019/1/14 15:53
 * description
 */
interface ContractUser {

    /*---------------------登录----------------------------*/
    interface LoginView : IView {

        fun showLoginSuccess(msg: String)

        fun showLoginFailed(msg: String)
    }

    interface LoginModel : IModel {
        fun login(userName: String, passWord: String)
    }

    /*------------------------注册--------------------------*/
    interface RegisterView : IView {

    }

    interface RegisterModel : IModel {

    }


}