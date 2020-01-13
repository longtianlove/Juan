package com.ja.assets.new_code.bussiness.diaopei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.Patrol.Jilu_PatrolCheckDetailActivity;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeixiangqingPostBean;
import com.ja.assets.new_code.bussiness.bean.result.DiaopeijiluxiangqingBean;
import com.ja.assets.new_code.bussiness.bean.result.ZiChansBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.WithScrolleViewListView;
import com.ja.assets.utils.ACacheUtil;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DiaopeijiluxiangqingActivity extends Activity {

    public View iv_back;
    public View iv_saoyisiao;
    WithScrolleViewListView lv_zichans;
    ZiChansAdapter madapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaopeijiluxiangqing);
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
        int id=getIntent().getIntExtra("id",-1);
        DiaopeixiangqingPostBean bean=new DiaopeixiangqingPostBean();
        bean.zcDeployId=id;
        ApiUtils.getApiService().listByZcDeployId(token,bean).enqueue(new JuanCallback<BaseBean<ArrayList<DiaopeijiluxiangqingBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<DiaopeijiluxiangqingBean>>> response, BaseBean<ArrayList<DiaopeijiluxiangqingBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<DiaopeijiluxiangqingBean>>> call, Throwable t) {

            }
        });
    }




    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<DiaopeijiluxiangqingBean> mData = new ArrayList<DiaopeijiluxiangqingBean>();

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
                convertView = inflater.inflate(R.layout.item_diaopeixiangqing, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_xunjianjieguo = convertView.findViewById(R.id.tv_xunjianjieguo);
                util.tv_xunjianshijian = convertView.findViewById(R.id.tv_xunjianshijian);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            DiaopeijiluxiangqingBean bean = mData.get(position);

            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.sydeptname);
            util.tv_guanlibumen.setText(bean.gldeptname);
            util.tv_cunfangdizhi.setText(bean.storeaddress);


            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_epcid;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_xunjianjieguo;
            public TextView tv_xunjianshijian;
            public TextView tv_cunfangdizhi;
        }
    }
}