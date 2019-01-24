package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import android.content.Context
import android.text.TextUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * author: liu
 * date: 2019/1/24 10:34
 * 请求拦截器，如果该请求存在cookie，将cookie添加到Header的Cookie中
 */
class AddCookiesInterceptor(private val mContext: Context) : Interceptor {

    companion object {
        const val COOKIE_PREF = "cookie_prefs"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val cookie = getCookie(request.url().toString(), request.url().host())
        if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie)
        }

        return chain.proceed(builder.build())
    }

    private fun getCookie(url: String, domain: String): String {
        val sp = mContext.getSharedPreferences(COOKIE_PREF, Context.MODE_PRIVATE)
        if (!TextUtils.isEmpty(url) && sp.contains(url) && !TextUtils.isEmpty(sp.getString(url, ""))) {
            return sp.getString(url, "")
        }

        if (!TextUtils.isEmpty(domain) && sp.contains(domain) && !TextUtils.isEmpty(sp.getString(domain, ""))) {
            return sp.getString(domain, "")
        }

        return ""
    }
}