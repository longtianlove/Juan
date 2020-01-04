package com.ja.assets.ui.activity.purchase

import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.PurchaseAuditAdapter
import com.ja.assets.model.PurchaseAudit
import com.ja.assets.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_purchase_audit.*

class PurchaseAuditActivity : BaseActivity() {
    private var purchaseAuditAdapter: PurchaseAuditAdapter? = null
    private var purchaseAuditList: MutableList<PurchaseAudit>? = null
    override fun getLayoutId(): Int = R.layout.activity_purchase_audit

    override fun initView() {

    }

    override fun initAdapter() {
        purchaseAuditList = mutableListOf()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, purchaseAuditRecycler)
        recyclerViewUtilKt.initRecyclerView()
        purchaseAuditAdapter = PurchaseAuditAdapter(R.layout.item_purachse_apply_layout, purchaseAuditList!!)
        recyclerViewUtilKt.setAdapter(purchaseAuditAdapter!!)
    }

    override fun initData() {



    }

}
