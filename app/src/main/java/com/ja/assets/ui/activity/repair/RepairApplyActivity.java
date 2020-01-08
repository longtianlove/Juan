package com.ja.assets.ui.activity.repair;


import android.app.Dialog;
import android.os.Bundle;

import com.ja.assets.R;
import com.ja.assets.model.UserInfo;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.ToastUtil;

import org.jetbrains.annotations.Nullable;

public class RepairApplyActivity extends BaseJavaActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
        UserInfo userInfo = ACacheUtil.getUserInfo();
        String nickname = ACacheUtil.getUserInfo().getNickname();
        String token = ACacheUtil.getToken();


        String username = ToastUtil.getString(R.string.address);

        toast("你好");

        ToastUtil.toast("你好");


        Dialog loadingDialog = ToastUtil.loadingDialog(this);
        loadingDialog.show();
        loadingDialog.dismiss();

    }

    //    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_repair_apply;
//    }
//
//    @Override
//    protected void initView() {
//
//    }
//
//    @Override
//    protected void initAdapter() {
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }
}