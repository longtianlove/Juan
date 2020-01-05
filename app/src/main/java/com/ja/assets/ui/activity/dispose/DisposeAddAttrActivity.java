package com.ja.assets.ui.activity.dispose;

import android.content.Intent;
import android.view.View;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivityDisposeAddAttrBinding;
import com.ja.assets.glide.GlideImgUtils;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.PhotoUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

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
                new PhotoUtils().pickImage(this, 1, 0);
                break;
            case R.id.lossReportImg:
                new PhotoUtils().pickImage(this, 1, 1);
                break;
            case R.id.submitDisposeAddBtn:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null && resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 0:
                List<String> photoList1 = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                GlideImgUtils.loadImage(this, photoList1.get(0), R.mipmap.add_img, disposeAddAttrBinding.disposalAttrImg);
                break;
            case 1:
                List<String> photoList2 = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                GlideImgUtils.loadImage(this, photoList2.get(0), R.mipmap.add_img, disposeAddAttrBinding.lossReportImg);
                break;
        }
    }
}
