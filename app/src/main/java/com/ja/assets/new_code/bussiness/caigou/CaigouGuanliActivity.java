package com.ja.assets.new_code.bussiness.caigou;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.Caigouitemzichan;
import com.ja.assets.new_code.bussiness.bean.post.CaigouzichanPostBean;
import com.ja.assets.new_code.bussiness.bean.post.ChuzhishenqingPostBean;
import com.ja.assets.new_code.bussiness.bean.result.Chuzhi_zichanliebiaoBean;
import com.ja.assets.new_code.bussiness.chuzhi.ChuzhiGuanliActivity;
import com.ja.assets.new_code.bussiness.chuzhi.ChuzhiYuanyinActivity;
import com.ja.assets.new_code.bussiness.chuzhi.ChuzhishangchuanfujianActivity;
import com.ja.assets.new_code.bussiness.chuzhi.ChuzhizichanliebiaoActivity;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class CaigouGuanliActivity extends Activity {

    View iv_back;
    View tv_tianjiazichan;


    View scl_bag;
    TextView tv_bumen;
    TextView tv_shangchuanfujiian;
    ListView lv_zichans;
    ZiChansAdapter madapter;


    public static TextView tv_wenjianmingcheng;
    TextView tv_tijiao;


    View noAssetsLinear;

    public static CaigouzichanPostBean caigouzichanPostBean = new CaigouzichanPostBean();


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


        setContentView(R.layout.activity_caigouguanli);
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
                Intent intent = new Intent(CaigouGuanliActivity.this, CaigouNewZichanActivity.class);
                startActivity(intent);
            }
        });
        scl_bag = findViewById(R.id.scl_bag);
        tv_bumen = findViewById(R.id.tv_bumen);
        tv_bumen.setText(ACacheUtil.getUserInfo().getDeptName());
        caigouzichanPostBean.companyName = ACacheUtil.getUserInfo().getDeptName();
        tv_shangchuanfujiian = findViewById(R.id.tv_shangchuanfujiian);
        tv_shangchuanfujiian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaigouGuanliActivity.this, CaigouchuanfujianActivity.class);
                startActivity(intent);
            }
        });


        lv_zichans = findViewById(R.id.lv_zichans);
        madapter = new ZiChansAdapter(this);
        lv_zichans.setAdapter(madapter);
        tv_wenjianmingcheng = findViewById(R.id.tv_wenjianmingcheng);
        tv_tijiao = findViewById(R.id.tv_tijiao);
        tv_tijiao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(caigouzichanPostBean.fileUrl)) {
                    ToastUtil.toast("请上传附件");
                    return;
                }
                String token = ACacheUtil.getToken();
                ApiUtils.getApiService().buy_insertData(token, caigouzichanPostBean).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        if (message.code == 0) {
                            caigouzichanPostBean.fileUrl = "";
                            caigouzichanPostBean.fileName = "";
                            caigouzichanPostBean.zcBuyItemList.clear();
                            noAssetsLinear.setVisibility(View.VISIBLE);
                            scl_bag.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFail(Call<BaseBean> call, Throwable t) {

                    }
                });
            }
        });

        noAssetsLinear = findViewById(R.id.noAssetsLinear);

        caigouzichanPostBean.fileUrl = "";
        caigouzichanPostBean.fileName = "";
        caigouzichanPostBean.zcBuyItemList.clear();


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (caigouzichanPostBean.zcBuyItemList.size() == 0) {
            scl_bag.setVisibility(View.GONE);
            noAssetsLinear.setVisibility(View.VISIBLE);
        } else {
            scl_bag.setVisibility(View.VISIBLE);
            madapter.mData = caigouzichanPostBean.zcBuyItemList;
            madapter.notifyDataSetChanged();
            noAssetsLinear.setVisibility(View.GONE);
        }

    }


    class ZiChansAdapter extends BaseAdapter {

        public Context mcontext;

        List<Caigouitemzichan> mData = new ArrayList<Caigouitemzichan>();

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
                convertView = inflater.inflate(R.layout.item_caigouzichan_xuanzehou_guanli, null);
                util.tv_zichanmingcheng = convertView.findViewById(R.id.tv_zichanmingcheng);
                util.tv_guanlibumen = convertView.findViewById(R.id.tv_guanlibumen);
                util.tv_caigoushuliang = convertView.findViewById(R.id.tv_caigoushuliang);
                util.tv_guigexinghao = convertView.findViewById(R.id.tv_guigexinghao);
                util.tv_chanpinpinpai = convertView.findViewById(R.id.tv_chanpinpinpai);
                util.tv_gongyingshangmingcheng = convertView.findViewById(R.id.tv_gongyingshangmingcheng);
                util.tv_jiliangdanwei = convertView.findViewById(R.id.tv_jiliangdanwei);
                util.tv_caigoudanjia = convertView.findViewById(R.id.tv_caigoudanjia);
                util.tv_yongtu = convertView.findViewById(R.id.tv_yongtu);

                util.et_beizhu=convertView.findViewById(R.id.et_beizhu);

                convertView.setTag(util);
            } else {
                util = (Util) convertView.getTag();
            }
            Caigouitemzichan bean = mData.get(position);
            util.tv_zichanmingcheng.setText(bean.name);
            util.tv_guanlibumen.setText(bean.gldeptname);
            util.tv_caigoushuliang.setText(bean.num + "");
            util.tv_guigexinghao.setText(bean.model);
            util.tv_chanpinpinpai.setText(bean.brand);
            util.tv_gongyingshangmingcheng.setText(bean.supplierName);
            util.tv_jiliangdanwei.setText(bean.unit);
            util.tv_caigoudanjia.setText(bean.price + "");
            util.tv_yongtu.setText(bean.useDes);

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
            TextView tv_zichanmingcheng;
            TextView tv_guanlibumen;
            TextView tv_caigoushuliang;
            TextView tv_guigexinghao;
            TextView tv_chanpinpinpai;
            TextView tv_gongyingshangmingcheng;
            TextView tv_jiliangdanwei;
            TextView tv_caigoudanjia;
            TextView tv_yongtu;

            EditText et_beizhu;

        }
    }
}
