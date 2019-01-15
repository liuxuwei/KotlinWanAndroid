package com.liu.kotlin.wanandroid.kotlinwanandroid

import android.util.Log
import com.liu.kotlin.wanandroid.kotlinwanandroid.bean.User
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.ApiService
import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog


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
        val retrofitHelper = RetrofitHelper.getInstance()
        retrofitHelper.create(ApiService::class.java)
                .login("piziliu", "zjk123456")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Logger.d(it.username)
                }

    }
}
