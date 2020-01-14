package com.ja.assets.new_code.bussiness.baoxiu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.BaoXiuZichanliiebiaoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeiZichanliiebiaoPostBean;
import com.ja.assets.new_code.bussiness.bean.result.Biaoxiiu_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.bean.result.Diaopei_zichanliebiaoBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.JuanListView;
import com.ja.assets.new_code.view.refresh.MaterialDesignPtrFrameLayout;
import com.ja.assets.utils.ACacheUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Call;
import retrofit2.Response;

public class BaoxiuzichanliebiaoActivity extends Activity {


    EditText et_doctor_search;
    View iv_search_guanbi;


    public View iv_back;
    View tv_create;
    MaterialDesignPtrFrameLayout ptr_refresh;
    JuanListView lv_zichans;
    ZiChansAdapter madapter;

    public static List<Biaoxiiu_zichanliebiaoBean> yixuanzeZiChanliebiao = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baoxiuzichanliebiao);
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
        yixuanzeZiChanliebiao.clear();
    }

    void initView() {
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
                finish();
            }
        });

        iv_search_guanbi = findViewById(R.id.iv_search_guanbi);
        iv_search_guanbi.setVisibility(View.GONE);
        iv_search_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_doctor_search.setText("");
            }
        });
        et_doctor_search = (EditText) findViewById(R.id.et_doctor_search);
        et_doctor_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    iv_search_guanbi.setVisibility(View.VISIBLE);
                } else {
                    iv_search_guanbi.setVisibility(View.GONE);
                }
                keyword = s.toString();
                BaoXiuZichanliiebiaoPostBean bean = new BaoXiuZichanliiebiaoPostBean();
                bean.limit = 10;
                PAGE_NO = 1;
                bean.offset = 1;
                bean.keyword = keyword;
                String token = ACacheUtil.getToken();
                ApiUtils.getApiService().repairList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>> response, BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>> message) {
                        yixuanzeZiChanliebiao.clear();
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
                    public void onFail(Call<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>> call, Throwable t) {
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

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

    public String keyword = "";

    void initData() {
        BaoXiuZichanliiebiaoPostBean bean = new BaoXiuZichanliiebiaoPostBean();
        bean.limit = 10;
        bean.offset = PAGE_NO;
        bean.keyword = keyword;
        String token = ACacheUtil.getToken();
        ApiUtils.getApiService().repairList(token, bean).enqueue(new JuanCallback<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>> response, BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>> message) {
                yixuanzeZiChanliebiao.clear();
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
            public void onFail(Call<BaseBean<ArrayList<Biaoxiiu_zichanliebiaoBean>>> call, Throwable t) {
                ptr_refresh.refreshComplete();
            }
        });
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
            Util util = null;
            // 中间变量
            final int flag = position;
            if (convertView == null) {
                util = new Util();
                LayoutInflater inflater = LayoutInflater.from(mcontext);
                convertView = inflater.inflate(R.layout.item_diaopeizichanliebiao_record, null);
                util.ck_chose = (CheckBox) convertView.findViewById(R.id.ck_chose);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
//                util.iv_erweima = convertView.findViewById(R.id.iv_erweima);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi = convertView.findViewById(R.id.tv_cunfangdizhi);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            Biaoxiiu_zichanliebiaoBean bean = mData.get(position);
            util.ck_chose.setChecked(bean.isChecked);
//            util.ck_chose.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        //如果选中
//                        bean.isChecked = true;
//                        yixuanzeZiChanliebiao.add(bean);
//                    } else {
//                        //如果没选中
//                        bean.isChecked = false;
//                        Iterator<Diaopei_zichanliebiaoBean> it = yixuanzeZiChanliebiao.iterator();
//                        while (it.hasNext()) {
//                            Diaopei_zichanliebiaoBean bean1 = it.next();
//                            if (bean1.id == bean.id) {
//                                it.remove();
//                            }
//                        }
//                    }
//                }
//            });
            Util finalUtil = util;
            util.ck_chose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (bean.isChecked) {
                        bean.isChecked = false;
                        finalUtil.ck_chose.setChecked(false);
                        Iterator<Biaoxiiu_zichanliebiaoBean> it = yixuanzeZiChanliebiao.iterator();
                        while (it.hasNext()) {
                            Biaoxiiu_zichanliebiaoBean bean1 = it.next();
                            if (bean1.id == bean.id) {
                                it.remove();
                            }
                        }

                    } else {
                        //如果选中
                        bean.isChecked = true;
                        finalUtil.ck_chose.setChecked(true);
                        yixuanzeZiChanliebiao.add(bean);
                    }
                }
            });
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.glDeptName);
            util.tv_cunfangdizhi.setText(bean.storeAddress);


            return convertView;
        }


        class Util {
            public CheckBox ck_chose;
            public TextView tv_epcid;
            //            public View iv_erweima;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_shiyongbumen;
            public TextView tv_guanlibumen;
            public TextView tv_cunfangdizhi;

        }
    }


    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}