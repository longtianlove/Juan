package com.ja.assets.new_code.bussiness.me;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.ACacheUtil;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

/**
 * Created by dragon on 2019/6/29.
 */

public class MeMessageActivity extends Activity {
    ImageView iv_back;
    TextView tv_username;
    TextView tv_nickname;
    TextView tv_bumen;
    TextView tv_phone;
    TextView tv_email;
    TextView tv_birthday;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memessage);
        initView();
    }

    void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        tv_username=findViewById(R.id.tv_username);

        tv_nickname = (TextView) findViewById(R.id.tv_nickname);

        tv_bumen=findViewById(R.id.tv_bumen);

        tv_phone = (TextView) findViewById(R.id.tv_phone);

        tv_email=findViewById(R.id.tv_email);

        tv_birthday=findViewById(R.id.tv_birthday);

    }


    @Override
    protected void onStart() {
        super.onStart();


        tv_username.setText(ACacheUtil.getUserInfo().getUsername());
        tv_nickname.setText(ACacheUtil.getUserInfo().getNickname());
        tv_bumen.setText(ACacheUtil.getUserInfo().getDeptName());
        tv_phone.setText(ACacheUtil.getUserInfo().getPhone());
        tv_email.setText(ACacheUtil.getUserInfo().getEmail());
        tv_birthday.setText(ACacheUtil.getUserInfo().getBirthday());
    }


}
