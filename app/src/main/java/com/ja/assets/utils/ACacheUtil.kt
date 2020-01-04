package com.ja.assets.utils

import com.ja.assets.model.UserInfo


object ACacheUtil {

    @JvmStatic
    fun setToken(token: String) {
        SpUtil().putString(ToastUtil.getContext(), "token", token)
    }

    @JvmStatic
    fun getToken(): String = SpUtil().getString(ToastUtil.getContext(), "token")

    @JvmStatic
    fun deleteToken() {
        SpUtil().deleteData(ToastUtil.getContext(), "token")
    }

    @JvmStatic
    fun setUsername(username: String) {
        SpUtil().putString(ToastUtil.getContext(), "username", username)
    }

    @JvmStatic
    fun getUsername(): String = SpUtil().getString(ToastUtil.getContext(), "username")

    @JvmStatic
    fun deleteUsername() {
        SpUtil().deleteData(ToastUtil.getContext(), "username")
    }

    @JvmStatic
    fun setPassword(password: String) {
        SpUtil().putString(ToastUtil.getContext(), "password", password)
    }

    @JvmStatic
    fun getPassword(): String = SpUtil().getString(ToastUtil.getContext(), "password")

    @JvmStatic
    fun deletePassword() {
        SpUtil().deleteData(ToastUtil.getContext(), "password")
    }

    @JvmStatic
    fun setUserInfo(userInfo: UserInfo) {
        SpUtil().putObject(ToastUtil.getContext(), "userInfo", userInfo)
    }

    @JvmStatic
    fun getUserInfo(): UserInfo = SpUtil().getObject(ToastUtil.getContext(), "userInfo")!!

    @JvmStatic
    fun deleteUserInfo() {
        SpUtil().deleteData(ToastUtil.getContext(), "userInfo")
    }

}