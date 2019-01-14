package com.liu.kotlin.wanandroid.kotlinwanandroid

import com.liu.kotlin.wanandroid.kotlinwanandroid.global.RetrofitHelper
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity.LoginActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.contract.ContractUser
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Rule @JvmField
    val rule = MyRule()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun retrofitHelperTest(){
        val retrofitHelper = RetrofitHelper.getInstance()
        assertNotNull(retrofitHelper)
    }
}
