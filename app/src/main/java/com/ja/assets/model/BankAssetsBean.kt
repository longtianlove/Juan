package com.ja.assets.model

import androidx.lifecycle.LiveData
import java.io.Serializable

data class LoginInfo(var loginTime: Long, var token: String, var userType: String, var code: String)

data class HomePage01(var position: Int, var funImgId: Int, var funName: String)

data class MainData(var count: Int, val value: MainData) : LiveData<MainData>(value)

data class DeptBean(var id: Int, var deptname: String) : Serializable

data class PurchaseAssetsInfo(
    var assetsName: String,
    var purpose: String,
    var departmentName: String,
    var departmentId: Int,
    var specsType: String,
    var purchaseQuantity: Int,
    var meteringCompany: String,
    var productBrand: String,
    var supplierName: String,
    var purchaseUnitPrice: String
) : Serializable


data class AssetsInfo(
    //资产名称
    var AssetsName: String,
    //资产追溯码
    var assetsTraceCode: String,
    //资产编码
    var assetsNumber: String,
    //资产类别
    var assetsType: String,
    //管理部门
    var adminDepartmentName: String,
    //管理部门ID
    var adminDepartmentId: String,
    //使用部门
    var useDepartmentName: String,
    //使用部门ID
    var useDepartmentId: String,
    //资产来源
    var assetsSource: String,
    //开始使用日期
    var startUserTime: String,
    //剩余期限
    var remainingPeriod: String,
    //原值
    var originalValue: String,
    //净值
    var netWorth: String
)


data class ResultResponse<out T>(val Code: Int, val message: String, val data: T) {
    fun isSuccess() = Code == 0
}


data class UserInfo(
    var id: Long,
    var createTime: String,
    var updateTime: String,
    var username: String,
    var nickname: String,
    var headImgUrl: String,
    var phone: String,
    var email: String,
    var birthday: String,
    var sex: Int,
    var status: Int,
    var memo: String,
    var c03: String,
    var deptId: Long
) : Serializable


data class HomeIndexCount(
    var zcValue: Int, var zcCount: Int, var caigouCount: Int, var diaoboCount: Int,
    var pandianCount: Int, var baoxiuCount: Int, var xunjianCount: Int, var chuzhiCount: Int,
    var bwbZcCount: Int, var kjbZcCount: Int, var zhbZcCount: Int, var yybZcCount: Int
)

data class PurchaseAudit(var username: String)
data class AllocationApply(var username: String)
data class InventoryBean(var username: String)
data class InventoryRecord(var username: String)
data class InspectionBean(var username: String)
data class PatrolCheck(var username: String)