package com.ja.assets.new_code.bussiness.me;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.MainActivity;
import com.ja.assets.R;
import com.ja.assets.databinding.FragmentUserLayoutBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.new_code.base.BaseFragment;
import com.ja.assets.ui.activity.login.LoginActivity;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.NotNull;

;

public class UserFragment extends BaseFragment {

    SimpleDraweeView sdv_header;

    private View ll_xiugaimima;

    private View ll_kefurexian;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.new_fragment_user_layout, container, false);
        initView(rootView);
        return rootView;
    }

    void initView(View rootView) {
        sdv_header = rootView.findViewById(R.id.sdv_header);
        sdv_header.setImageDrawable(getContext().getDrawable(R.drawable.icon_person_default));

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