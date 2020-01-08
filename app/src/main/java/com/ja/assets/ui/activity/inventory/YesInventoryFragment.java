package com.ja.assets.ui.activity.inventory;

import com.fixed.u8.ui.base.BaseFragment;
import com.ja.assets.R;

public class YesInventoryFragment extends BaseFragment {
    private InventoryActivity inventoryActivity;

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

    }
}