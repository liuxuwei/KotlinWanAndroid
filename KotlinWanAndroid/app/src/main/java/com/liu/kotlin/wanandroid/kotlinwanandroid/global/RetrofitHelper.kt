package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.concurrent.TimeUnit

/**
 * author: liu
 * date: 2019/1/14 16:18
 * description
 */
class RetrofitHelper {
    private var mRetrofit: Retrofit

    companion object {
        private var retrofitHelper: RetrofitHelper? = null

        fun getInstance(): RetrofitHelper{
            if (retrofitHelper == null) {
                synchronized(RetrofitHelper::class.java) {
                    if (retrofitHelper == null) {
                        retrofitHelper = RetrofitHelper()
                    }
                }
            }
            return retrofitHelper!!
        }
    }

    private constructor(){
        mRetrofit = createRetrofit()
    }

    private fun createRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)

        return Retrofit.Builder()
                .baseUrl(Interfaces.BASE_URL)
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun <T> create(service: Class<T>): T{
        return mRetrofit.create(service)
    }

}