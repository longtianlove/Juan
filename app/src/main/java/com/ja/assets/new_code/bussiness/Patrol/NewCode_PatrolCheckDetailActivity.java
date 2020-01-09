package com.ja.assets.new_code.bussiness.Patrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ja.assets.R;
import com.ja.assets.glide.GlideImgUtils;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.post.TianxiexunjianPostBean;
import com.ja.assets.new_code.bussiness.bean.result.UploadImageResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.new_code.util.ToastUtil;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.LuBanImgUtils;
import com.ja.assets.utils.PhotoUtils;


import org.greenrobot.eventbus.EventBus;
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


public class NewCode_PatrolCheckDetailActivity extends Activity {

    View ll_tijiaotupian;
    View iv_back;

    RadioGroup rg_waiguan;
    RadioButton intactRadioBtn;
    RadioButton damageRadioBtn;
    public String waiguan = "";

    RadioGroup rg_gongneng;
    RadioButton normalRadioBtn;
    RadioButton abnormalRadioBtn;
    public String gongneng = "";

    RadioGroup rg_jieguo;
    RadioButton reachingStandardRadioBtn;
    RadioButton noReachingStandardRadioBtn;
    public String jieguo = "";

    public EditText et_yijian;

    View submitInspectionDetailBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcode_activity_inspection_detail);
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
        rg_waiguan = findViewById(R.id.rg_waiguan);
        intactRadioBtn = findViewById(R.id.intactRadioBtn);
        damageRadioBtn = findViewById(R.id.damageRadioBtn);
        rg_waiguan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.intactRadioBtn == checkedId) {
                    waiguan = "1";
                } else {
                    waiguan = "0";
                }
            }
        });
        rg_gongneng = findViewById(R.id.rg_gongneng);
        normalRadioBtn = findViewById(R.id.normalRadioBtn);
        abnormalRadioBtn = findViewById(R.id.abnormalRadioBtn);
        rg_gongneng.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.normalRadioBtn == checkedId) {
                    gongneng = "1";
                } else {
                    gongneng = "0";
                }
            }
        });

        rg_jieguo = findViewById(R.id.rg_jieguo);
        reachingStandardRadioBtn = findViewById(R.id.reachingStandardRadioBtn);
        noReachingStandardRadioBtn = findViewById(R.id.noReachingStandardRadioBtn);
        rg_jieguo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (R.id.reachingStandardRadioBtn == checkedId) {
                    jieguo = "1";
                } else {
                    jieguo = "0";
                }
            }
        });

        ll_tijiaotupian = findViewById(R.id.ll_tijiaotupian);
        ll_tijiaotupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoUtils().pickImage(NewCode_PatrolCheckDetailActivity.this, 1, 0);
            }
        });


        et_yijian = findViewById(R.id.et_yijian);

        submitInspectionDetailBtn = findViewById(R.id.submitInspectionDetailBtn);
        submitInspectionDetailBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(waiguan)) {
                    ToastUtil.showAtCenter("请查看外观状况");
                    return;
                }
                if (TextUtils.isEmpty(gongneng)) {
                    ToastUtil.showAtCenter("请查看功能状况");
                    return;
                }
                if (TextUtils.isEmpty(jieguo)) {
                    ToastUtil.showAtCenter("请选择巡检结果");
                    return;
                }
                if (TextUtils.isEmpty(et_yijian.getText().toString())) {
                    ToastUtil.showAtCenter("请填写巡检意见");
                    return;
                }
                if (TextUtils.isEmpty(imageUrl)) {
                    ToastUtil.showAtCenter("请上传设备图片");
                    return;
                }

                TianxiexunjianPostBean bean = new TianxiexunjianPostBean();
                bean.appearance = waiguan;
                bean.epcid = epcid;
                bean.funct = gongneng;
                bean.img = imageUrl;
                bean.opinion = et_yijian.getText().toString();
                bean.result = jieguo;
                String token = ACacheUtil.getToken();
                ApiUtils.getApiService().insertRecord(token, bean).enqueue(new JuanCallback<BaseBean>() {
                    @Override
                    public void onSuccess(Response<BaseBean> response, BaseBean message) {
                        if (message.code == 0) {
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

    String epcid;

    void initData() {
        Intent intent = getIntent();
        epcid = intent.getStringExtra("epcid");
        waiguan = "";
        gongneng = "";
        jieguo = "";
        imageUrl = "";
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null && resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 0:

                List<String> photoList1 = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                GlideImgUtils.loadImage(this, photoList1.get(0), R.mipmap.add_img, findViewById(R.id.lossReportImg));
                LuBanImgUtils luBanImgUtils = new LuBanImgUtils(NewCode_PatrolCheckDetailActivity.this, photoList1.get(0));
                luBanImgUtils.setListener(new LuBanImgUtils.ImgListener() {
                    @Override
                    public void handleResult(@NotNull File file) {
                        uploadImage(file.getPath());
                    }
                });

                break;
        }
    }


    public String imageUrl = "";

    //上传头像信息
    public void uploadImage(final String path) {
        try {

            //把Bitmap保存到sd卡中
            File fImage = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), fImage);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", fImage.getName(), requestFile);
            String token = ACacheUtil.getToken();
            ApiUtils.getApiService().uploadLogo(body, token).enqueue(new JuanCallback<UploadImageResultBean>() {

                                                                         @Override
                                                                         public void onSuccess(Response<UploadImageResultBean> response, UploadImageResultBean message) {
                                                                             imageUrl = message.url;
//                                                                      switch (message.code) {
//                                                                          case Constants.HTTP_SUCCESS:
//                                                                              String path = message.result.path;
//                                                                              UserInstance.getInstance().userInfo.avatar = path;
//                                                                              SPUtil.putAVATAR(UserInstance.getInstance().userInfo.avatar);
//                                                                              EventBus.getDefault().post(new EventManage.uploadImageSuccess(path));
//                                                                              break;
//                                                                      }


                                                                         }

                                                                         @Override
                                                                         public void onFail(Call<UploadImageResultBean> call, Throwable t) {

                                                                         }
                                                                     }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}