package com.ja.assets.new_code.bussiness.caigou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.CaigouiLiebiaoPostbean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhiLiebiaoPostbean;
import com.ja.assets.new_code.bussiness.bean.result.CaigoulistResultBean;
import com.ja.assets.new_code.bussiness.bean.result.ChuzhiliiebiaoBean;
import com.ja.assets.new_code.bussiness.chuzhi.Chuzhi_ZichanliebiaoActivity;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CaigoujiluActivity extends Activity {

    public View iv_back;
    public View iv_saoyisiao;
    ListView lv_zichans;
    ZiChansAdapter madapter;

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

        setContentView(R.layout.activity_caigoujiilu);
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
        lv_zichans = findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(this);
        lv_zichans.setAdapter(madapter);
    }

    void initData() {
        String token = ACacheUtil.getToken();
        CaigouiLiebiaoPostbean bean = new CaigouiLiebiaoPostbean();
        bean.limit = 1000;
        bean.offset = 1;
        ApiUtils.getApiService().buyRecordList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<CaigoulistResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<CaigoulistResultBean>>> response, BaseBean<ArrayList<CaigoulistResultBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<CaigoulistResultBean>>> call, Throwable t) {

            }
        });
    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<CaigoulistResultBean> mData = new ArrayList<CaigoulistResultBean>();

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
                convertView = inflater.inflate(R.layout.item_caigoujilu, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_nickname = convertView.findViewById(R.id.tv_nickname);
                util.tv_companyName = convertView.findViewById(R.id.tv_companyName);
                util.tv_buyNum = convertView.findViewById(R.id.tv_buyNum);
                util.tv_createTime = convertView.findViewById(R.id.tv_createTime);
                util.tv_stepname = convertView.findViewById(R.id.tv_stepname);
                util.tv_flowname = convertView.findViewById(R.id.tv_flowname);
                util.tv_fileName = convertView.findViewById(R.id.tv_fileName);
                util.tv_status = convertView.findViewById(R.id.tv_status);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            CaigoulistResultBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CaigoujiluActivity.this, CaigoujiluXiangqingActivity.class);
                    intent.putExtra("id", bean.id);
                    startActivity(intent);
                }
            });
            util.tv_nickname.setText(bean.nickname);
            util.tv_companyName.setText(bean.companyName);
            util.tv_buyNum.setText(bean.buyNum);
            util.tv_createTime.setText(bean.createTime);
            util.tv_stepname.setText(bean.stepname);
            util.tv_flowname.setText(bean.flowname);
            util.tv_fileName.setText(bean.fileName);
            if (1 == bean.status) {
                util.tv_status.setText("进行中");
            } else {
                util.tv_status.setText("已完成");
            }

            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_nickname;
            public TextView tv_companyName;
            public TextView tv_buyNum;
            public TextView tv_createTime;
            public TextView tv_stepname;
            public TextView tv_flowname;
            public TextView tv_fileName;
            public TextView tv_status;


        }
    }
}