package com.ja.assets.new_code.bussiness.message;

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
import com.ja.assets.new_code.bussiness.bean.post.ChuzhiLiebiaoPostbean;
import com.ja.assets.new_code.bussiness.bean.result.ChuzhiliiebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.DaibanMessageResultBean;
import com.ja.assets.new_code.bussiness.chuzhi.Chuzhi_ZichanliebiaoActivity;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MessageActivity extends Activity {

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

        setContentView(R.layout.activity_message);
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

        ApiUtils.getApiService().getAllWailDealList(token).enqueue(new JuanCallback<BaseBean<ArrayList<DaibanMessageResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<DaibanMessageResultBean>>> response, BaseBean<ArrayList<DaibanMessageResultBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<DaibanMessageResultBean>>> call, Throwable t) {

            }
        });
    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<DaibanMessageResultBean> mData = new ArrayList<DaibanMessageResultBean>();

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
                convertView = inflater.inflate(R.layout.item_message, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_chuanjianren = convertView.findViewById(R.id.tv_chuanjianren);
                util.tv_chuanjianrenbumen = convertView.findViewById(R.id.tv_chuanjianrenbumen);
                util.tv_shenheren = convertView.findViewById(R.id.tv_shenheren);
                util.tv_fasongren = convertView.findViewById(R.id.tv_fasongren);
                util.tv_biaoti = convertView.findViewById(R.id.tv_biaoti);
                util.tv_shenheneirong = convertView.findViewById(R.id.tv_shenheneirong);
                util.tv_chuanjianshijian = convertView.findViewById(R.id.tv_chuanjianshijian);
                util.tv_gengxinshijian = convertView.findViewById(R.id.tv_gengxinshijian);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            DaibanMessageResultBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(MessageActivity.this, Chuzhi_ZichanliebiaoActivity.class);
//                    intent.putExtra("id", bean.id);
//                    startActivity(intent);
//                    zcbuy/auditZcBuy.html   资产购买
//                    zcrepair/auditZcRepair.html  维修审核
//                    zcrepair/confirmZcRepair.html  维修确认
//                    zcdeploy/auditZcDeploy.html  资产调配
//                    zcbf/auditZcBf.html   处置审核
//                    zcbf/cwauditZcBf.html  处置财务确认


                    if ("zcdeploy/auditZcDeploy.html".equals(bean.url)) {
                        Intent intent = new Intent(MessageActivity.this, DiaopeiMessageActivity.class);
                        intent.putExtra("bizid",bean.bizid);
                        intent.putExtra("id",bean.id);
                        startActivity(intent);
                    }
                    if("zcbuy/auditZcBuy.html".equals(bean.url)){
                        Intent intent = new Intent(MessageActivity.this, GouMaiMessageActivity.class);
                        intent.putExtra("bizid",bean.bizid);
                        intent.putExtra("id",bean.id);
                        startActivity(intent);
                    }


                }
            });
            util.tv_chuanjianren.setText(bean.bizcreateby + "");
            util.tv_chuanjianrenbumen.setText(bean.bizdeptid + "");
            util.tv_shenheren.setText(bean.auditby + "");
            util.tv_fasongren.setText(bean.sendby + "");
            util.tv_biaoti.setText(bean.biaoti);
            util.tv_shenheneirong.setText(bean.neirong);
            util.tv_chuanjianshijian.setText(bean.createTime);
            util.tv_gengxinshijian.setText(bean.updateTime);

            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_chuanjianren;
            public TextView tv_chuanjianrenbumen;
            public TextView tv_shenheren;
            public TextView tv_fasongren;
            public TextView tv_biaoti;
            public TextView tv_shenheneirong;
            public TextView tv_chuanjianshijian;
            public TextView tv_gengxinshijian;

        }
    }
}