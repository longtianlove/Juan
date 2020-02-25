package com.ja.assets.new_code.bussiness.message;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.RepairCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.RepairCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.RepairCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.RepairCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.RepairCheckMainInfoResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.WithScrolleViewListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.ToastUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class WeixiuMessageActivity extends Activity {

    public View iv_back;
    public View iv_saoyisiao;
    WithScrolleViewListView lv_zichans;
    ZiChansAdapter madapter;

    EditText et_shenpiyijian;
    TextView tv_querendiaopei;

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
        setContentView(R.layout.activity_weixiumessage);
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
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
        tv_shenqingren = findViewById(R.id.tv_shenqingren);
        tv_shenqingbumen = findViewById(R.id.tv_shenqingbumen);
        tv_shenqingshijian = findViewById(R.id.tv_shenqingshijian);
        tv_zhuangtai = findViewById(R.id.tv_zhuangtai);


        lv_zichans = findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(this);
        lv_zichans.setAdapter(madapter);
        et_shenpiyijian = findViewById(R.id.et_shenpiyijian);
        et_shenpiyijian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tv_querendiaopei.setEnabled(false);
                } else {
                    for (RepairCheckItemListResultBean bean2 : repairCheckPostBean.zcRepairItemList) {
                        if (TextUtils.isEmpty(bean2.qrStatus)) {
                            tv_querendiaopei.setEnabled(false);
                            return;
                        }
                    }
                    tv_querendiaopei.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tv_querendiaopei = findViewById(R.id.tv_querendiaopei);
        tv_querendiaopei.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String neirong = et_shenpiyijian.getText().toString();
                repairCheckPostBean.zcRepairId = bizid;
                repairCheckPostBean.flowTodoId = id;
                repairCheckPostBean.neirong = neirong;
                String token = ACacheUtil.getToken();

                ApiUtils.getApiService().repairCheck(token, repairCheckPostBean).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        if (message.code == 0) {
                            ToastUtil.toast("处理成功");
                            finish();
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean> call, Throwable t) {

                    }
                });
            }
        });
    }

    RepairCheckPostBean repairCheckPostBean;

    TextView tv_shenqingren;
    TextView tv_shenqingbumen;
    TextView tv_shenqingshijian;
    TextView tv_zhuangtai;


    int bizid;
    long id;


    void initData() {
        String token = ACacheUtil.getToken();
        bizid = getIntent().getIntExtra("bizid", -1);
        RepairCheckMainInfoPostBean bean = new RepairCheckMainInfoPostBean();
        bean.zcRepairId = bizid;
        ApiUtils.getApiService().repairCheckMainInfo(token, bean).enqueue(new JuanCallback<BaseBean<RepairCheckMainInfoResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<RepairCheckMainInfoResultBean>> response, BaseBean<RepairCheckMainInfoResultBean> message) {
                if (message.code == 0) {
                    tv_shenqingren.setText(message.data.nickname);
                    tv_shenqingbumen.setText(message.data.deptname);
                    tv_shenqingshijian.setText(message.data.createTime);
                    if (message.data.status == 1) {
                        tv_zhuangtai.setText("审核中");
                    } else {
                        tv_zhuangtai.setText("审核完成");

                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<RepairCheckMainInfoResultBean>> call, Throwable t) {

            }
        });
        RepairCheckItemListPostBean bean1 = new RepairCheckItemListPostBean();
        id = getIntent().getLongExtra("id", -1);
        bean1.flowTodoId = id;
        ApiUtils.getApiService().repairCheckItemList(token, bean1).enqueue(new JuanCallback<BaseBean<ArrayList<RepairCheckItemListResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<RepairCheckItemListResultBean>>> response, BaseBean<ArrayList<RepairCheckItemListResultBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        repairCheckPostBean.zcRepairItemList=message.data;
                        madapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<RepairCheckItemListResultBean>>> call, Throwable t) {

            }
        });


        repairCheckPostBean = new RepairCheckPostBean();

    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<RepairCheckItemListResultBean> mData = new ArrayList<RepairCheckItemListResultBean>();

        public ZiChansAdapter(Context context) {
            this.mcontext = context;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // 声明内部类
            Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_weixiuzichan, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_zcCodenum = convertView.findViewById(R.id.tv_zcCodenum);
                util.tv_zcName = convertView.findViewById(R.id.tv_zcName);
                util.tv_glDeptName = convertView.findViewById(R.id.tv_glDeptName);
                util.tv_zcFrom = convertView.findViewById(R.id.tv_zcFrom);
                util.tv_startUseTime = convertView.findViewById(R.id.tv_startUseTime);
                util.tv_remainingperiod = convertView.findViewById(R.id.tv_remainingperiod);
                util.tv_warrantyperiod = convertView.findViewById(R.id.tv_warrantyperiod);
                util.tv_outUsername = convertView.findViewById(R.id.tv_outUsername);
                util.tv_outPhone = convertView.findViewById(R.id.tv_outPhone);

                util.tv_outAddress = convertView.findViewById(R.id.tv_outAddress);
                util.tv_outCompany = convertView.findViewById(R.id.tv_outCompany);
                util.rg_weixiujielun = convertView.findViewById(R.id.rg_weixiujielun);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            RepairCheckItemListResultBean bean = mData.get(position);
            util.tv_zcCodenum.setText(bean.zcCodenum);
            util.tv_zcName.setText(bean.zcName);
            util.tv_glDeptName.setText(bean.glDeptName);
            util.tv_zcFrom.setText(bean.zcFrom);
            util.tv_startUseTime.setText(bean.startUseTime);
            util.tv_remainingperiod.setText(bean.remainingperiod);
            util.tv_warrantyperiod.setText(bean.warrantyperiod);
            util.tv_outPhone.setText(bean.outPhone);
            util.tv_outUsername.setText(bean.outUsername);
            util.tv_outAddress.setText(bean.outAddress);
            util.tv_outCompany.setText(bean.outCompany);
            util.rg_weixiujielun.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rb_hege) {
                        bean.qrStatus = "1";
                    } else {
                        bean.qrStatus = "0";
                    }


                    for (RepairCheckItemListResultBean bean2 : mData) {
                        if (TextUtils.isEmpty(bean2.qrStatus)) {
                            tv_querendiaopei.setEnabled(false);
                            return;
                        }
                    }
                    if (!TextUtils.isEmpty(et_shenpiyijian.getText().toString())) {
                        tv_querendiaopei.setEnabled(true);
                    }

                }
            });


            return convertView;
        }


        class Util {
            public View ll_all;
            TextView tv_zcCodenum;
            TextView tv_zcName;
            TextView tv_glDeptName;
            TextView tv_zcFrom;
            TextView tv_startUseTime;
            TextView tv_remainingperiod;
            TextView tv_warrantyperiod;
            TextView tv_outUsername;
            TextView tv_outPhone;
            TextView tv_outAddress;
            TextView tv_outCompany;

            RadioGroup rg_weixiujielun;

        }
    }
}