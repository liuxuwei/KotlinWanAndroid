package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseResModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * author: liu
 * date: 2019/1/15 8:25
 * description
 */
interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String)
            : Observable<BaseResModel<User>>
}