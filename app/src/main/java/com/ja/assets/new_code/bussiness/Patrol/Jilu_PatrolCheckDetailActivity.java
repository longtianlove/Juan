package com.ja.assets.new_code.bussiness.Patrol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.JiluXunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.JiluXunjianDetail;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.utils.ACacheUtil;


import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Response;


public class Jilu_PatrolCheckDetailActivity extends Activity {


    View iv_back;
    TextView tv_waiguan;
    TextView tv_gongneng;
    TextView tv_jieguo;
    TextView et_yijian;

    SimpleDraweeView sdv_xunjianjieguo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jilu_activity_inspection_detail);
        initView();
        initData();
    }

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_waiguan = findViewById(R.id.tv_waiguan);
        tv_gongneng = findViewById(R.id.tv_gongneng);
        tv_jieguo=findViewById(R.id.tv_jieguo);
        et_yijian=findViewById(R.id.et_yijian);
        sdv_xunjianjieguo=findViewById(R.id.sdv_xunjianjieguo);

    }

    void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String token = ACacheUtil.getToken();
        JiluXunjianPostBean bean = new JiluXunjianPostBean();
        bean.id = id;
        ApiUtils.getApiService().inspectDetail(token, bean).enqueue(new JuanCallback<BaseBean<JiluXunjianDetail>>() {
            @Override
            public void onSuccess(Response<BaseBean<JiluXunjianDetail>> response, BaseBean<JiluXunjianDetail> message) {
                if (message.code == 0) {
                    if ("1".equals(message.data.appearance)) {
                        tv_waiguan.setText("完好");
                    } else {
                        tv_waiguan.setText("破损");
                    }
                    if ("1".equals(message.data.funct)) {
                        tv_gongneng.setText("正常");
                    } else {
                        tv_gongneng.setText("异常");
                    }
                    if ("1".equals(message.data.result)) {
                        tv_jieguo.setText("达标");
                    } else {
                        tv_jieguo.setText("不达标");
                    }
                    et_yijian.setText(message.data.opinion);
                    sdv_xunjianjieguo.setImageURI(Uri.parse(message.data.img));


                }
            }

            @Override
            public void onFail(Call<BaseBean<JiluXunjianDetail>> call, Throwable t) {

            }
        });

    }


}