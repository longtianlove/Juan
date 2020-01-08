package com.ja.assets.retrofit

import com.ja.assets.model.*
import retrofit2.http.*
import rx.Observable


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
    @GET("/api/getUserInfo")
    suspend fun getUserInfo(@Header("token") token: String): ResultResponse<UserInfo>

    /**
     * 获取首页资产净值和资产数量
     */
    @POST("/api/getZcValueAndZcNumber")
    fun getZcValueAndZcNumber(@Header("token") token: String): Observable<ResultResponse<HomeIndexCount>>


    /**
     * 获取所有的待办事项列表
     */
    @POST("/api/getAllWailDealList")
    fun getAllWailDealList(@Header("token") token: String): Observable<ResultResponse<MutableList<DeptBean>>>


    /**
     * 获取所有的部门和支行
     */
    @POST("/api/getAllManagerDeptList")
    fun getAllBranchDeptList(@Header("token") token: String): Observable<ResultResponse<MutableList<DeptBean>>>


    /**
     * 获取所有的管理部门
     */
    @POST("/api/getAllManagerDeptList")
    fun getAllManagerDeptList(@Header("token") token: String): Observable<ResultResponse<MutableList<DeptBean>>>

}