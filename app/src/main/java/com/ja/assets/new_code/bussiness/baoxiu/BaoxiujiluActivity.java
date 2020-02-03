package com.ja.assets.new_code.bussiness.baoxiu;

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
import com.ja.assets.new_code.bussiness.Patrol.Jilu_PatrolCheckDetailActivity;
import com.ja.assets.new_code.bussiness.bean.post.BaoxiujiluPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuijilulistBean;
import com.ja.assets.new_code.bussiness.bean.result.ZiChansBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.WithScrolleViewListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class BaoxiujiluActivity extends Activity {

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

        setContentView(R.layout.activity_baoxiujiilu);
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
        BaoxiujiluPostBean bean = new BaoxiujiluPostBean();
        bean.limit = 1000;
        bean.offset = 1;
        ApiUtils.getApiService().repairRecordList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<BaoxiuijilulistBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<BaoxiuijilulistBean>>> response, BaseBean<ArrayList<BaoxiuijilulistBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<BaoxiuijilulistBean>>> call, Throwable t) {

            }
        });
    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<BaoxiuijilulistBean> mData = new ArrayList<BaoxiuijilulistBean>();

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
                convertView = inflater.inflate(R.layout.item_baoxiujilu, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_weixiudanhao = convertView.findViewById(R.id.tv_weixiudanhao);
                util.tv_weixiuzhuangtai = convertView.findViewById(R.id.tv_weixiuzhuangtai);
                util.tv_shenqinngyonghu = convertView.findViewById(R.id.tv_shenqinngyonghu);
                util.tv_shenqingbumen = convertView.findViewById(R.id.tv_shenqingbumen);
                util.tv_chuangjianshijian = convertView.findViewById(R.id.tv_chuangjianshijian);

                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            BaoxiuijilulistBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BaoxiujiluActivity.this, BaoxiujiluDetailActivity.class);
                    intent.putExtra("id", bean.id);
                    startActivity(intent);
                }
            });


            util.tv_weixiudanhao.setText(bean.code);
            if (bean.status == 0) {
                util.tv_weixiuzhuangtai.setText("待提交");
            } else if (bean.status == 1) {
                util.tv_weixiuzhuangtai.setText("审核中");
            } else if (bean.status == 2) {
                util.tv_weixiuzhuangtai.setText("已审核");

            }
            util.tv_shenqinngyonghu.setText(bean.nickname);
            util.tv_shenqingbumen.setText(bean.deptname);
            util.tv_chuangjianshijian.setText(bean.createTime);


            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_weixiudanhao;
            public TextView tv_weixiuzhuangtai;
            public TextView tv_shenqinngyonghu;
            public TextView tv_shenqingbumen;
            public TextView tv_chuangjianshijian;


        }
    }
}