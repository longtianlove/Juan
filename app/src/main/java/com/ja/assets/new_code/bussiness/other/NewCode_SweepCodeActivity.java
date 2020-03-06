package com.ja.assets.new_code.bussiness.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivitySweepCodeBinding;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.GetZcInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.result.GetZcInfoResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.ACacheUtil;

import retrofit2.Call;
import retrofit2.Response;

public class NewCode_SweepCodeActivity extends Activity {

    View iv_back;
    TextView sweepCodeAssetsNameTV;
    TextView sweepCodeAssetTraceCodeTV;
    TextView sweepCodeAssetCodeTV;
    TextView sweepCodeAssetClassTV;
    TextView sweepCodeAdminDepartmentTV;
    TextView sweepCodeSourceTV;
    TextView sweepCodeStartUseDateTV;
    TextView sweepCodeRemainingPeriodTV;
    TextView sweepCodeOriginalValueTV;
    TextView sweepCodeNetWorthTV;

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
        setContentView(R.layout.newcode_activity_sweep_code);
        initView();
        initData();
    }

    void initView() {
        sweepCodeAssetsNameTV = findViewById(R.id.sweepCodeAssetsNameTV);
        sweepCodeAssetTraceCodeTV = findViewById(R.id.sweepCodeAssetTraceCodeTV);
        sweepCodeAssetCodeTV = findViewById(R.id.sweepCodeAssetCodeTV);
        sweepCodeAssetClassTV = findViewById(R.id.sweepCodeAssetClassTV);
        sweepCodeAdminDepartmentTV = findViewById(R.id.sweepCodeAdminDepartmentTV);
        sweepCodeSourceTV = findViewById(R.id.sweepCodeSourceTV);
        sweepCodeStartUseDateTV = findViewById(R.id.sweepCodeStartUseDateTV);
        sweepCodeRemainingPeriodTV = findViewById(R.id.sweepCodeRemainingPeriodTV);
        sweepCodeOriginalValueTV = findViewById(R.id.sweepCodeOriginalValueTV);
        sweepCodeNetWorthTV = findViewById(R.id.sweepCodeNetWorthTV);
    }

    void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String resultUrl = getIntent().getStringExtra("resultUrl");
        String token = ACacheUtil.getToken();
        GetZcInfoPostBean bean = new GetZcInfoPostBean();
        bean.epcid = resultUrl;
        ApiUtils.getApiService().getZcInfo(token, bean).enqueue(new JuanCallback<BaseBean<GetZcInfoResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<GetZcInfoResultBean>> response, BaseBean<GetZcInfoResultBean> message) {
                if (message.data == null) {
                    ToastUtil.showAtCenterLong("该资产未入账");
                } else {
                    sweepCodeAssetsNameTV.setText(message.data.zcName);
                    sweepCodeAssetTraceCodeTV.setText(message.data.epcid);
                    sweepCodeAssetCodeTV.setText(message.data.zcCodenum);
                    sweepCodeAssetClassTV.setText(message.data.zcCategoryName);
                    sweepCodeAdminDepartmentTV.setText(message.data.glDeptName);
                    sweepCodeSourceTV.setText(message.data.zcFrom);
                    sweepCodeStartUseDateTV.setText(message.data.startUseTime);
                    sweepCodeRemainingPeriodTV.setText(message.data.remainingperiod + "");
                    sweepCodeOriginalValueTV.setText(message.data.originalValue + "");
                    sweepCodeNetWorthTV.setText(message.data.net + "");
                }
            }

            @Override
            public void onFail(Call<BaseBean<GetZcInfoResultBean>> call, Throwable t) {

            }
        });

    }
}
