package com.ja.assets.new_code.bussiness.caigou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.new_code.bussiness.bean.post.Caigouitemzichan;
import com.ja.assets.new_code.bussiness.chuzhi.ChuzhizichanliebiaoActivity;
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;

import org.w3c.dom.Text;

import java.math.BigDecimal;

public class CaigouNewZichanActivity extends Activity {

    View iv_back;
    EditText et_zichan_name;
    EditText et_yongtu;
    //管理部门
    View ll_guanlibumen;
    TextView adminDepartmentTV;
    EditText et_caigoushuliang;
    EditText et_guigexinghao;
    EditText et_jiliangdanwei;
    EditText et_chanpinpinpai;
    EditText et_gongyingshangmingcheng;
    EditText et_danjia;
    EditText et_beizhu;
    TextView btn_tiaojiao;

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


        setContentView(R.layout.activity_caigouxinzeng);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();

    }

    TextWatcher textWatcherListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!checkEmpty()) {
                btn_tiaojiao.setEnabled(true);
            } else {
                btn_tiaojiao.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    void initView() {
        et_zichan_name = findViewById(R.id.et_zichan_name);
        et_zichan_name.addTextChangedListener(textWatcherListener);

        et_yongtu = findViewById(R.id.et_yongtu);
        et_yongtu.addTextChangedListener(textWatcherListener);

        ll_guanlibumen = findViewById(R.id.ll_guanlibumen);
        ll_guanlibumen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaigouNewZichanActivity.this, CaigouBumenActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        adminDepartmentTV = findViewById(R.id.adminDepartmentTV);
        adminDepartmentTV.addTextChangedListener(textWatcherListener);

        et_caigoushuliang = findViewById(R.id.et_caigoushuliang);
        et_caigoushuliang.addTextChangedListener(textWatcherListener);

        et_guigexinghao = findViewById(R.id.et_guigexinghao);
        et_guigexinghao.addTextChangedListener(textWatcherListener);

        et_jiliangdanwei = findViewById(R.id.et_jiliangdanwei);
        et_jiliangdanwei.addTextChangedListener(textWatcherListener);

        et_chanpinpinpai = findViewById(R.id.et_chanpinpinpai);
        et_chanpinpinpai.addTextChangedListener(textWatcherListener);

        et_gongyingshangmingcheng = findViewById(R.id.et_gongyingshangmingcheng);
        et_gongyingshangmingcheng.addTextChangedListener(textWatcherListener);

        et_danjia = findViewById(R.id.et_danjia);
        et_danjia.addTextChangedListener(textWatcherListener);

        et_beizhu = findViewById(R.id.et_beizhu);
        et_beizhu.addTextChangedListener(textWatcherListener);

        btn_tiaojiao = findViewById(R.id.tv_tijiao);
        btn_tiaojiao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Caigouitemzichan bean = new Caigouitemzichan();
                bean.name = et_zichan_name.getText().toString();
                bean.unit = et_jiliangdanwei.getText().toString();
                bean.brand = et_chanpinpinpai.getText().toString();
                try {
                    bean.price = new BigDecimal(et_danjia.getText().toString());
                } catch (Exception e) {
                    bean.price = new BigDecimal(0);
                }
                bean.supplierName = et_gongyingshangmingcheng.getText().toString();
                bean.useDes = et_yongtu.getText().toString();
                bean.num = Integer.parseInt(et_caigoushuliang.getText().toString());
                bean.model = et_guigexinghao.getText().toString();
                bean.glDeptName = glDeptName;
                bean.glDeptId = glDeptId;
                bean.buyBz = et_beizhu.getText().toString();
                CaigouGuanliActivity.caigouzichanPostBean.zcBuyItemList.add(bean);
                finish();
            }
        });
    }

    boolean checkEmpty() {
        if (TextUtils.isEmpty(et_zichan_name.getText().toString())) {
            return true;
        }
        if (TextUtils.isEmpty(et_yongtu.getText().toString())) {
            return true;
        }
        if (TextUtils.isEmpty(adminDepartmentTV.getText().toString())) {
            return true;
        }
        if (TextUtils.isEmpty(et_caigoushuliang.getText().toString())) {
            return true;
        }
//        if (TextUtils.isEmpty(et_guigexinghao.getText().toString())) {
//            return true;
//        }
//        if (TextUtils.isEmpty(et_jiliangdanwei.getText().toString())) {
//            return true;
//        }
//        if (TextUtils.isEmpty(et_chanpinpinpai.getText().toString())) {
//            return true;
//        }
//        if (TextUtils.isEmpty(et_gongyingshangmingcheng.getText().toString())) {
//            return true;
//        }
//        if (TextUtils.isEmpty(et_danjia.getText().toString())) {
//            return true;
//        }


        return false;
    }


    private String glDeptName;
    private long glDeptId;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        glDeptName = data.getStringExtra("name");
        adminDepartmentTV.setText(glDeptName);
        glDeptId = data.getIntExtra("glDeptId", -1);
    }
}
