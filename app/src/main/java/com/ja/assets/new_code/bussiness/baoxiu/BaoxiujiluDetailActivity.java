package com.ja.assets.new_code.bussiness.baoxiu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.JiluXunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuDetailBean;
import com.ja.assets.new_code.bussiness.bean.result.JiluXunjianDetail;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


public class BaoxiujiluDetailActivity extends Activity {


    View iv_back;
    public TextView tv_epcid;
    public TextView tv_weixiudanhao;
    public TextView tv_weixiuzhuangtai;
    public TextView tv_shenqinngyonghu;
    public TextView tv_shenqingbumen;
    public TextView tv_chuangjianshijian;

    SimpleDraweeView sdv_xunjianjieguo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //沉浸式代码配置
        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
        //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
        //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }
        //用来设置整体下移，状态栏沉浸
        StatusBarUtil.setRootViewFitsSystemWindows(this, false);
        setContentView(R.layout.activity_baoxiujilu_detail);
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
        tv_epcid = findViewById(R.id.tv_epcid);
        tv_weixiudanhao = findViewById(R.id.tv_weixiudanhao);
        tv_weixiuzhuangtai = findViewById(R.id.tv_weixiuzhuangtai);
        tv_shenqinngyonghu = findViewById(R.id.tv_shenqinngyonghu);
        tv_shenqingbumen = findViewById(R.id.tv_shenqingbumen);
        tv_chuangjianshijian = findViewById(R.id.tv_chuangjianshijian);
        sdv_xunjianjieguo = findViewById(R.id.sdv_xunjianjieguo);

    }

    void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String token = ACacheUtil.getToken();
        JiluXunjianPostBean bean = new JiluXunjianPostBean();
        bean.id = id;
        ApiUtils.getApiService().listByZcReId(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<BaoxiuDetailBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<BaoxiuDetailBean>>> response, BaseBean<ArrayList<BaoxiuDetailBean>> message) {
                if (message.code == 0) {
                    BaoxiuDetailBean bean=message.data.get(0);
                    tv_epcid.setText(bean.epcid);
                    tv_weixiudanhao.setText(bean.code);
                    if (bean.status == 1) {
                        tv_weixiuzhuangtai.setText("审核中");
                    } else {
                        tv_weixiuzhuangtai.setText("审核完毕");
                    }
                    tv_shenqinngyonghu.setText(bean.zcName);
                    tv_shenqingbumen.setText(bean.glDeptName);
                    tv_chuangjianshijian.setText(bean.startUseTime);

                    sdv_xunjianjieguo.setImageURI(Uri.parse(bean.imgUrl));


                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<BaoxiuDetailBean>>> call, Throwable t) {

            }
        });

    }


}