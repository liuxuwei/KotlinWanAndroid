package com.liu.kotlin.wanandroid.kotlinwanandroid.utils.loadsir

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kingja.loadsir.callback.Callback
import com.liu.kotlin.wanandroid.kotlinwanandroid.R

/**
 * author: liu
 * date: 2019/1/18 14:27
 * description
 */
class LoadingCallback: Callback() {
    override fun onCreateView(): Int {
        return R.layout.loading_callback
    }

    override fun onViewCreate(context: Context?, view: View?) {
        val imageView = view!!.findViewById<ImageView>(R.id.iv_loading)
        Glide.with(context!!).load(R.drawable.loading_bar).into(imageView)
        super.onViewCreate(context, view)
    }

    override fun onAttach(context: Context?, view: View?) {
        val imageView = view!!.findViewById<ImageView>(R.id.iv_loading)
        Glide.with(context!!).load(R.drawable.loading_bar).into(imageView)
        super.onAttach(context, view)
    }
}