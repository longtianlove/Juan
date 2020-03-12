package com.ja.assets.new_code.bussiness.inventory;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;
import com.ja.assets.adapter.ViewPageManagerAdapter;
import com.ja.assets.databinding.ActivityInventoryBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuangjianpandiandanBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.JiluXunjianDetail;
import com.ja.assets.new_code.bussiness.bean.result.WeiPandianResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.DialogUtil;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.ui.activity.inventory.NoInventoryFragment;
import com.ja.assets.ui.activity.inventory.YesInventoryFragment;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.ACacheUtil;


import org.jetbrains.annotations.NotNull;

import java.io.UTFDataFormatException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class NewCode_InventoryActivity extends Activity {

    View iv_back;
    View tv_create;
    MaterialDesignPtrFrameLayout ptr_refresh;
    JuanListView lv_weipandian;
    DoctorAdapter madapter;

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
        setContentView(R.layout.newcode_activity_inventory);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_create = findViewById(R.id.tv_create);
        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuangjianpandiandanBean bean = new ChuangjianpandiandanBean();
                bean.checkDeptId = ACacheUtil.getUserInfo().getDeptId() + "";

                Date temp = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                bean.checkTime = dateFormat.format(temp);

                String token = ACacheUtil.getToken();
                DialogUtil.showProgress(NewCode_InventoryActivity.this, "");
                ApiUtils.getApiService().zcCheckSave(token, bean).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        DialogUtil.closeProgress();
                        if (message.code == 0) {
                            ToastUtil.showAtCenter("创建成功");
                            PAGE_NO = 1;
                            getDoctors();
                        } else if (message.code == 400) {
                            ToastUtil.showAtCenter("该盘点单已经创建过了");
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean> call, Throwable t) {
                        DialogUtil.closeProgress();
                    }
                });
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
        ApiUtils.getApiService().getZcCheckList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<WeiPandianResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<WeiPandianResultBean>>> response, BaseBean<ArrayList<WeiPandianResultBean>> message) {
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
            public void onFail(Call<BaseBean<ArrayList<WeiPandianResultBean>>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
    }


    class DoctorAdapter extends BaseAdapter {

        public Context mcontext;

        List<WeiPandianResultBean> mData = new ArrayList<WeiPandianResultBean>();

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
                convertView = inflater.inflate(R.layout.newcode_item_fragment_inventory_pandianjilu, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_danhao = convertView.findViewById(R.id.tv_danhao);
                util.tv_pandianbumen = convertView.findViewById(R.id.tv_pandianbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_zichanzongshu = convertView.findViewById(R.id.tv_zichanzongshu);
                util.tv_pandianshijian = convertView.findViewById(R.id.tv_pandianshijian);
                util.tv_pandianzhuangtai = convertView.findViewById(R.id.tv_pandianzhuangtai);
                util.tv_shifoufupan = convertView.findViewById(R.id.tv_shifoufupan);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            WeiPandianResultBean bean = mData.get(position);
            util.ll_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(NewCode_InventoryActivity.this, NewCode_ZichanliebiaoActivity.class);
                    intent.putExtra("id", bean.id);
                    startActivity(intent);
                }
            });
            util.tv_danhao.setText(bean.check_num);
            util.tv_pandianbumen.setText(bean.checkDeptName);
            util.tv_guanlibumen.setText(bean.checkUserName);
            util.tv_zichanzongshu.setText(bean.total + "");
            util.tv_pandianshijian.setText(bean.checkTime);
            switch (bean.status) {
                case 0:
                    util.tv_pandianzhuangtai.setText("待盘点");
                    break;
                case 1:
                    util.tv_pandianzhuangtai.setText("盘点中");
                    break;
                case 2:
                    util.tv_pandianzhuangtai.setText("盘点完成");
                    break;
                case 3:
                    util.tv_pandianzhuangtai.setText("再次盘点数据");
                    break;
            }
            if (0 == bean.result) {
                util.tv_shifoufupan.setText("否");
            } else {
                util.tv_shifoufupan.setText("是");

            }
            return convertView;
        }


        class Util {
            View ll_all;
            TextView tv_danhao;
            TextView tv_pandianbumen;
            TextView tv_guanlibumen;
            TextView tv_zichanzongshu;
            TextView tv_pandianshijian;
            TextView tv_pandianzhuangtai;
            TextView tv_shifoufupan;

        }
    }
}
