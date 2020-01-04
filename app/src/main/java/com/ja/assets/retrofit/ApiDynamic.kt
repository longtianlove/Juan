package com.ja.assets.retrofit

import com.ja.assets.model.LoginInfo
import com.ja.assets.model.ResultResponse
import com.ja.assets.model.UserInfo
import retrofit2.http.*


/**
 * 数据接口api
 */


interface ApiDynamic {

    /**
     * 登录
     */
    @POST("login")
    @FormUrlEncoded
    suspend fun login(@Field("username") userName: String, @Field("password") passWord: String): LoginInfo


    /**
     * 获取用户信息
     */
    @GET("api/getUserInfo")
    suspend fun getUserInfo(@Header("token") token: String): ResultResponse<UserInfo>


}