package com.ja.assets.new_code.bussiness.inventory;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.base.BaseJavaFragment;
import com.ja.assets.new_code.bussiness.Patrol.NewCode_PatrolCheckListActivity;
import com.ja.assets.new_code.bussiness.bean.post.PanyingAndkuiPostBean;
import com.ja.assets.new_code.bussiness.bean.result.PandianBaseResultBean;
import com.ja.assets.new_code.bussiness.bean.result.Pandian_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.ZiChansBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.utils.ACacheUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class ZichanbaobiaoFragment extends BaseJavaFragment {


    public int id;
    public int type;
    MaterialDesignPtrFrameLayout ptr_refresh;

    com.ja.assets.new_code.view.JuanListView lv_doctors;
    ZiChansAdapter madapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_zichanbaobiao, container, false);
        initView(rootView);
        initData();
        return rootView;
    }

    void initView(View rootView) {
        ptr_refresh = (MaterialDesignPtrFrameLayout) rootView.findViewById(R.id.ptr_refresh);
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
        lv_doctors = (com.ja.assets.new_code.view.JuanListView) rootView.findViewById(R.id.lv_doctors);
        madapter = new ZiChansAdapter(mActivity);
        lv_doctors.setAdapter(madapter);
        lv_doctors.setOnUpLoadListener(new JuanListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                getDoctors();
            }
        });
    }


    void initData() {
        PAGE_NO = 1;
        getDoctors();
    }

    int PAGE_NO = 1;
    int PAGE_SIZE = 10;


    void getDoctors() {
        String token = ACacheUtil.getToken();
        PanyingAndkuiPostBean bean = new PanyingAndkuiPostBean();
        bean.limit = 10;
        bean.offset = PAGE_NO;
        bean.type = type;
        bean.zcCheckId = id;
        ApiUtils.getApiService().profitList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> response, BaseBean<ArrayList<Pandian_zichanliebiaoBean>> message) {
                ptr_refresh.refreshComplete();
                if (message.code == 0) {
                    if (message.data != null && message.data.size() >= 0) {
                        lv_doctors.setLoading(false);
                        if (PAGE_NO == 1) {
                            madapter.mData.clear();
                        }
                        //有消息
                        PAGE_NO++;
                        madapter.mData.addAll(message.data);
                        if (message.data.size() < 10) {
                            lv_doctors.setHasLoadMore(false);
                            lv_doctors.setLoadAllViewText("暂时只有这么多资产");
                            lv_doctors.setLoadAllFooterVisible(true);
                        } else {
                            lv_doctors.setHasLoadMore(true);
                        }
                        madapter.notifyDataSetChanged();
                    } else {
                        //没有消息
                        lv_doctors.setHasLoadMore(false);
                        lv_doctors.setLoadAllViewText("暂时只有这么多资产");
                        lv_doctors.setLoadAllFooterVisible(true);
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
//        getListDoctorTypePostBean bean = new getListDoctorTypePostBean();
//        bean.userId = UserInstance.getInstance().getUid();
//        bean.token = UserInstance.getInstance().getToken();
//        bean.pageNo = PAGE_NO;
//        bean.pageSize = PAGE_SIZE;
//        bean.type = type;
//        DialogUtil.showProgress(mActivity, "");
//        ApiUtils.getApiService().getListDoctor(bean).enqueue(new TaiShengCallback<BaseBean<DoctorsResultBean>>() {
//            @Override
//            public void onSuccess(Response<BaseBean<DoctorsResultBean>> response, BaseBean<DoctorsResultBean> message) {
//                ptr_refresh.refreshComplete();
//                DialogUtil.closeProgress();
//                switch (message.code) {
//                    case Constants.HTTP_SUCCESS:
//                        if (message.result.records != null && message.result.records.size() > 0) {
//                            lv_doctors.setLoading(false);
//                            if (PAGE_NO == 1) {
//                                madapter.mData.clear();
//                            }
//                            //有消息
//                            PAGE_NO++;
//                            madapter.mData.addAll(message.result.records);
//                            if (message.result.records.size() < 10) {
//                                lv_doctors.setHasLoadMore(false);
//                                lv_doctors.setLoadAllViewText("暂时只有这么多医生");
//                                lv_doctors.setLoadAllFooterVisible(true);
//                            } else {
//                                lv_doctors.setHasLoadMore(true);
//                            }
//                            madapter.notifyDataSetChanged();
//                        } else {
//                            //没有消息
//                            lv_doctors.setHasLoadMore(false);
//                            lv_doctors.setLoadAllViewText("暂时只有这么多医生");
//                            lv_doctors.setLoadAllFooterVisible(true);
//                        }
//                        break;
//                }
//            }
//
//            @Override
//            public void onFail(Call<BaseBean<DoctorsResultBean>> call, Throwable t) {
//                ptr_refresh.refreshComplete();
//                DialogUtil.closeProgress();
//            }
//        });


    }

    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<Pandian_zichanliebiaoBean> mData = new ArrayList<Pandian_zichanliebiaoBean>();

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
                convertView = inflater.inflate(R.layout.new_item_pandian_zichan, null);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);

                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
                util.tv_pandianjieguo = convertView.findViewById(R.id.tv_pandianjieguo);
                convertView.setTag(util);
            } else {
                util = (ZiChansAdapter.Util) convertView.getTag();
            }
            Pandian_zichanliebiaoBean bean = mData.get(position);
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);

            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.glDeptName);
            util.tv_cunfangdizhi.setText(bean.storeAddress);
            switch (bean.result) {
                case 0:
                    util.tv_pandianjieguo.setText("盘点中");
                    break;
                case 1:
                    util.tv_pandianjieguo.setText("盘点完成");
                    break;
                case 2:
                    util.tv_pandianjieguo.setText("盘点异常");
                    break;
            }


            return convertView;
        }


        class Util {
            public TextView tv_epcid;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_cunfangdizhi;
            public TextView tv_pandianjieguo;

        }
    }

}
