package com.ja.assets.new_code.bussiness.Patrol;

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

public class Jilu_Code_PatrolCheckListActivity extends Activity {

    public View iv_back;
    public View iv_saoyisiao;
    WithScrolleViewListView lv_zichans;
    ZiChansAdapter madapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jilu_activity_patrol_check_list);
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
        ApiUtils.getApiService().JiluPatrolCheckList(token).enqueue(new JuanCallback<BaseBean<ArrayList<ZiChansBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<ZiChansBean>>> response, BaseBean<ArrayList<ZiChansBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<ZiChansBean>>> call, Throwable t) {

            }
        });
    }

    private int requestBackCode = 100;

    void toSaomao() {
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, requestBackCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == requestBackCode && resultCode == RESULT_OK) {
            String resultUrl = data.getStringExtra(Constant.CODED_CONTENT);

        }
    }

    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<ZiChansBean> mData = new ArrayList<ZiChansBean>();

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
                convertView = inflater.inflate(R.layout.jilu_item_inventory_record, null);
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
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Jilu_Code_PatrolCheckListActivity.this, Jilu_PatrolCheckDetailActivity.class);
                    startActivity(intent);
                }
            });

            ZiChansBean bean = mData.get(position);
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.glDeptName);
            if (bean.result == 0) {
                util.tv_xunjianjieguo.setText("达标");
            } else {
                util.tv_xunjianjieguo.setText("不达标");
            }
            util.tv_xunjianshijian.setText(bean.createTime);
            util.tv_cunfangdizhi.setText(bean.storeAddress);


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