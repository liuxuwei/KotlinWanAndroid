package com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.activity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.liu.kotlin.wanandroid.kotlinwanandroid.R
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.baseimpl.BaseMvpActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.contract.ContractProjectAndArticle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_project.presenter.DetailsPresenter
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.DeviceUtil
import com.liu.kotlin.wanandroid.kotlinwanandroid.utils.bindView
import com.orhanobut.logger.Logger
import org.jetbrains.anko.toast

class DetailsActivity : BaseMvpActivity<DetailsPresenter, ContractProjectAndArticle.DetailsView>(), ContractProjectAndArticle.DetailsView {
    private var wvDetails: WebView? = null
    private lateinit var detailsUrl: String
    private val ivBack by bindView<ImageView>(R.id.iv_back)
    private val tvTitle by bindView<TextView>(R.id.tv_title)
    private val pbProgress by bindView<ProgressBar>(R.id.pb_load_progress)
    private val layoutStatus by bindView<LinearLayout>(R.id.layout_status_bar)

    companion object {
        fun start(context: Context, url: String) {
            val intent = Intent()
            intent.setClass(context, DetailsActivity::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_details
    }

    override fun getPresenter(): DetailsPresenter {
        return DetailsPresenter(getContext())
    }

    override fun init() {
        layoutStatus.setBackgroundResource(R.color.color_blue)
        DeviceUtil.setStatusBar(layoutStatus)

        detailsUrl = intent.getStringExtra("url")
        wvDetails = findViewById(R.id.wv_details)
        wvDetails!!.loadUrl(detailsUrl)

        initWebView()
        setListener()
    }

    private fun initWebView() {
        wvDetails!!.webViewClient = webViewClient
        wvDetails!!.webChromeClient = webChromeClient
        val webSettings = wvDetails!!.settings
        webSettings.javaScriptEnabled = true
        webSettings.setSupportZoom(true)
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        //提高网页加载速度，暂时阻塞图片加载，网页加载好了以后再加载图片
        webSettings.blockNetworkImage = true
        webSettings.builtInZoomControls = true
    }

    private fun setListener() {
        ivBack.setOnClickListener {
            finishActivity()
        }
    }

    override fun initData() {

    }

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private val webViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            pbProgress.visibility = View.GONE
            view?.settings?.blockNetworkImage = false
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            tvTitle.text = "加载中..."
            pbProgress.visibility = View.VISIBLE
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            if (detailsUrl.contains("http://www.google.com/")) {
                toast("国内不能访问谷歌")
                return true
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    //WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
    private val webChromeClient = object : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            tvTitle.text = title
            super.onReceivedTitle(view, title)
        }

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            pbProgress.progress = newProgress
        }
    }

    override fun onBackPressed() {
        if (wvDetails!!.canGoBack()) {
            wvDetails!!.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (wvDetails!!.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK) {
            wvDetails!!.goBack()
            return true
        } else {
            onBackPressed()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        //释放webView
        wvDetails!!.destroy()
        wvDetails = null
    }

}
