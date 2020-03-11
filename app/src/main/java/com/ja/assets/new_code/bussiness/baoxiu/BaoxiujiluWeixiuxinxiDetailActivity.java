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
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;


public class BaoxiujiluWeixiuxinxiDetailActivity extends Activity {


    View iv_back;


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
        setContentView(R.layout.activity_baoxiujilu_weixiuxinxi_detail);
        initView();

    }

    TextView tv_zcCodenum;
    TextView tv_epcid;
    TextView tv_zcName;
    TextView tv_shengyuqixian;
    TextView tv_daoqiqixian;
    TextView tv_baoxiuqixian;
    TextView tv_repairMode;
    TextView tv_deliverMode;
    TextView tv_fuwushangmingcheng;
    TextView tv_dizhi;
    TextView tv_lianxiren;
    TextView tv_dianhua;
    TextView tv_songxiudizhi;

    void initView() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_zcCodenum = findViewById(R.id.tv_zcCodenum);
        tv_epcid = findViewById(R.id.tv_epcid);
        tv_zcName = findViewById(R.id.tv_zcName);
        tv_shengyuqixian = findViewById(R.id.tv_shengyuqixian);
        tv_daoqiqixian = findViewById(R.id.tv_daoqiqixian);
        tv_baoxiuqixian = findViewById(R.id.tv_baoxiuqixian);
        tv_repairMode = findViewById(R.id.tv_repairMode);
        tv_deliverMode = findViewById(R.id.tv_deliverMode);
        tv_fuwushangmingcheng = findViewById(R.id.tv_fuwushangmingcheng);
        tv_dizhi = findViewById(R.id.tv_dizhi);
        tv_lianxiren = findViewById(R.id.tv_lianxiren);
        tv_dianhua = findViewById(R.id.tv_dianhua);
        tv_songxiudizhi = findViewById(R.id.tv_songxiudizhi);
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        BaoxiuDetailBean bean = BaoxiujiluDetailActivity2.madapter.mData.get(position);
        tv_zcCodenum.setText(bean.zcCodenum);
        tv_epcid.setText(bean.epcid);
        tv_zcName.setText(bean.zcName);
        tv_shengyuqixian.setText(bean.remainingperiod);
        tv_daoqiqixian.setText(bean.useMonths);
        tv_baoxiuqixian.setText(bean.warrantyperiod);
        tv_repairMode.setText(bean.repairMode);
        tv_deliverMode.setText(bean.deliverMode);
        tv_fuwushangmingcheng.setText(bean.outCompany);
        tv_dizhi.setText(bean.outAddress);
        tv_lianxiren.setText(bean.outUsername);
        tv_dianhua.setText(bean.outPhone);
        tv_songxiudizhi.setText(bean.repairAddress);

    }


}