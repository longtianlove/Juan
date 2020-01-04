package com.ja.assets.ui.activity.login

import android.content.Intent
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.ja.assets.MainActivity
import com.ja.assets.R
import com.ja.assets.databinding.ActivityLoginBinding
import com.ja.assets.model.LoginInfo
import com.ja.assets.model.ResultResponse
import com.ja.assets.model.UserInfo
import com.ja.assets.retrofit.RetrofitClient
import com.ja.assets.ui.base.BaseActivity
import com.ja.assets.utils.ACacheUtil
import com.ja.assets.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.common_title.*
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {
    private var loginBinding: ActivityLoginBinding? = null
    private var account: String? = null
    private var password: String? = null
    override fun getLayoutId(): Int = R.layout.activity_login
    override fun initView() {
        loginBinding = viewDataBinding as ActivityLoginBinding
        loginBinding?.handlerClick = this
        commonLeftLinearLayout.visibility = View.GONE
        titleCenterText.text = getString(R.string.Login)
    }

    override fun initAdapter() = Unit

    override fun initData() {
        account = ACacheUtil.getUsername()
        password = ACacheUtil.getPassword()
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            loginAccount.setText(account)
            loginPassword.setText(password)
        } else {
            login(account!!, password!!)
        }

        displayPassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //如果选中，显示密码
                loginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                //否则隐藏密码
                loginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }

        loginPassword.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.ACTION_DOWN) {
                assetsLogin()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }

    fun loginHandleClick(view: View) {
        when (view.id) {
            R.id.deletePassword -> {
                loginAccount.setText("")
            }
            R.id.loginBtn -> {
                assetsLogin()
            }
        }
    }

    private fun assetsLogin() {
        account = loginAccount.text.toString().trim()
        password = loginPassword.text.toString().trim()
        if (TextUtils.isEmpty(account!!)) {
            return ToastUtil.toast("用户名不能为空")
        }
        if (TextUtils.isEmpty(password!!)) {
            return ToastUtil.toast("用户密码不能为空")
        }
        login(account!!, password!!)
    }


    private fun login(account: String, password: String) {
        launch {
            try {
                val loginInfo: LoginInfo = RetrofitClient.networkService.login(account, password)
                ACacheUtil.setToken(loginInfo.token)
                ACacheUtil.setUsername(account)
                ACacheUtil.setPassword(password)
                val resultResponse: ResultResponse<UserInfo> = RetrofitClient.networkService.getUserInfo(loginInfo.token)
                if (resultResponse.isSuccess()) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    ToastUtil.toast("登陆失败")
                }
            } catch (e: Exception) {
                Log.e("error", e.message.toString())
                ToastUtil.toast("登陆失败")
            }
        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (((keyCode == KeyEvent.KEYCODE_BACK) || (keyCode == KeyEvent.KEYCODE_HOME)) && event?.repeatCount == 0) {
            myActivityManager?.AppExit()
        }
        return super.onKeyDown(keyCode, event)
    }

}
