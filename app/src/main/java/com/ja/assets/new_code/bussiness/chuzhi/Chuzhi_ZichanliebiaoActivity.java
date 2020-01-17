package com.ja.assets.new_code.bussiness.chuzhi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhizichanPostbean;
import com.ja.assets.new_code.bussiness.bean.post.PandianZichanWanchengPostBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandiianzichanPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ZichanSaomaPostBean;
import com.ja.assets.new_code.bussiness.bean.result.Chuzhi_zichanliiebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.Pandian_zichanliebiaoBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.utils.ACacheUtil;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class Chuzhi_ZichanliebiaoActivity extends Activity {

    public View iv_back;
    View tv_create;
    MaterialDesignPtrFrameLayout ptr_refresh;
    JuanListView lv_zichans;
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
        setContentView(R.layout.activity_chuzhi_zichanliebiao);
        initView();
        id = getIntent().getIntExtra("id", -1);

    }

    public int id;
    int PAGE_NO = 1;

    @Override
    protected void onStart() {
        super.onStart();
        PAGE_NO = 1;
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



        ptr_refresh = findViewById(R.id.ptr_refresh);
        /**
         * 下拉刷新
         */
        ptr_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                PAGE_NO = 1;
                initData();

            }
        });

        lv_zichans = findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(this);
        lv_zichans.setAdapter(madapter);
        lv_zichans.setOnUpLoadListener(new JuanListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                initData();
            }
        });
    }

    void initData() {
        ChuzhizichanPostbean bean = new ChuzhizichanPostbean();
        bean.zcBfId = id;
        bean.limit = 10;
        bean.offset = PAGE_NO;
        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().getBFRecordItemList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<Chuzhi_zichanliiebiaoBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<Chuzhi_zichanliiebiaoBean>>> response, BaseBean<ArrayList<Chuzhi_zichanliiebiaoBean>> message) {
                ptr_refresh.refreshComplete();
                if (message.code == 0) {
                    if (message.data != null && message.data.size() >= 0) {
                        lv_zichans.setLoading(false);
                        if (PAGE_NO == 1) {
                            madapter.mData.clear();
                        }
                        //有消息
                        PAGE_NO++;
                        madapter.mData.addAll(message.data);
                        if (message.data.size() < 10) {
                            lv_zichans.setHasLoadMore(false);
                            lv_zichans.setLoadAllViewText("暂时只有这么多资产");
                            lv_zichans.setLoadAllFooterVisible(true);
                        } else {
                            lv_zichans.setHasLoadMore(true);
                        }
                        madapter.notifyDataSetChanged();
                    } else {
                        //没有消息
                        lv_zichans.setHasLoadMore(false);
                        lv_zichans.setLoadAllViewText("暂时只有这么多资产");
                        lv_zichans.setLoadAllFooterVisible(true);
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<Chuzhi_zichanliiebiaoBean>>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<Chuzhi_zichanliiebiaoBean> mData = new ArrayList<Chuzhi_zichanliiebiaoBean>();

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
                convertView = inflater.inflate(R.layout.item_chuzhi_zichanliebiao, null);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            Chuzhi_zichanliiebiaoBean bean = mData.get(position);
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.sydeptname);
            util.tv_guanlibumen.setText(bean.gldeptname);
            util.tv_cunfangdizhi.setText(bean.storeaddress);


            return convertView;
        }


        class Util {
            public TextView tv_epcid;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_cunfangdizhi;

        }
    }
}