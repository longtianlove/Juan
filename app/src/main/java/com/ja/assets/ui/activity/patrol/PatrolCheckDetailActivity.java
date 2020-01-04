package com.ja.assets.ui.activity.patrol;

import android.view.View;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivityInspectionDetailBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.ui.base.BaseActivity;

import org.jetbrains.annotations.NotNull;


public class PatrolCheckDetailActivity extends BaseActivity implements HandlerClickListener {


    private ActivityInspectionDetailBinding inspectionDetailBinding;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_inspection_detail;
    }

    @Override
    protected void initView() {
        inspectionDetailBinding = (ActivityInspectionDetailBinding) getViewDataBinding();
        inspectionDetailBinding.setClick(this);
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void handlerClick(@NotNull View view) {
        switch (view.getId()) {
            case R.id.lossReportImg:
                break;
            case R.id.submitInspectionDetailBtn:
                break;
            default:
                break;
        }
    }
}