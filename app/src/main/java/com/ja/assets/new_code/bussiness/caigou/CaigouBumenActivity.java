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

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.result.BumenListBean;
import com.ja.assets.new_code.bussiness.bean.result.GuanliBumenListBean;
import com.ja.assets.new_code.bussiness.diaopei.DiaopeizichanliebiaoActivity;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CaigouBumenActivity extends Activity {


    ListView lv_bumens;

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

        setContentView(R.layout.activity_caigoubumen);
        lv_bumens = findViewById(R.id.lv_bumens);
        initData();
    }


    void initData() {

        BumensAdapter madapter = new BumensAdapter(this);
        lv_bumens.setAdapter(madapter);

        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().caigou_guanlibumen(token).enqueue(new JuanCallback<BaseBean<ArrayList<GuanliBumenListBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<GuanliBumenListBean>>> response, BaseBean<ArrayList<GuanliBumenListBean>> message) {
                if (message.code == 0) {
                    madapter.mData = message.data;
                    madapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<GuanliBumenListBean>>> call, Throwable t) {

            }
        });
    }


    class BumensAdapter extends BaseAdapter {

        public Context mcontext;

        List<GuanliBumenListBean> mData = new ArrayList<GuanliBumenListBean>();

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
            GuanliBumenListBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.get(nowPosition).backDeptId = bean.id;
//                    DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.get(nowPosition).backUsername = bean.deptname;
                    Intent intent = new Intent();
                    intent.putExtra("name", bean.name);
                    intent.putExtra("glDeptId",bean.id);
                    setResult(2,intent);
                    finish();
                }
            });
            util.tv_diaorubumen.setText(bean.name);

            return convertView;
        }


        class Util {
            View ll_all;
            TextView tv_diaorubumen;

        }
    }
}
