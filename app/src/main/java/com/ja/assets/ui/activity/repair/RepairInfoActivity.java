package com.ja.assets.ui.activity.repair;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivityRepairInfoBinding;
import com.ja.assets.ui.base.BaseActivity;

class RepairInfoActivity extends BaseActivity {
    private ActivityRepairInfoBinding repairInfoBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repair_info;
    }

    @Override
    protected void initView() {
        repairInfoBinding = (ActivityRepairInfoBinding)getViewDataBinding();
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {

    }
}