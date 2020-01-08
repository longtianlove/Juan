package com.ja.assets.ui.activity.purchase;
import	java.util.ArrayList;

import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.ja.assets.R;
import com.ja.assets.adapter.PurchaseAuditAdapter;
import com.ja.assets.databinding.ActivityPurchaseAuditBinding;
import com.ja.assets.model.PurchaseAudit;
import com.ja.assets.ui.base.BaseActivity;

import java.util.List;

public class PurchaseAuditActivity extends BaseActivity {
    private PurchaseAuditAdapter purchaseAuditAdapter;
    private List<PurchaseAudit> purchaseAuditList;
    private ActivityPurchaseAuditBinding purchaseAuditBinding;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchase_audit;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initAdapter() {
        purchaseAuditList = new ArrayList<>();
        RecyclerViewUtilKt recyclerViewUtilKt =new  RecyclerViewUtilKt(this,purchaseAuditBinding. purchaseAuditRecycler);
        recyclerViewUtilKt.initRecyclerView();
        purchaseAuditAdapter =new  PurchaseAuditAdapter(R.layout.item_purachse_apply_layout, purchaseAuditList);
        recyclerViewUtilKt.setAdapter(purchaseAuditAdapter);
    }

    @Override
    protected void initData() {

    }
}
