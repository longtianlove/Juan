package com.ja.assets.model

import androidx.lifecycle.LiveData
import java.io.Serializable

data class LoginInfo(var loginTime: Long, var token: String, var userType: String, var code: String)

data class HomePage01(var position: Int, var funImgId: Int, var funName: String)

data class MainData(var count: Int, val value: MainData) : LiveData<MainData>(value)

data class AdminBean(var adminId: Int, var adminName: String):Serializable

data class AssetsInfo(var AssetsName: String,
    var purpose: String,
    var departmentName: String,
    var departmentId: Int,
    var specsType: String,
    var purchaseQuantity: Int,
    var meteringCompany: String,
    var productBrand: String,
    var supplierName: String,
    var purchaseUnitPrice: String) : Serializable

data class ResultResponse<out T>(val Code: Int, val Msg: String, val data: T) {
    fun isSuccess() = Code == 0
}


data class UserInfo(var id: Long,
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
    var deptId: Long) : Serializable

data class PurchaseAudit(var username: String)
data class AllocationApply(var username: String)
data class InventoryBean(var username: String)
data class InventoryRecord(var username: String)
data class InspectionBean(var username: String)
data class PatrolCheck(var username:String)