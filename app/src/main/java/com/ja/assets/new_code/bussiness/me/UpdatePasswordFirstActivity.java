package com.ja.assets.new_code.bussiness.me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseJavaActivity;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by dragon on 2019/6/28.
 */

public class UpdatePasswordFirstActivity extends BaseJavaActivity {
    View iv_back;
    EditText et_password;
    ImageView iv_password_yincang;
    TextView tv_done;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatepwd_first);
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





        tv_done = (TextView) findViewById(R.id.tv_done);
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String password = et_password.getText().toString();
//                UpdatePswPostBean bean = new UpdatePswPostBean();
//                bean.userId = UserInstance.getInstance().getUid();
//                bean.token = UserInstance.getInstance().getToken();
//                bean.password = password;
//                ApiUtils.getApiService().modifypasswrod(bean).enqueue(new TaiShengCallback<BaseBean>() {
//                    @Override
//                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
//                        switch (message.code) {
//                            case Constants.HTTP_SUCCESS:
//                                Intent intent = new Intent(UpdatePasswordFirstActivity.this, UpdatePasswordLastActivity.class);
//                                startActivity(intent);
//                                finish();
//                                break;
//                            default:
//                                ToastUtil.showTost("更新失败");
//                                break;
//                        }
//                    }
//
//                    @Override
//                    public void onFail(Call<BaseBean> call, Throwable t) {
//
//                    }
//                });
            }
        });
        et_password = (EditText) findViewById(R.id.et_password);
        et_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    tv_done.setEnabled(true);
                } else {
                    tv_done.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        iv_password_yincang = (ImageView) findViewById(R.id.iv_password_yincang);
        yincang = true;
        iv_password_yincang.setImageDrawable(getResources().getDrawable(R.drawable.icon_yincang));
        et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        iv_password_yincang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yincang) {
                    yincang = false;
                    iv_password_yincang.setImageDrawable(getResources().getDrawable(R.drawable.icon_xianshi));
                    et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    yincang = true;
                    iv_password_yincang.setImageDrawable(getResources().getDrawable(R.drawable.icon_yincang));
                    et_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

                }
                if (et_password.getText().length() > 0) {
                    et_password.setSelection(et_password.getText().length());
                }
            }
        });


    }

    boolean yincang = true;


}
