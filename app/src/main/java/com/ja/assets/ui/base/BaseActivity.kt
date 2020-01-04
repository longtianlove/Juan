package com.ja.assets.ui.base

import android.content.pm.ActivityInfo
import android.os.Bundle


abstract class BaseActivity : BaseOrientationActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //禁止横竖屏切换
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}

