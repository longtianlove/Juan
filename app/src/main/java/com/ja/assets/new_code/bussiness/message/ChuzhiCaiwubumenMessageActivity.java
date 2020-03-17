package com.ja.assets.new_code.bussiness.message;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.BfCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BfCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ShbCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BfCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BfCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckMainInfoResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.WithScrolleViewListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.ToastUtil;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ChuzhiCaiwubumenMessageActivity extends Activity {

    public View iv_back;
    WithScrolleViewListView lv_zichans;
    ZiChansAdapter madapter;

    EditText et_shenpiyijian;
    TextView tv_querendiaopei;

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
        setContentView(R.layout.activity_chuzhicaiwumessage);
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


        tv_shenqingbumen = findViewById(R.id.tv_shenqingbumen);


        lv_zichans = findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(this);
        lv_zichans.setAdapter(madapter);
        et_shenpiyijian = findViewById(R.id.et_shenpiyijian);
        et_shenpiyijian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    tv_querendiaopei.setEnabled(false);
                } else {
                    for (BfCheckItemListResultBean bean2 : shbCheckPostBean.flowTodoItems) {
                        if (bean2.status == 0) {
                            tv_querendiaopei.setEnabled(false);
                            return;
                        }
                    }
                    tv_querendiaopei.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tv_querendiaopei = findViewById(R.id.tv_querendiaopei);
        tv_querendiaopei.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String neirong = et_shenpiyijian.getText().toString();
                shbCheckPostBean.bizid = bizid;
                shbCheckPostBean.todoid = id;
                shbCheckPostBean.itemStatus = itemStatus;
                shbCheckPostBean.neirong = neirong;
                String token = ACacheUtil.getToken();

                ApiUtils.getApiService().cwCheck(token, shbCheckPostBean).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        if (message.code == 0) {
                            ToastUtil.toast("处理成功");
                            finish();
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean> call, Throwable t) {

                    }
                });


//                ApiUtils.getApiService().buyCheck(token, buyCheckPostBean).enqueue(new JuanCallback<BaseBean>() {
//                    @Override
//                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
//                        if (message.code == 0) {
//                            ToastUtil.toast("处理成功");
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onFail(Call<BaseBean> call, Throwable t) {
//
//                    }
//                });
            }
        });
    }

    ShbCheckPostBean shbCheckPostBean;


    TextView tv_shenqingbumen;


    int bizid;
    long id;
    public long itemStatus;


    void initData() {
        String token = ACacheUtil.getToken();
        bizid = getIntent().getIntExtra("bizid", -1);
        id = getIntent().getLongExtra("id", -1);
        BfCheckMainInfoPostBean bean = new BfCheckMainInfoPostBean();
        bean.bizid = bizid;
        bean.todoid = id;
        ApiUtils.getApiService().bfCheckMainInfo(token, bean).enqueue(new JuanCallback<BaseBean<BfCheckMainInfoResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<BfCheckMainInfoResultBean>> response, BaseBean<BfCheckMainInfoResultBean> message) {
                if (message.code == 0) {
                    tv_shenqingbumen.setText(message.data.applyDeptName);

//                    if (message.data.result == 0) {
//                        tv_querendiaopei.setText("提交财务");
//                    } else {
//                        tv_querendiaopei.setText("确认审批");
//                    }

                }
            }

            @Override
            public void onFail(Call<BaseBean<BfCheckMainInfoResultBean>> call, Throwable t) {

            }
        });

        BfCheckItemListPostBean bean1 = new BfCheckItemListPostBean();
        bean1.todoid = id;
        ApiUtils.getApiService().bfCheckItemList(token, bean1).enqueue(new JuanCallback<BaseBean<ArrayList<BfCheckItemListResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<BfCheckItemListResultBean>>> response, BaseBean<ArrayList<BfCheckItemListResultBean>> message) {
                if (message.code == 0) {
                    madapter.mData = message.data;
                    shbCheckPostBean.flowTodoItems = message.data;
                    madapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<BfCheckItemListResultBean>>> call, Throwable t) {

            }
        });


        shbCheckPostBean = new ShbCheckPostBean();

    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<BfCheckItemListResultBean> mData = new ArrayList<BfCheckItemListResultBean>();

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
                convertView = inflater.inflate(R.layout.item_chuzhicaiwuzichan, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_zichanleibie = convertView.findViewById(R.id.tv_zichanleibie);
                util.tv_kaishishiyongshijian = convertView.findViewById(R.id.tv_kaishishiyongshijian);
                util.tv_shengyuqixian = convertView.findViewById(R.id.tv_shengyuqixian);
                util.tv_yuanjiazhi = convertView.findViewById(R.id.tv_yuanjiazhi);
                util.tv_jingzhi = convertView.findViewById(R.id.tv_jingzhi);
                util.tv_baofeiyuanyin = convertView.findViewById(R.id.tv_baofeiyuanyin);
                util.tv_zhuangtai = convertView.findViewById(R.id.tv_zhuangtai);
                util.rg_chuzhi_caozuo = convertView.findViewById(R.id.rg_chuzhi_caozuo);
                util.ll_caozuo = convertView.findViewById(R.id.ll_caozuo);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            BfCheckItemListResultBean bean = mData.get(position);
            itemStatus = bean.itemStatus;
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_guanlibumen.setText(bean.gldeptname);
            util.tv_shiyongbumen.setText(bean.sydeptname);
            util.tv_zichanleibie.setText(bean.categoryName);
            util.tv_kaishishiyongshijian.setText(bean.startusetime);
            util.tv_shengyuqixian.setText(bean.warrantyperiod);
            util.tv_yuanjiazhi.setText(bean.originalvalue + "");
            util.tv_jingzhi.setText(bean.netvalue + "");
            util.tv_baofeiyuanyin.setText(bean.bz);
            Util finalUtil = util;
            util.rg_chuzhi_caozuo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rb_baofei) {
                        bean.status = 1;
                    } else if (checkedId == R.id.rb_zhengchang) {
                        bean.status = 2;
                    }
                    finalUtil.tv_zhuangtai.setText("已处理");

                    for (BfCheckItemListResultBean bean2 : mData) {
                        if (bean2.status == 0) {
                            tv_querendiaopei.setEnabled(false);
                            return;
                        }
                    }
                    if (!TextUtils.isEmpty(et_shenpiyijian.getText().toString())) {
                        tv_querendiaopei.setEnabled(true);
                    }
                }
            });
            if (bean.status == 0) {
                util.tv_zhuangtai.setText("未处理");
                util.ll_caozuo.setVisibility(View.VISIBLE);
            } else {
                util.tv_zhuangtai.setText("已处理");
                util.ll_caozuo.setVisibility(View.GONE);
            }

            return convertView;
        }


        class Util {
            public View ll_all;
            public TextView tv_epcid;
            public TextView tv_zichanbianhao;
            public TextView tv_zichanmingcheng;
            public TextView tv_guanlibumen;
            public TextView tv_shiyongbumen;
            public TextView tv_zichanleibie;
            public TextView tv_kaishishiyongshijian;
            public TextView tv_shengyuqixian;
            public TextView tv_yuanjiazhi;
            public TextView tv_jingzhi;
            public TextView tv_baofeiyuanyin;
            public TextView tv_zhuangtai;
            public View ll_caozuo;

            RadioGroup rg_chuzhi_caozuo;

        }
    }
}