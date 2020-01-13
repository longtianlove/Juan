package com.ja.assets.new_code.bussiness.diaopei;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.result.BumenListBean;
import com.ja.assets.new_code.bussiness.bean.result.Diaopei_zichanliebiaoBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.utils.ACacheUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class DiaopeiBumenActivity extends Activity {


    ListView lv_bumens;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaorubumen);
        lv_bumens = findViewById(R.id.lv_bumens);
        initData();
    }

    public int nowPosition;

    void initData() {
        nowPosition = getIntent().getIntExtra("position", -1);
        BumensAdapter madapter = new BumensAdapter(this);
        lv_bumens.setAdapter(madapter);

        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().diaopei_bumenlist(token).enqueue(new JuanCallback<BaseBean<ArrayList<BumenListBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<BumenListBean>>> response, BaseBean<ArrayList<BumenListBean>> message) {
                if (message.code == 0) {
                    madapter.mData = message.data;
                    madapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<BumenListBean>>> call, Throwable t) {

            }
        });
    }


    class BumensAdapter extends BaseAdapter {

        public Context mcontext;

        List<BumenListBean> mData = new ArrayList<BumenListBean>();

        public BumensAdapter(Context context) {
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
                convertView = inflater.inflate(R.layout.item_bumen, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_diaorubumen = convertView.findViewById(R.id.tv_diaorubumen);

                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            BumenListBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.get(nowPosition).backDeptId = bean.id;
                    DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.get(nowPosition).backUsername = bean.deptname;
                    finish();
                }
            });
            util.tv_diaorubumen.setText(bean.deptname);

            return convertView;
        }


        class Util {
            View ll_all;
            TextView tv_diaorubumen;

        }
    }
}
