package com.ja.assets.new_code.bussiness.baoxiu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;
import com.ja.assets.new_code.Constants;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.JiluXunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuDetailBean;
import com.ja.assets.new_code.bussiness.bean.result.BaoxiuijilulistBean;
import com.ja.assets.new_code.bussiness.message.GouMaiMessageActivity;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;
import com.liji.imagezoom.util.ImageZoom;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class BaoxiujiluDetailActivity2 extends Activity {


    View iv_back;
    ListView lv_zichans;
    public static ZiChansAdapter madapter;


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
        setContentView(R.layout.activity_baoxiujilu_detail2);
        initView();
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
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String token = ACacheUtil.getToken();
        JiluXunjianPostBean bean = new JiluXunjianPostBean();
        bean.id = id;
        ApiUtils.getApiService().listByZcReId(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<BaoxiuDetailBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<BaoxiuDetailBean>>> response, BaseBean<ArrayList<BaoxiuDetailBean>> message) {
                if (message.code == 0) {

                    madapter.mData = message.data;
                    madapter.notifyDataSetChanged();


                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<BaoxiuDetailBean>>> call, Throwable t) {

            }
        });

    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        public List<BaoxiuDetailBean> mData = new ArrayList<BaoxiuDetailBean>();

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
                convertView = inflater.inflate(R.layout.item_weixiudanxiangqing, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_zcCodenum = convertView.findViewById(R.id.tv_zcCodenum);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.tv_zcName = convertView.findViewById(R.id.tv_zcName);
                util.tv_baoxiukaishishijian = convertView.findViewById(R.id.tv_baoxiukaishishijian);
                util.tv_fujian = convertView.findViewById(R.id.tv_fujian);
                util.tv_weixiuxinxidianjichakan = convertView.findViewById(R.id.tv_weixiuxinxidianjichakan);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            BaoxiuDetailBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                }
            });
            util.tv_zcCodenum.setText(bean.zcCodenum);
            util.tv_epcid.setText(bean.epcid);
            util.tv_zcName.setText(bean.zcName);
            util.tv_baoxiukaishishijian.setText(bean.createTime);
            util.tv_fujian.setText(bean.imgUrl);
            util.tv_fujian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url= Constants.Url.FILE_HOST+bean.imgUrl;
                    ArrayList<String> urls=new ArrayList<>();
                    urls.add(url);
                    ImageZoom.show(BaoxiujiluDetailActivity2.this, url,urls);

                }
            });
            util.tv_weixiuxinxidianjichakan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BaoxiujiluDetailActivity2.this, BaoxiujiluWeixiuxinxiDetailActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });


            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_zcCodenum;
            public TextView tv_epcid;
            public TextView tv_zcName;
            public TextView tv_baoxiukaishishijian;
            public TextView tv_fujian;
            public TextView tv_weixiuxinxidianjichakan;


        }
    }


}