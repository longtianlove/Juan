package com.ja.assets.ui.activity.dispose;

import android.view.View;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivityDisposeAddAttrBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.ui.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

public class DisposeAddAttrActivity extends BaseActivity implements HandlerClickListener {

    private ActivityDisposeAddAttrBinding disposeAddAttrBinding;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dispose_add_attr;
    }

    @Override
    protected void initView() {
        disposeAddAttrBinding = (ActivityDisposeAddAttrBinding) getViewDataBinding();
        disposeAddAttrBinding.setClick(this);
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
            case R.id.assetsNameLinear:
                break;
            case R.id.disposeTypeLinear:
                break;
            case R.id.disposalReasonTV:
                break;
            case R.id.disposalAttrImg:

                break;
            case R.id.lossReportImg:
                break;
            case R.id.submitDisposeAddBtn:
                break;
            default:
                break;
        }

    }
}
