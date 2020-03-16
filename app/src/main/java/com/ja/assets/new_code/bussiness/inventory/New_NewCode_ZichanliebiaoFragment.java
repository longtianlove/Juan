package com.ja.assets.new_code.bussiness.inventory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.base.BaseJavaFragment;
import com.ja.assets.new_code.bussiness.bean.post.PanyingAndkuiPostBean;
import com.ja.assets.new_code.bussiness.bean.post.WeiPandiianzichanPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ZichanSaomaPostBean;
import com.ja.assets.new_code.bussiness.bean.result.Pandian_zichanliebiaoBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.utils.ACacheUtil;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class New_NewCode_ZichanliebiaoFragment extends BaseJavaFragment {


    MaterialDesignPtrFrameLayout ptr_refresh;
    JuanListView lv_zichans;
    ZiChansAdapter madapter;

    public int type;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_new_newcode_zichanliiebiao, container, false);
        initView(rootView);
        return rootView;
    }


    public int id;
   public int PAGE_NO = 1;

    void initView(View rootView) {
        ptr_refresh = rootView.findViewById(R.id.ptr_refresh);
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

        lv_zichans = rootView.findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(getActivity());
        lv_zichans.setAdapter(madapter);
        lv_zichans.setOnUpLoadListener(new JuanListView.OnUpLoadListener() {
            @Override
            public void onUpLoad() {
                initData();
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        PAGE_NO = 1;
        initData();
    }

    void initData() {

        WeiPandiianzichanPostBean bean = new WeiPandiianzichanPostBean();
        bean.id = id;
        bean.type = type;
        bean.limit = 10;
        bean.offset = PAGE_NO;
        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().zichanliebiao(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> response, BaseBean<ArrayList<Pandian_zichanliebiaoBean>> message) {
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
            public void onFail(Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
    }

    private int requestBackCode = 100;

    void toSaomao() {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, requestBackCode);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == requestBackCode && resultCode == RESULT_OK) {
            String epcid = data.getStringExtra(Constant.CODED_CONTENT);
//            Intent intent = new Intent(NewCode_ZichanliebiaoActivity.this, NewCode_PatrolCheckDetailActivity.class);
//            intent.putExtra("epcid", epcid);
//            startActivity(intent);


            ZichanSaomaPostBean bean = new ZichanSaomaPostBean();
            bean.epcid = epcid;
            bean.zcCheckId = id;
            String token = ACacheUtil.getToken();

            ApiUtils.getApiService().updateZcItemStatus(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>>() {
                @Override
                public void onSuccess(Response<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> response, BaseBean<ArrayList<Pandian_zichanliebiaoBean>> message) {
                    if (message.code == 0) {
                        ToastUtil.showAtCenter("资产提交成功");
                        PAGE_NO = 1;
                        initData();
                    }
                }

                @Override
                public void onFail(Call<BaseBean<ArrayList<Pandian_zichanliebiaoBean>>> call, Throwable t) {

                }
            });

        }
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
                convertView = inflater.inflate(R.layout.new_item_zichanliebiao_record, null);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.ll_erweima=convertView.findViewById(R.id.ll_erweima);
                util.iv_erweima = convertView.findViewById(R.id.iv_erweima);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
                convertView.setTag(util);
            } else {
                util = (ZiChansAdapter.Util) convertView.getTag();
            }
            Pandian_zichanliebiaoBean bean = mData.get(position);
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.iv_erweima.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    toSaomao();
                }
            });
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.glDeptName);
            util.tv_cunfangdizhi.setText(bean.storeAddress);

            if (type == 1) {
                util.ll_erweima.setVisibility(View.GONE);
            } else {
                util.ll_erweima.setVisibility(View.VISIBLE);
            }


            return convertView;
        }


        class Util {
            public TextView tv_epcid;
            public View ll_erweima;
            public View iv_erweima;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_cunfangdizhi;

        }
    }


}
