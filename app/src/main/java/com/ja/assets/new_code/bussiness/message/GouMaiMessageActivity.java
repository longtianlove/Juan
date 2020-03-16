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
import com.ja.assets.new_code.Constants;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.BuyCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckItemListPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckMainInfoPostBean;
import com.ja.assets.new_code.bussiness.bean.post.DeployCheckPostBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.BuyCheckMainInfoResultBean;
import com.ja.assets.new_code.bussiness.bean.result.DeployCheckItemListResultBean;
import com.ja.assets.new_code.bussiness.bean.result.DeployCheckMainInfoResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.WithScrolleViewListView;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.ToastUtil;
import com.liji.imagezoom.util.ImageZoom;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class GouMaiMessageActivity extends Activity {

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
        setContentView(R.layout.activity_goumaimessage);
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
        tv_fujian = findViewById(R.id.tv_fujian);


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
                    for (BuyCheckItemListResultBean bean2 : buyCheckPostBean.flowTodoItems) {
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
                buyCheckPostBean.zcBuyId = bizid;
                buyCheckPostBean.itemStatus = itemStatus;
                buyCheckPostBean.neirong = neirong;
                String token = ACacheUtil.getToken();

                ApiUtils.getApiService().buyCheck(token, buyCheckPostBean).enqueue(new JuanCallback<BaseBean>() {
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

    BuyCheckPostBean buyCheckPostBean;

    TextView tv_shenqingren;
    TextView tv_shenqingbumen;
    TextView tv_shenqingshijian;
    TextView tv_zhuangtai;
    TextView tv_fujian;


    int bizid;
    long id;
    public long itemStatus;

    void initData() {
        String token = ACacheUtil.getToken();
        bizid = getIntent().getIntExtra("bizid", -1);
        BuyCheckMainInfoPostBean bean = new BuyCheckMainInfoPostBean();
        bean.zcBuyId = bizid;
        ApiUtils.getApiService().buyCheckMainInfo(token, bean).enqueue(new JuanCallback<BaseBean<BuyCheckMainInfoResultBean>>() {
            @Override
            public void onSuccess(Response<BaseBean<BuyCheckMainInfoResultBean>> response, BaseBean<BuyCheckMainInfoResultBean> message) {
                if (message.code == 0) {
                    tv_shenqingren.setText(message.data.nickname);
                    tv_shenqingbumen.setText(message.data.deptname);
                    tv_shenqingshijian.setText(message.data.createTime);
                    if (message.data.status == 1) {
                        tv_zhuangtai.setText("审核中");
                    } else {
                        tv_zhuangtai.setText("审核完成");
                    }
                    tv_fujian.setText(message.data.fileUrl);
                    tv_fujian.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String url=Constants.Url.FILE_HOST+message.data.fileUrl;
                            ArrayList<String> urls=new ArrayList<>();
                            urls.add(url);
                            ImageZoom.show(GouMaiMessageActivity.this, url,urls);
                        }
                    });
                }
            }

            @Override
            public void onFail(Call<BaseBean<BuyCheckMainInfoResultBean>> call, Throwable t) {

            }
        });
        BuyCheckItemListPostBean bean1 = new BuyCheckItemListPostBean();
        id = getIntent().getLongExtra("id", -1);
        bean1.todoId = id;
        ApiUtils.getApiService().buyCheckItemList(token, bean1).enqueue(new JuanCallback<BaseBean<ArrayList<BuyCheckItemListResultBean>>>() {
            @Override
            public void onSuccess(Response<BaseBean<ArrayList<BuyCheckItemListResultBean>>> response, BaseBean<ArrayList<BuyCheckItemListResultBean>> message) {
                if (message.code == 0) {
                    if (message.data.size() >= 0) {
                        madapter.mData = message.data;
                        buyCheckPostBean.flowTodoItems = message.data;
                        madapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFail(Call<BaseBean<ArrayList<BuyCheckItemListResultBean>>> call, Throwable t) {

            }
        });


        buyCheckPostBean = new BuyCheckPostBean();

    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<BuyCheckItemListResultBean> mData = new ArrayList<BuyCheckItemListResultBean>();

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
                convertView = inflater.inflate(R.layout.item_goumaizichan, null);
                util.ll_all = convertView.findViewById(R.id.ll_all);
                util.tv_buyNum = convertView.findViewById(R.id.tv_buyNum);
                util.tv_name = convertView.findViewById(R.id.tv_name);
                util.tv_gldeptname = convertView.findViewById(R.id.tv_gldeptname);
                util.tv_zongjia = convertView.findViewById(R.id.tv_zongjia);
                util.et_danwei = convertView.findViewById(R.id.et_danwei);
                util.et_goumaidanjia = convertView.findViewById(R.id.et_goumaidanjia);
                util.et_shuliang = convertView.findViewById(R.id.et_shuliang);
                util.tv_sydeptname = convertView.findViewById(R.id.tv_sydeptname);
                util.et_miaoshu = convertView.findViewById(R.id.et_miaoshu);

                util.et_pinpai = convertView.findViewById(R.id.et_pinpai);
                util.et_xinghao = convertView.findViewById(R.id.et_xinghao);
                util.rg_goumai_caozuo = convertView.findViewById(R.id.rg_goumai_caozuo);
                util.et_gongyingshangmingcheng = convertView.findViewById(R.id.et_gongyingshangmingcheng);
                util.et_beizhu = convertView.findViewById(R.id.et_beizhu);

                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            BuyCheckItemListResultBean bean = mData.get(position);
            util.tv_buyNum.setText(bean.buyNum);
            util.tv_name.setText(bean.name);
            util.tv_gldeptname.setText(bean.gldeptname);
            util.et_goumaidanjia.setText(bean.price + "");
            Util finalUtil = util;
            util.et_goumaidanjia.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        bean.price = Double.parseDouble(s.toString());
                        finalUtil.tv_zongjia.setText((bean.num * bean.price) + "");
                    } catch (Exception e) {
                        bean.price = 1;
                        finalUtil.tv_zongjia.setText((bean.num * bean.price) + "");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            util.et_shuliang.setText(bean.num + "");

            util.et_shuliang.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    try {
                        bean.num = Integer.parseInt(s.toString());
                        finalUtil.tv_zongjia.setText((bean.num * bean.price) + "");
                    } catch (Exception e) {
                        bean.num = 1;
                        finalUtil.tv_zongjia.setText((bean.num * bean.price) + "");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            util.tv_zongjia.setText((bean.num * bean.price) + "");
            util.et_danwei.setText(bean.unit);
            util.et_danwei.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bean.unit = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            util.tv_sydeptname.setText(bean.sydeptname);
            util.et_miaoshu.setText(bean.useDes);
            util.et_miaoshu.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bean.useDes = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            util.et_pinpai.setText(bean.brand);
            util.et_pinpai.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bean.brand = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            util.et_xinghao.setText(bean.model);
            util.et_xinghao.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bean.model = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            util.rg_goumai_caozuo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.rb_tongyi) {
                        bean.status = 1;
                    } else if (checkedId == R.id.rb_jujue) {
                        bean.status = 2;
                    } else if (checkedId == R.id.rb_bohui) {
                        bean.status = 3;
                    }

                    for (BuyCheckItemListResultBean bean2 : mData) {
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

            itemStatus = bean.itemStatus;
            util.et_gongyingshangmingcheng.setText(bean.supplierName);
            util.et_gongyingshangmingcheng.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bean.supplierName = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            util.et_beizhu.setText(bean.buyBz);
            util.et_beizhu.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    bean.buyBz = s.toString();
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            return convertView;
        }


        class Util {
            public View ll_all;
            TextView tv_buyNum;
            TextView tv_name;
            TextView tv_gldeptname;
            EditText et_goumaidanjia;
            TextView tv_zongjia;
            EditText et_danwei;
            EditText et_shuliang;
            TextView tv_sydeptname;

            EditText et_miaoshu;
            EditText et_pinpai;
            EditText et_xinghao;
            EditText et_gongyingshangmingcheng;
            EditText et_beizhu;


            RadioGroup rg_goumai_caozuo;

        }
    }
}