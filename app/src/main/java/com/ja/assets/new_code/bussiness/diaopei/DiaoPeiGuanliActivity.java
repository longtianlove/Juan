package com.ja.assets.new_code.bussiness.diaopei;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    View noAssetsLinear;
    View scl_bag;
    ListView lv_zichans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        noAssetsLinear=findViewById(R.id.noAssetsLinear);
        scl_bag=findViewById(R.id.scl_bag);
        lv_zichans=findViewById(R.id.lv_zichans);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao.size() == 0) {
            noAssetsLinear.setVisibility(View.VISIBLE);
            scl_bag.setVisibility(View.GONE);
            lv_zichans.setVisibility(View.GONE);
        }else{
            noAssetsLinear.setVisibility(View.GONE);
            scl_bag.setVisibility(View.VISIBLE);
            ZiChansAdapter mapapter=new ZiChansAdapter(this);
            mapapter.mData=DiaopeizichanliebiaoActivity.yixuanzeZiChanliebiao;
            lv_zichans.setVisibility(View.VISIBLE);
            lv_zichans.setAdapter(mapapter);
        }
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

        }
    }
}
