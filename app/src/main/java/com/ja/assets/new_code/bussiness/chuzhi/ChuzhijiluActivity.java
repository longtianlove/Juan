package com.ja.assets.new_code.bussiness.chuzhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.baoxiu.BaoxiujiluDetailActivity;
import com.ja.assets.new_code.bussiness.bean.post.BaoxiujiluPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhiLiebiaoPostbean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuijilulistBean;
import com.ja.assets.new_code.bussiness.bean.result.ChuzhiliiebiaoBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.WithScrolleViewListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ChuzhijiluActivity extends Activity {

    public View iv_back;
    public View iv_saoyisiao;
    WithScrolleViewListView lv_zichans;
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

        setContentView(R.layout.activity_chuzhijiilu);
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
        ChuzhiLiebiaoPostbean bean = new ChuzhiLiebiaoPostbean();
        bean.limit = 1000;
        bean.offset = 1;
        ApiUtils.getApiService().getBFRecordList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<ChuzhiliiebiaoBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<ChuzhiliiebiaoBean>>> response, BaseBean<ArrayList<ChuzhiliiebiaoBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<ChuzhiliiebiaoBean>>> call, Throwable t) {

            }
        });
    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<ChuzhiliiebiaoBean> mData = new ArrayList<ChuzhiliiebiaoBean>();

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
                convertView = inflater.inflate(R.layout.item_chuzhijilu, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_shenqingren = convertView.findViewById(R.id.tv_shenqingren);
                util.tv_zhanghao = convertView.findViewById(R.id.tv_zhanghao);
                util.tv_shenqingbumen = convertView.findViewById(R.id.tv_shenqingbumen);
                util.tv_liuchenghuanjie = convertView.findViewById(R.id.tv_liuchenghuanjie);
                util.tv_yewuleixing = convertView.findViewById(R.id.tv_yewuleixing);
                util.tv_chuangjianshijian = convertView.findViewById(R.id.tv_chuangjianshijian);

                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            ChuzhiliiebiaoBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ChuzhijiluActivity.this, Chuzhi_ZichanliebiaoActivity.class);
                    intent.putExtra("id", bean.id);
                    startActivity(intent);
                }
            });
            util.tv_shenqingren.setText(bean.nickname);
            util.tv_zhanghao.setText(bean.username);
            util.tv_shenqingbumen.setText(bean.deptname);
            util.tv_liuchenghuanjie.setText(bean.stepname);
            util.tv_yewuleixing.setText(bean.flowname);
            util.tv_chuangjianshijian.setText(bean.createtime);

            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_shenqingren;
            public TextView tv_zhanghao;
            public TextView tv_shenqingbumen;
            public TextView tv_liuchenghuanjie;
            public TextView tv_yewuleixing;
            public TextView tv_chuangjianshijian;

        }
    }
}