package com.ja.assets.ui.activity.other;

import android.widget.TextView;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivitySweepCodeBinding;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.ToastUtil;

public class SweepCodeActivity extends BaseActivity {

    private ActivitySweepCodeBinding sweepCodeBinding;
    private String traceCode;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_sweep_code;
    }

    @Override
    protected void initView() {
        TextView titleCenterText = findViewById(R.id.titleCenterText);
        titleCenterText.setText(ToastUtil.getString(R.style.assetsInfo));

        LinearLayoutCompat commonLeftLinearLayout = findViewById(R.id.commonLeftLinearLayout);
        sweepCodeBinding = (ActivitySweepCodeBinding) getViewDataBinding();
        commonLeftLinearLayout.setOnClickListener(v -> finish());
        traceCode = getIntent().getStringExtra("traceCode");
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {

    }
}
