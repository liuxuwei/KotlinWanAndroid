package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseResModel
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * author: liu
 * date: 2019/1/15 8:25
 * 项目接口
 */
interface ApiService {

    /**
     * 注册用户
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") userName: String, @Field("password") password: String
                 , @Field("repassword") rePassword: String): Observable<BaseResModel<User>>

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String)
            : Observable<BaseResModel<User>>

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    fun getChapters(): Observable<BaseResModel<List<Chapters>>>

    /**
     * 获取文章列表
     */
    @GET("wxarticle/list/{id}/{page}/json")
    fun getArticles(@Path("id") id: Int, @Path("page") page: Int): Observable<BaseResModel<Article>>

    /**
     * 获取项目分类
     */
    @GET("project/tree/json")
    fun getProjectType(): Observable<BaseResModel<List<ProjectType>>>

    /**
     * 获取项目列表
     */
    @GET("project/list/{page}/json?")
    fun getProjectList(@Path("page") page: Int, @Query("cid") typeId: Int): Observable<BaseResModel<ProjectItem>>

    /**
     * 在公众号文章列表中进行搜索
     */
    @GET("wxarticle/list/{id}/{page}/json?")
    fun searchArticle(@Path("id") id: Int, @Path("page") page: Int, @Query("k") keyWord: String): Observable<BaseResModel<Article>>

    /**
     * 收藏文章列表   暂时还没用到
     */
    @Deprecated("doesn't use in this version")
    @GET ("lg/collect/list/0/json")
    fun getCollectArticleList() : Observable<BaseResModel<Article>>


}