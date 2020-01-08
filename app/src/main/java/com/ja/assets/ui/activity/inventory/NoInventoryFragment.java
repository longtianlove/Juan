package com.ja.assets.ui.activity.inventory;

import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.fixed.u8.ui.base.BaseFragment;
import com.ja.assets.R;
import com.ja.assets.adapter.InventoryAdapter;
import com.ja.assets.databinding.FragmentYesInventoryBinding;
import com.ja.assets.model.InventoryBean;

import java.util.ArrayList;
import java.util.List;

public class NoInventoryFragment extends BaseFragment {
    private InventoryActivity inventoryActivity;
    private FragmentYesInventoryBinding inventoryBinding;
    private InventoryAdapter inventoryAdapter;
    private List<InventoryBean> inventoryList;


    @Override
    protected void setOnCreate() {
        inventoryActivity = (InventoryActivity) getActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_yes_inventory;
    }

    @Override
    protected void initDataView() {
        inventoryBinding = (FragmentYesInventoryBinding) getFragmentDataBinding();

        initAdapter();
    }

    private void initAdapter() {
        inventoryList = new ArrayList<>();
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(inventoryActivity, inventoryBinding.inventoryRecycler);
        recyclerViewUtilKt.initRecyclerView();
        inventoryAdapter = new InventoryAdapter(R.layout.item_fragment_inventory, inventoryList);
        recyclerViewUtilKt.setAdapter(inventoryAdapter);
    }
}