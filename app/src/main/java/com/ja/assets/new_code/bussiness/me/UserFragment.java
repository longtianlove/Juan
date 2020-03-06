package com.ja.assets.new_code.bussiness.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;

import com.ja.assets.model.UserInfo;
import com.ja.assets.new_code.base.BaseJavaFragment;
import com.ja.assets.ui.activity.login.LoginActivity;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.NotNull;

;

public class UserFragment extends BaseJavaFragment {

    SimpleDraweeView sdv_header;
    private TextView tv_nickname;
    private TextView tv_bumen;


    private View ll_gerenziliao;

    private View ll_xiugaimima;

    private View ll_kefurexian;


    private View ll_setting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.new_fragment_user_layout, container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    void initView(View rootView) {
        sdv_header = rootView.findViewById(R.id.sdv_header);
        sdv_header.setImageDrawable(getContext().getDrawable(R.drawable.icon_person_default));

        tv_nickname = rootView.findViewById(R.id.tv_nickname);


        tv_bumen = rootView.findViewById(R.id.tv_bumen);


        ll_gerenziliao = rootView.findViewById(R.id.ll_gerenziliao);
        ll_gerenziliao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MeMessageActivity.class);
                startActivity(intent);
            }
        });

        ll_xiugaimima = rootView.findViewById(R.id.ll_xiugaimima);
        ll_xiugaimima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdatePasswordFirstActivity.class);
                startActivity(intent);
            }
        });

        ll_kefurexian = rootView.findViewById(R.id.ll_kefurexian);
        ll_kefurexian.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:0359-2049830");
                intent.setData(data);
                startActivity(intent);
            }
        });


        ll_setting = rootView.findViewById(R.id.ll_setting);
        ll_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewCode_SetUpActivity.class);
                startActivity(intent);
            }
        });
    }

    void initData() {
        String nickname = ACacheUtil.getUserInfo().getNickname();
        tv_nickname.setText(nickname);

        String bumen = ACacheUtil.getUserInfo().getDeptName();
        tv_bumen.setText("隶属于：" + bumen);
    }


    public void handlerClick(@NotNull View view) {
        switch (view.getId()) {
            case R.id.logout:
                ACacheUtil.deleteUsername();
                ACacheUtil.deletePassword();
                ACacheUtil.deleteToken();
                ACacheUtil.deleteUserInfo();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}