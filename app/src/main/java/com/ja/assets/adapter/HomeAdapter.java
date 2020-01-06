package com.ja.assets.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fixed.u8.animation.MyBaseViewHolder;
import com.ja.assets.R;
import com.ja.assets.model.HomePage01;

import java.util.List;

public class HomeAdapter extends BaseQuickAdapter<HomePage01, MyBaseViewHolder> {
    private Context context;
    public HomeAdapter(Context context ,int layoutResId, @Nullable List<HomePage01> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(@NonNull MyBaseViewHolder helper, HomePage01 item) {
        helper.setText(R.id.itemHomeModelTV, item.getFunName());
        helper.setImgLoad(context, R.id.itemHomeModelImg, item.getFunImgId(), item.getFunImgId(), item.getFunImgId());
    }
}
