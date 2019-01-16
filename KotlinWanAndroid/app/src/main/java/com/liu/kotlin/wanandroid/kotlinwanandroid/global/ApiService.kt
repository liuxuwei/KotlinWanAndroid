package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseResModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Chapters
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import io.reactivex.Observable
import retrofit2.http.*

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

    @GET("wxarticle/chapters/json")
    fun getChapters() : Observable<BaseResModel<List<Chapters>>>

    @GET("wxarticle/list/{id}/{page}/json")
    fun getArticles(@Path("id") id: Int,@Path("page") page: Int) : Observable<BaseResModel<Article>>
}