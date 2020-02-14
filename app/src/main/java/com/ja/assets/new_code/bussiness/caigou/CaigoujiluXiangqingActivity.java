package com.ja.assets.new_code.bussiness.caigou;

import android.app.Activity;
import android.content.Context;
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
import com.ja.assets.new_code.bussiness.bean.post.Caigouitemzichan;
import com.ja.assets.new_code.bussiness.bean.post.CaigouxiangqingPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhiLiebiaoPostbean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CaigoujiluXiangqingActivity extends Activity {

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

        setContentView(R.layout.activity_caigoujiiluxiangqing);
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
        int id = getIntent().getIntExtra("id", -1);
        String token = ACacheUtil.getToken();
        CaigouxiangqingPostBean bean = new CaigouxiangqingPostBean();
        bean.cw = "";
        bean.zcBuyId = id;
        ApiUtils.getApiService().getBuyRecordItemDetailList(token,bean).enqueue(new JuanCallback<BaseBean<ArrayList<Caigouitemzichan>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<Caigouitemzichan>>> response, BaseBean<ArrayList<Caigouitemzichan>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<Caigouitemzichan>>> call, Throwable t) {

            }
        });

//        ApiUtils.getApiService().getBFRecordList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<ChuzhiliiebiaoBean>>>() {
//            @Override
//            public void onSuccess(Response<BaseBean<ArrayList<ChuzhiliiebiaoBean>>> response, BaseBean<ArrayList<ChuzhiliiebiaoBean>> message) {
//                if (message.code == 0) {
//                    if (message.data.size() >= 0) {
//                        madapter.mData = message.data;
//                        madapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFail(Call<BaseBean<ArrayList<ChuzhiliiebiaoBean>>> call, Throwable t) {
//
//            }
//        });
    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<Caigouitemzichan> mData = new ArrayList<Caigouitemzichan>();

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
                convertView = inflater.inflate(R.layout.item_caigouzichan_xuanzehou, null);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_caigoushuliang = convertView.findViewById(R.id.tv_caigoushuliang);
                util.tv_guigexinghao = convertView.findViewById(R.id.tv_guigexinghao);
                util.tv_chanpinpinpai = convertView.findViewById(R.id.tv_chanpinpinpai);
                util.tv_gongyingshangmingcheng = convertView.findViewById(R.id.tv_gongyingshangmingcheng);
                util.tv_jiliangdanwei = convertView.findViewById(R.id.tv_jiliangdanwei);
                util.tv_caigoudanjia = convertView.findViewById(R.id.tv_caigoudanjia);


                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            Caigouitemzichan bean = mData.get(position);
            util.tv_zichanmingcheng.setText(bean.name);
            util.tv_guanlibumen.setText(bean.glDeptName);
            util.tv_caigoushuliang.setText(bean.num + "");
            util.tv_guigexinghao.setText(bean.model);
            util.tv_chanpinpinpai.setText(bean.brand);
            util.tv_gongyingshangmingcheng.setText(bean.supplierName);
            util.tv_jiliangdanwei.setText(bean.unit);
            util.tv_caigoudanjia.setText(bean.price + "");
            return convertView;
        }


        class Util {
            TextView tv_zichanmingcheng;
            TextView tv_guanlibumen;
            TextView tv_caigoushuliang;
            TextView tv_guigexinghao;
            TextView tv_chanpinpinpai;
            TextView tv_gongyingshangmingcheng;
            TextView tv_jiliangdanwei;
            TextView tv_caigoudanjia;

        }
    }
}