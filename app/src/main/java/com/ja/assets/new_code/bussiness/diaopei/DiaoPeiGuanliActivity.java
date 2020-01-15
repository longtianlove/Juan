package com.ja.assets.new_code.bussiness.diaopei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuangjianpandiandanBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.Diaopei_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.WeiPandianResultBean;
import com.ja.assets.new_code.bussiness.inventory.NewCode_ZichanliebiaoActivity;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.DialogUtil;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.utils.ACacheUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class DiaoPeiGuanliActivity extends Activity {

    View iv_back;
    View tv_tianjiazichan;
    TextView tv_tijiao;

    View noAssetsLinear;
    View scl_bag;
    ListView lv_zichans;

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
        setContentView(R.layout.activity_diaopeiguanli);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_tianjiazichan = findViewById(R.id.tv_tianjiazichan);
        tv_tianjiazichan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaoPeiGuanliActivity.this, DiaopeizichanliebiaoActivity.class);
                startActivity(intent);
            }
        });
        noAssetsLinear = findViewById(R.id.noAssetsLinear);
        scl_bag = findViewById(R.id.scl_bag);
        lv_zichans = findViewById(R.id.lv_zichans);
        tv_tijiao = findViewById(R.id.tv_tijiao);
        tv_tijiao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String token = ACacheUtil.getToken();
                ApiUtils.getApiService().insertZcDeployData(token, DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        if (message.code == 0) {
                            DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.clear();
                            noAssetsLinear.setVisibility(View.VISIBLE);
                            scl_bag.setVisibility(View.GONE);
                            lv_zichans.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean> call, Throwable t) {

                    }
                });
            }
        });
        DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.clear();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.size() == 0) {
            noAssetsLinear.setVisibility(View.VISIBLE);
            scl_bag.setVisibility(View.GONE);
            lv_zichans.setVisibility(View.GONE);
        } else {
            noAssetsLinear.setVisibility(View.GONE);
            scl_bag.setVisibility(View.VISIBLE);
            ZiChansAdapter mapapter = new ZiChansAdapter(this);
            mapapter.mData = DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao;
            lv_zichans.setVisibility(View.VISIBLE);
            lv_zichans.setAdapter(mapapter);
        }
        tijiaoganniu();
    }

    void tijiaoganniu() {
        for (Diaopei_zichanliebiaoBean bean : DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao) {
            if (TextUtils.isEmpty(bean.backUsername)) {
                tv_tijiao.setEnabled(false);
                return;
            }
        }
        tv_tijiao.setEnabled(true);
    }

    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<Diaopei_zichanliebiaoBean> mData = new ArrayList<Diaopei_zichanliebiaoBean>();

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
            ZiChansAdapter.Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new ZiChansAdapter.Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_diaopeizichanliebiao_xuanzehou, null);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
//                util.iv_erweima = convertView.findViewById(R.id.iv_erweima);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
                util.tv_diaochubumen = convertView.findViewById(R.id.tv_diaochubumen);
                util.tv_diaochubumen_dianji = convertView.findViewById(R.id.tv_diaochubumen_dianji);
                convertView.setTag(util);
            } else {
                util = (ZiChansAdapter.Util) convertView.getTag();
            }
            Diaopei_zichanliebiaoBean bean = mData.get(position);

            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.glDeptName);
            util.tv_cunfangdizhi.setText(bean.storeAddress);
            if (TextUtils.isEmpty(bean.backUsername)) {
                util.tv_diaochubumen.setVisibility(View.GONE);
                util.tv_diaochubumen_dianji.setVisibility(View.VISIBLE);
                util.tv_diaochubumen_dianji.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DiaoPeiGuanliActivity.this, DiaopeiBumenActivity.class);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }
                });
            } else {
                util.tv_diaochubumen.setVisibility(View.VISIBLE);
                util.tv_diaochubumen.setText(bean.backUsername);
                util.tv_diaochubumen_dianji.setVisibility(View.GONE);
            }


            return convertView;
        }


        class Util {
            public TextView tv_epcid;
            //            public View iv_erweima;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_cunfangdizhi;

            public TextView tv_diaochubumen;
            public View tv_diaochubumen_dianji;

        }
    }
}
