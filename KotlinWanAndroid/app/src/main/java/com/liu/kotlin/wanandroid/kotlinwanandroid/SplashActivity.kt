package com.liu.kotlin.wanandroid.kotlinwanandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.liu.kotlin.wanandroid.kotlinwanandroid.mvp.module_user.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rl_main.setOnClickListener {
            LoginActivity.start(this@SplashActivity)
            finish()
        }
    }
}
