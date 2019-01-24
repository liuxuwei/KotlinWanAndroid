package com.liu.kotlin.wanandroid.kotlinwanandroid

import android.util.Log
import com.liu.kotlin.wanandroid.kotlinwanandroid.base.BaseObserver
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.Article
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.AddCookiesInterceptor
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.App
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.AboutMeActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.DetailsActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity.ProjectAndArticleActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.InputSoftUtil
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.TimeUtil
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, maxSdk = 23)
class ExampleUnitTest {

    @Rule
    @JvmField
    val rule = MyRule()

    @Before
    fun beforeTest() {
        Logger.addLogAdapter(AndroidLogAdapter())
        ShadowLog.stream = System.out
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun retrofitHelperTest() {
        val retrofitHelper = RetrofitHelper.getInstance()
        assertNotNull(retrofitHelper)
    }

    @Test
    fun apiServiceTest() {

        val client = OkHttpClient.Builder()
                .connectTimeout(9,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build()

        val request = Request.Builder()
                .url("http://wanandroid.com/wxarticle/list/408/0/json ")
                .build()
        val call = client.newCall(request)

        Logger.d(call.execute().body()?.string())

    }

    @Test
    fun timeUtilTest() {
        Logger.d(TimeUtil.stampToStrTime(1547395200000))       //2019-01-14
    }


    @Test
    fun headerTest() {

        RetrofitHelper.getInstance()
                .create(ApiService::class.java)
                .login("piziliu","zjk123456")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }

        val builder = OkHttpClient.Builder()
                .addInterceptor(AddCookiesInterceptor(App.getInstance().applicationContext))

        val client = builder.build()

        val request =  Request.Builder()
                .url("http://www.wanandroid.com/lg/collect/list/0/json")
                .build()

        val call = client.newCall(request)

        Logger.d(call.execute().body()?.string())


//        RetrofitHelper.getInstance()
//                .create(ApiService::class.java)
//                .getCollectArticleList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : BaseObserver<Article>(){
//                    override fun onSuccess(bean: Article) {
//                        Logger.d(bean.toString())
//                    }
//
//                    override fun onFailed(msg: String) {
//                        Logger.d(msg)
//                    }
//                })
    }
}
