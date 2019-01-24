package com.liu.kotlin.wanandroid.kotlinwanandroid.global

import android.content.Context
import android.text.TextUtils
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.NullPointerException

/**
 * author: liu
 * date: 2019/1/24 9:55
 * 响应拦截器，从response中获取set-cookie字段的值，保存cookie到本地
 * 来源博客：https://www.jianshu.com/p/437d0f032ed4
 */
class SaveCookiesInterceptor(private val mContext: Context):  Interceptor{

    companion object {
        const val COOKIE_PREF = "cookies_prefs"

        /**
         * 清除本地cookie
         */
        fun clearCookie(context: Context) {
            val sp = context.getSharedPreferences(COOKIE_PREF,Context.MODE_PRIVATE)
            sp.edit().clear().apply()
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        //set-cookie 可能为多个
        if (!response.headers("set-cookie").isEmpty()) {
            val cookies = response.headers("set-cookie")
            val cookie = encodeCookie(cookies)
            saveCookie(request.url().toString(),request.url().host(),cookie)
        }
        return response
    }

    /**
     * 整合cookie为唯一字符串
     */
    private fun encodeCookie(cookies: List<String>): String {
        var strBuilder = StringBuilder()
        var strSet = HashSet<String>()
        for (cookie in cookies) {
            val arr = cookie.split(";")
            for (str in arr) {
                if (strSet.contains(str)) {
                    continue
                }
                strSet.add(str)
            }
        }
        for (cookie in strSet) {
            strBuilder.append(cookie).append(";")
        }
        val lastIndex = strBuilder.lastIndexOf(";")
        if (strBuilder.length - 1 == lastIndex) {
            strBuilder.deleteCharAt(lastIndex)
        }

        Logger.d("服务器端返回的cookie: $strBuilder")
        return strBuilder.toString()
    }

    /**
     * 保存cookie到本地，为url和host设置相同的cookie，host可选
     * 这样能使得cookie的应用范围更广
     */
    private fun saveCookie(url: String, domain: String, cookie: String) {
        val sp = mContext.getSharedPreferences(COOKIE_PREF,Context.MODE_PRIVATE)
        val editor = sp.edit()

        if (TextUtils.isEmpty(url)) {
            throw NullPointerException("url is null")
        } else {
            editor.putString(url,cookie)
        }

        if (!TextUtils.isEmpty(domain)) {
            editor.putString(domain,cookie)
        }

        editor.apply()
    }

}