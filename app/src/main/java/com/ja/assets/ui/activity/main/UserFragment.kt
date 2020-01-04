package com.ja.assets.ui.activity.main

import android.content.Intent
import android.view.View
import com.fixed.u8.ui.base.BaseFragment
import com.ja.assets.MainActivity
import com.ja.assets.R
import com.ja.assets.databinding.FragmentUserLayoutBinding
import com.ja.assets.listener.HandlerClickListener
import com.ja.assets.ui.activity.login.LoginActivity
import com.ja.assets.utils.ACacheUtil
import kotlinx.android.synthetic.main.fragment_user_layout.*

class UserFragment : BaseFragment(), HandlerClickListener {


    private var userBinding: FragmentUserLayoutBinding? = null
    var mainActivity: MainActivity? = null
    override fun setOnCreate() {
        mainActivity = activity as MainActivity
    }

    override fun getLayoutId(): Int = R.layout.fragment_user_layout

    override fun initDataView() {
        userBinding = fragmentDataBinding as FragmentUserLayoutBinding
        userBinding?.click = this

    }

    override fun handlerClick(view: View) {
        when (view.id) {
            R.id.logout -> {
                ACacheUtil.deleteUsername()
                ACacheUtil.deletePassword()
                ACacheUtil.deleteToken()
                ACacheUtil.deleteUserInfo()
                val intent = Intent(mainActivity!!, LoginActivity::class.java)
                startActivity(intent)
            }
        }
    }
}