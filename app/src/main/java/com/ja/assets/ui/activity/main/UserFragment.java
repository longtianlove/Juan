package com.ja.assets.ui.activity.main;

import android.content.Intent;
import android.view.View;

import com.fixed.u8.ui.base.BaseFragment;
import com.ja.assets.MainActivity;
import com.ja.assets.R;
import com.ja.assets.databinding.FragmentUserLayoutBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.ui.activity.login.LoginActivity;
import com.ja.assets.utils.ACacheUtil;
;import org.jetbrains.annotations.NotNull;

public class UserFragment extends BaseFragment implements HandlerClickListener {

    private FragmentUserLayoutBinding userBinding;
    private MainActivity mainActivity;

    @Override
    protected void setOnCreate() {
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_layout;
    }

    @Override
    protected void initDataView() {
        userBinding = (FragmentUserLayoutBinding) getFragmentDataBinding();
        userBinding.setClick(this);

    }

    @Override
    public void handlerClick(@NotNull View view) {
        switch (view.getId()) {
            case R.id.logout:
                ACacheUtil.deleteUsername();
                ACacheUtil.deletePassword();
                ACacheUtil.deleteToken();
                ACacheUtil.deleteUserInfo();
                Intent intent = new Intent(mainActivity, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}