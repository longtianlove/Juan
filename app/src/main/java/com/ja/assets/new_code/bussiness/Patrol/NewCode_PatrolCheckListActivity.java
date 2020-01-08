package com.ja.assets.new_code.bussiness.Patrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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

public class NewCode_PatrolCheckListActivity extends Activity {


    WithScrolleViewListView lv_zichans;
    ZiChansAdapter madapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcode_activity_patrol_check_list);
        initView();
        initData();
    }

    void initView() {
        lv_zichans = findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(this);
        lv_zichans.setAdapter(madapter);
    }

    void initData() {
        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().deviceBinding(token).enqueue(new JuanCallback<BaseBean<ArrayList<ZiChansBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<ZiChansBean>>> response, BaseBean<ArrayList<ZiChansBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData=message.data;
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
                convertView = inflater.inflate(R.layout.new_item_inventory_record, null);


                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }


            return convertView;
        }


        class Util {


        }
    }
}