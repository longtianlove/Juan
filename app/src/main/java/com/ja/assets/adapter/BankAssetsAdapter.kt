package com.ja.assets.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.fixed.u8.animation.MyBaseViewHolder
import com.ja.assets.R
import com.ja.assets.model.*

//class HomeAdapter(private val context: Context, layoutResId: Int, data: MutableList<HomePage01>) :
//    BaseQuickAdapter<HomePage01, MyBaseViewHolder>(layoutResId, data) {
//    override fun convert(helper: MyBaseViewHolder, item: HomePage01?) {
//        helper.setText(R.id.itemHomeModelTV, item!!.funName)
//        helper.setImgLoad(context, R.id.itemHomeModelImg, item.funImgId, item.funImgId, item.funImgId)
//    }
//}

class AdminPopupAdapter(layoutResId: Int, data: MutableList<AdminBean>) : BaseQuickAdapter<AdminBean, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: AdminBean?) {
        helper.setText(R.id.itemAdminPopupTV, item!!.adminName)
    }
}

class PurchaseApplyAdapter(layoutResId: Int, data: MutableList<PurchaseAssetsInfo>) :
    BaseQuickAdapter<PurchaseAssetsInfo, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: PurchaseAssetsInfo?) {
        helper.addOnClickListener(R.id.itemPurchaseApplyEdit).addOnClickListener(R.id.itemPurchaseApplyUnAgree)
            .addOnClickListener(R.id.itemPurchaseApplyAgree)
            .addOnClickListener(R.id.itemPurchaseApplyDelete)
        helper.setText(R.id.itemPurchaseApplyName, item!!.assetsName)
        helper.setText(R.id.itemPurchaseApplyBrand, item.productBrand).setText(R.id.itemPurchaseApplySpecsModel, item.specsType)
            .setText(R.id.itemPurchaseApplyCompany, item.meteringCompany)
            .setText(R.id.itemPurchaseApplyUnitPurchasePrice, item.purchaseUnitPrice).setText(R.id.itemPurchaseApplyPurpose, item.purpose)
            .setText(R.id.itemPurchaseApplySupplierName, item.supplierName)
            .setText(R.id.itemPurchaseApplyAdministrationDepartmentText, item.departmentName)
            .setText(R.id.itemPurchaseApplyPurchaseQuantity, item.purchaseQuantity.toString())
    }
}

class PurchaseAuditAdapter(layoutResId: Int, data: MutableList<PurchaseAudit>) :
    BaseQuickAdapter<PurchaseAudit, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: PurchaseAudit?) {
        helper.setText(R.id.itemAdminPopupTV, item!!.username)
    }
}

class AllocationApplyAdapter(layoutResId: Int, data: MutableList<AllocationApply>) :
    BaseQuickAdapter<AllocationApply, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: AllocationApply?) {
        helper.setText(R.id.itemAdminPopupTV, item!!.username)
    }
}

class InventoryAdapter(layoutResId: Int, data: MutableList<InventoryBean>) :
    BaseQuickAdapter<InventoryBean, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: InventoryBean?) {
        helper.setText(R.id.itemAdminPopupTV, item!!.username)
    }
}

class InventoryRecordAdapter(layoutResId: Int, data: MutableList<InventoryRecord>) :
    BaseQuickAdapter<InventoryRecord, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: InventoryRecord?) {

    }
}

class InspectionAdapter(layoutResId: Int, data: MutableList<InspectionBean>) :
    BaseQuickAdapter<InspectionBean, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: InspectionBean?) {

    }
}

class PatrolCheckAdapter(layoutResId: Int, data: MutableList<PatrolCheck>) :
    BaseQuickAdapter<PatrolCheck, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: PatrolCheck) {

    }
}


class DepartmentAdapter(layoutResId: Int, data: MutableList<AdminBean>) : BaseQuickAdapter<AdminBean, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: AdminBean) {
        helper.setText(R.id.itemAdminPopupTV, item!!.adminName)
    }
}

class SelectAssetsAdapter(layoutResId: Int, data: MutableList<PurchaseAssetsInfo>) : BaseQuickAdapter<PurchaseAssetsInfo, MyBaseViewHolder>(layoutResId, data) {
    override fun convert(helper: MyBaseViewHolder, item: PurchaseAssetsInfo?) {

    }
}






