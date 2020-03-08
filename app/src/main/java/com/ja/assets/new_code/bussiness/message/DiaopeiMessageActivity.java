package com.ja.assets.new_code.bussiness.message;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DiaopeixiangqingPostBean;
import com.ja.assets.new_code.bussiness.bean.result.DeployCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.DeployCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.DiaopeijiluxiangqingBean;
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

public class DiaopeiMessageActivity extends Activity {

    public View iv_back;
    public View iv_saoyisiao;
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
        setContentView(R.layout.activity_diaopeimessage);
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
        tv_shenqingren = findViewById(R.id.tv_shenqingren);
        tv_shenqingbumen = findViewById(R.id.tv_shenqingbumen);
        tv_shenqingshijian = findViewById(R.id.tv_shenqingshijian);
        tv_zhuangtai = findViewById(R.id.tv_zhuangtai);


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
                DeployCheckPostBean bean = new DeployCheckPostBean();
                bean.deployId = bizid;
                bean.todoId = id;
                bean.itemStatus = itemStatus;
                bean.neirong = neirong;
                String token = ACacheUtil.getToken();

                ApiUtils.getApiService().deployCheck(token, bean).enqueue(new JuanCallback<BaseBean>() {
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
            }
        });
    }


    TextView tv_shenqingren;
    TextView tv_shenqingbumen;
    TextView tv_shenqingshijian;
    TextView tv_zhuangtai;


    int bizid;
    long id;
    public long itemStatus;

    void initData() {
        String token = ACacheUtil.getToken();
        bizid = getIntent().getIntExtra("bizid", -1);
        DeployCheckMainInfoPostBean bean = new DeployCheckMainInfoPostBean();
        bean.deployId = bizid;
        ApiUtils.getApiService().deployCheckMainInfo(token, bean).enqueue(new JuanCallback<BaseBean<DeployCheckMainInfoResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<DeployCheckMainInfoResultBean>> response, BaseBean<DeployCheckMainInfoResultBean> message) {
                if (message.code == 0) {
                    tv_shenqingren.setText(message.data.nickname);
                    tv_shenqingbumen.setText(message.data.uDeptName);
                    tv_shenqingshijian.setText(message.data.createTime);
                    tv_zhuangtai.setText(message.data.statusText);
                }
            }

            @Override
            public void onFail(Call<BaseBean<DeployCheckMainInfoResultBean>> call, Throwable t) {

            }
        });

        DeployCheckItemListPostBean bean1 = new DeployCheckItemListPostBean();
        id = getIntent().getLongExtra("id", -1);
        bean1.todoId = id;
        ApiUtils.getApiService().deployCheckItemList(token, bean1).enqueue(new JuanCallback<BaseBean<ArrayList<DeployCheckItemListResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<DeployCheckItemListResultBean>>> response, BaseBean<ArrayList<DeployCheckItemListResultBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        madapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<DeployCheckItemListResultBean>>> call, Throwable t) {

            }
        });

    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<DeployCheckItemListResultBean> mData = new ArrayList<DeployCheckItemListResultBean>();

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
                convertView = inflater.inflate(R.layout.item_diaopeizichan, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_epcid = convertView.findViewById(R.id.tv_epcid);
                util.tv_zichanbianhao = convertView.findViewById(R.id.tv_zichanbianhao);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_shiyongbumen = convertView.findViewById(R.id.tv_shiyongbumen);
                util.tv_guanlibumen=convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_cunfangdizhi=convertView.findViewById(R.id.tv_cunfangdizhi);
                util.tv_diaochubumen=convertView.findViewById(R.id.tv_diaochubumen);
                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            DeployCheckItemListResultBean bean = mData.get(position);
            util.tv_epcid.setText(bean.epcid);
            util.tv_zichanbianhao.setText(bean.zcCodenum);
            util.tv_zichanmingcheng.setText(bean.zcName);
            util.tv_shiyongbumen.setText(bean.syDeptName);
            util.tv_guanlibumen.setText(bean.gldeptname);
            util.tv_cunfangdizhi.setText(bean.storeaddress);
            util.tv_diaochubumen.setText(bean.backdeptname);

            itemStatus = bean.itemStatus;

            return convertView;
        }


        class Util {
            public View ll_all;
            TextView tv_epcid;
            TextView tv_zichanbianhao;
            TextView tv_zichanmingcheng;
            TextView tv_shiyongbumen;
            TextView tv_guanlibumen;
            TextView tv_cunfangdizhi;
            TextView tv_diaochubumen;


        }
    }
}