package com.ja.assets.new_code.bussiness.baoxiu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.result.Biaoxiiu_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.Diaopei_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.diaopei.DiaopeiBumenActivity;

import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.utils.ACacheUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class BaoxiuGuanliActivity extends Activity {

    View iv_back;
    View tv_tianjiazichan;
    TextView tv_tijiao;

    View noAssetsLinear;
    View scl_bag;
    ListView lv_zichans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baoxiuguanli);
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
                Intent intent = new Intent(BaoxiuGuanliActivity.this, BaoxiuzichanliebiaoActivity.class);
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
                ApiUtils.getApiService().insertRepairData(token, BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        if (message.code == 0) {
                            BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao.clear();
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

        BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao.clear();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao.size() == 0) {
            noAssetsLinear.setVisibility(View.VISIBLE);
            scl_bag.setVisibility(View.GONE);
            lv_zichans.setVisibility(View.GONE);
        } else {
            noAssetsLinear.setVisibility(View.GONE);
            scl_bag.setVisibility(View.VISIBLE);
            ZiChansAdapter mapapter = new ZiChansAdapter(this);
            mapapter.mData = BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao;
            lv_zichans.setVisibility(View.VISIBLE);
            lv_zichans.setAdapter(mapapter);
        }
        tijiaoganniu();
    }

    void tijiaoganniu() {
        for (Biaoxiiu_zichanliebiaoBean bean : BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao) {
            if (TextUtils.isEmpty(bean.imageUrl)) {
                tv_tijiao.setEnabled(false);
                return;
            }
        }
        tv_tijiao.setEnabled(true);
    }

    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<Biaoxiiu_zichanliebiaoBean> mData = new ArrayList<Biaoxiiu_zichanliebiaoBean>();

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
                convertView = inflater.inflate(R.layout.item_baoxiuzichanliebiao_xuanzehou, null);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.tv_shangchuanfujiian = convertView.findViewById(R.id.tv_shangchuanfujiian);
//                util.iv_erweima = convertView.findViewById(R.id.iv_erweima);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
//                util.tv_diaochubumen = convertView.findViewById(R.id.tv_diaochubumen);
//                util.tv_diaochubumen_dianji = convertView.findViewById(R.id.tv_diaochubumen_dianji);
                convertView.setTag(util);
            } else {
                util = (ZiChansAdapter.Util) convertView.getTag();
            }
            Biaoxiiu_zichanliebiaoBean bean = mData.get(position);

            util.tv_epcid.setText(bean.epcid);
//            tv_tianxieshenqing
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.glDeptName);
            util.tv_cunfangdizhi.setText(bean.storeAddress);

            util.tv_shangchuanfujiian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(BaoxiuGuanliActivity.this, BaoxiushangchuanfujianActivity.class);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
            if (TextUtils.isEmpty(bean.imageUrl)) {
                util.tv_shangchuanfujiian.setVisibility(View.VISIBLE);
            } else {
                util.tv_shangchuanfujiian.setVisibility(View.GONE);
            }
            
            return convertView;
        }


        class Util {
            public TextView tv_epcid;
            public View tv_shangchuanfujiian;
            //            public View iv_erweima;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_cunfangdizhi;

//            public TextView tv_diaochubumen;
//            public View tv_diaochubumen_dianji;

        }
    }
}
