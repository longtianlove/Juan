package com.ja.assets.new_code.bussiness.me;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseJavaActivity;
import com.ja.assets.ui.activity.login.LoginActivity;
import com.ja.assets.utils.ACacheUtil;

public class NewCode_SetUpActivity extends BaseJavaActivity {

    private View iv_back;
    private View exitLogon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcode_activity_set_up);
        initView();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

        exitLogon = findViewById(R.id.exitLogon);
        exitLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ACacheUtil.deleteUsername();
                ACacheUtil.deletePassword();
                ACacheUtil.deleteToken();
                ACacheUtil.deleteUserInfo();
                Intent intent = new Intent(NewCode_SetUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

}
