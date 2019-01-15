package com.liu.kotlin.wanandroid.kotlinwanandroid

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        rl_main.setOnClickListener {
            LoginActivity.start(this@SplashActivity)
            finish()
        }
    }
}
