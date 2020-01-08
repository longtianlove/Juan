package com.ja.assets.ui.activity.inventory;

import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.ja.assets.R;
import com.ja.assets.adapter.InventoryRecordAdapter;
import com.ja.assets.databinding.ActivityInventoryRecordBinding;
import com.ja.assets.model.InventoryRecord;
import com.ja.assets.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class InventoryRecordActivity extends BaseActivity {
    private ActivityInventoryRecordBinding inventoryRecordBinding;
    private InventoryRecordAdapter inventoryRecordAdapter;
    private List<InventoryRecord> inventoryRecordList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory_record;
    }

    @Override
    protected void initView() {
        inventoryRecordBinding = (ActivityInventoryRecordBinding) getViewDataBinding();
    }

    @Override
    protected void initAdapter() {
        inventoryRecordList = new ArrayList<>();
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(this, inventoryRecordBinding.inventorySurplusAssetsRecycler);
        recyclerViewUtilKt.initRecyclerView();
        inventoryRecordAdapter = new InventoryRecordAdapter(R.layout.item_inventory_record, inventoryRecordList);
        recyclerViewUtilKt.setAdapter(inventoryRecordAdapter);
    }

    @Override
    protected void initData() {

    }
}
