package com.ja.assets.new_code.bussiness.Patrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ja.assets.R;
import com.ja.assets.glide.GlideImgUtils;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.result.UploadImageResultBean;
import com.ja.assets.new_code.http.ApiUtils;
import com.ja.assets.new_code.http.JuanCallback;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.LuBanImgUtils;
import com.ja.assets.utils.PhotoUtils;
import com.ja.assets.utils.ToastUtil;

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
        ll_tijiaotupian = findViewById(R.id.ll_tijiaotupian);
        ll_tijiaotupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoUtils().pickImage(NewCode_PatrolCheckDetailActivity.this, 1, 0);
            }
        });

        submitInspectionDetailBtn=findViewById(R.id.submitInspectionDetailBtn);
        submitInspectionDetailBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(imageUrl)){
                    //
                }
            }
        });
    }

    void initData() {
        Intent intent = getIntent();
        String epcid = intent.getStringExtra("epcid");
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
                LuBanImgUtils luBanImgUtils=new LuBanImgUtils(NewCode_PatrolCheckDetailActivity.this,photoList1.get(0));
                luBanImgUtils.setListener(new LuBanImgUtils.ImgListener() {
                    @Override
                    public void handleResult(@NotNull File file) {
                        uploadImage(file.getPath());
                    }
                });

                break;
        }
    }


    public String imageUrl="";

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