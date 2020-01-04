package com.ja.assets.ui.activity.splash

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.ja.assets.MainActivity
import com.ja.assets.R
import com.ja.assets.model.LoginInfo
import com.ja.assets.model.ResultResponse
import com.ja.assets.model.UserInfo
import com.ja.assets.retrofit.RetrofitClient
import com.ja.assets.ui.activity.login.LoginActivity
import com.ja.assets.ui.base.PermissionActivity
import com.ja.assets.utils.ACacheUtil
import com.ja.assets.utils.ToastUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SplashActivity : PermissionActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = layoutInflater.inflate(R.layout.activity_splash, null)
        val animation = AlphaAnimation(0.3f, 1.0f)
        animation.duration = 1000
        animation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                assetsLogin()
            }
        })
        view.animation = animation
        setContentView(view)
    }


    private fun assetsLogin() {
        val username = ACacheUtil.getUsername()
        val password = ACacheUtil.getPassword()
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            Log.e("error", "登陆失败")
            finish()
        } else {
            launch {
                try {
                    val loginInfo: LoginInfo = RetrofitClient.networkService.login(ACacheUtil.getUsername(), ACacheUtil.getPassword())
                    ACacheUtil.setToken(loginInfo.token)
                    val resultResponse: ResultResponse<UserInfo> = RetrofitClient.networkService.getUserInfo(loginInfo.token)
                    if (resultResponse.isSuccess()) {
                        ACacheUtil.setUserInfo(resultResponse.data)
                        val intent = Intent(this@SplashActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        ToastUtil.toast("登陆失败")
                    }
                } catch (e: Exception) {
                    Log.e("error", e.message.toString())
                    ToastUtil.toast("登陆失败")
                    val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }
}
