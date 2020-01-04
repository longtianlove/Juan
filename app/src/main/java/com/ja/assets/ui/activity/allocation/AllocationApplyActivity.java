package com.ja.assets.ui.activity.allocation;


import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.ja.assets.R;
import com.ja.assets.adapter.AllocationApplyAdapter;
import com.ja.assets.databinding.ActivityAllocationApplyBinding;
import com.ja.assets.model.AllocationApply;
import com.ja.assets.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class AllocationApplyActivity extends BaseActivity {
    private AllocationApplyAdapter allocationApplyAdapter;
    private List<AllocationApply> allocationApplyList;
    private ActivityAllocationApplyBinding allocationApplyBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_allocation_apply;
    }

    @Override
    protected void initView() {
        allocationApplyBinding = (ActivityAllocationApplyBinding) getViewDataBinding();
    }

    @Override
    protected void initAdapter() {
        allocationApplyList = new ArrayList<>();
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(this, allocationApplyBinding.allocationApplyRecycler);
        recyclerViewUtilKt.initRecyclerView();
        allocationApplyAdapter = new AllocationApplyAdapter(R.layout.item_purchase_audit, allocationApplyList);
        recyclerViewUtilKt.setAdapter(allocationApplyAdapter);
    }

    @Override
    protected void initData() {

    }
}
