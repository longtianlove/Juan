package com.ja.assets.new_code.bussiness.Patrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ja.assets.R;
import com.ja.assets.glide.GlideImgUtils;
import com.ja.assets.new_code.bussiness.bean.result.UploadImageResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.LuBanImgUtils;
import com.ja.assets.utils.PhotoUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;


public class Jilu_PatrolCheckDetailActivity extends Activity {

    View ll_tijiaotupian;
    View iv_back;

    RadioGroup rg_waiguan;
    RadioButton intactRadioBtn;
    RadioButton damageRadioBtn;
    public String waiguan="";

    RadioGroup rg_gongneng;
    RadioButton normalRadioBtn;
    RadioButton abnormalRadioBtn;
    public String gongneng="";

    RadioGroup rg_jieguo;
    RadioButton reachingStandardRadioBtn;
    RadioButton noReachingStandardRadioBtn;
    public String jieguo="";

    View submitInspectionDetailBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jilu_activity_inspection_detail);
        initView();
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

    }

    void initData() {
        Intent intent = getIntent();
        String epcid = intent.getStringExtra("epcid");

    }



}