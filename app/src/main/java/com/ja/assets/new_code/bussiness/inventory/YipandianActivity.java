package com.ja.assets.new_code.bussiness.inventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuangjianpandiandanBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.WeiPandianResultBean;
import com.ja.assets.new_code.bussiness.bean.result.YipandianResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.DialogUtil;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.utils.ACacheUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class YipandianActivity extends Activity {


    View iv_back;
    MaterialDesignPtrFrameLayout ptr_refresh;
    JuanListView lv_weipandian;
    DoctorAdapter madapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yipandian);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ptr_refresh = findViewById(R.id.ptr_refresh);
        /**
         * 下拉刷新
         */
        ptr_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                PAGE_NO = 1;
                getDoctors();

            }
        });
        lv_weipandian = findViewById(R.id.lv_weipandian);
        madapter = new DoctorAdapter(this);
        lv_weipandian.setAdapter(madapter);
        lv_weipandian.setOnUpLoadListener(new JuanListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                getDoctors();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    void initData() {
        PAGE_NO = 1;
        getDoctors();
    }

    int PAGE_NO = 1;
    int PAGE_SIZE = 10;

    void getDoctors() {
        WeiPandianPostBean bean = new WeiPandianPostBean();
        bean.limit = 10;
        bean.offset = PAGE_NO;
        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().checkRecordList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<YipandianResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<YipandianResultBean>>> response, BaseBean<ArrayList<YipandianResultBean>> message) {
                ptr_refresh.refreshComplete();
                if (message.code == 0) {
                    if (message.data != null && message.data.size() >= 0) {
                        lv_weipandian.setLoading(false);
                        if (PAGE_NO == 1) {
                            madapter.mData.clear();
                        }
                        //有消息
                        PAGE_NO++;
                        madapter.mData.addAll(message.data);
                        if (message.data.size() < 10) {
                            lv_weipandian.setHasLoadMore(false);
                            lv_weipandian.setLoadAllViewText("暂时只有这么多单子");
                            lv_weipandian.setLoadAllFooterVisible(true);
                        } else {
                            lv_weipandian.setHasLoadMore(true);
                        }
                        madapter.notifyDataSetChanged();
                    } else {
                        //没有消息
                        lv_weipandian.setHasLoadMore(false);
                        lv_weipandian.setLoadAllViewText("暂时只有这么多单子");
                        lv_weipandian.setLoadAllFooterVisible(true);
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<YipandianResultBean>>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
    }


    class DoctorAdapter extends BaseAdapter {

        public Context mcontext;

        List<YipandianResultBean> mData = new ArrayList<YipandianResultBean>();

        public DoctorAdapter(Context context) {
            this.mcontext = context;
        }

        @Override
        public int getCount() {
            if (mData == null) {
                return 0;
            } else {
                return mData.size();
            }
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
                convertView = inflater.inflate(R.layout.newcode_item_fragment_inventory, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_danhao = convertView.findViewById(R.id.tv_danhao);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_zichanzongshu = convertView.findViewById(R.id.tv_zichanzongshu);
                util.tv_pandianshijian = convertView.findViewById(R.id.tv_pandianshijian);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            YipandianResultBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(YipandianActivity.this, ZichanbaobiaoActivity.class);
                    intent.putExtra("id", bean.id);
                    startActivity(intent);
                }
            });
            util.tv_danhao.setText(bean.check_num);
            util.tv_shiyongbumen.setText(bean.checkDeptName);
            util.tv_guanlibumen.setText(bean.checkUserName);
            util.tv_zichanzongshu.setText(bean.total + "");
            util.tv_pandianshijian.setText(bean.checkTime);
            return convertView;
        }


        class Util {
            View ll_all;
            TextView tv_danhao;
            TextView tv_shiyongbumen;
            TextView tv_guanlibumen;
            TextView tv_zichanzongshu;
            TextView tv_pandianshijian;

        }
    }
}
