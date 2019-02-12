package com.liu.kotlin.wanandroid.kotlinwanandroid.view

import android.text.method.Touch.onTouchEvent
import android.view.MotionEvent
import android.widget.ZoomButtonsController
import android.os.Build.VERSION_CODES
import android.os.Build.VERSION_CODES.HONEYCOMB
import android.os.Build.VERSION
import android.os.Build.VERSION.SDK_INT
import android.webkit.WebSettings
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView


/**
 * author: liu
 * date: 2019/1/28 10:26
 * 自定义WebView类 解决缩放webView     zoomButton报错
 */

class CustomWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = android.R.attr.webViewStyle) : WebView(context, attrs, defStyleAttr) {

    private var zoom_controll: ZoomButtonsController? = null

    init {
        initWebSettings()
    }

    @SuppressLint("NewApi")
    private fun initWebSettings() {
        val webSettings = getSettings()

        webSettings.setSupportZoom(true)
        webSettings.setBuiltInZoomControls(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webSettings.allowContentAccess = true
            webSettings.displayZoomControls = false
        } else {
            try {
                val webview = Class.forName("android.webkit.WebView")
                val method = webview.getMethod("getZoomButtonsController")
                zoom_controll = method.invoke(this, null) as ZoomButtonsController
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (zoom_controll != null)
            zoom_controll!!.zoomControls.visibility = View.GONE
        return super.onTouchEvent(event)
    }


}
