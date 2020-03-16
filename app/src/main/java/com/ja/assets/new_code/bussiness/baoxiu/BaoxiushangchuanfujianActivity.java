package com.ja.assets.new_code.bussiness.baoxiu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
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
import com.ja.assets.new_code.view.chenjinshi.StatusBarUtil;
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


public class BaoxiushangchuanfujianActivity extends Activity {

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

        setContentView(R.layout.activity_baoxiuishangchuanfujian);
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
                new PhotoUtils().pickImage(BaoxiushangchuanfujianActivity.this, 1, 0);
            }
        });


    }

    int position;

    void initData() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
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
                LuBanImgUtils luBanImgUtils = new LuBanImgUtils(BaoxiushangchuanfujianActivity.this, photoList1.get(0));
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
                                                                             BaoxiuzichanliebiaoActivity.yixuanzeZiChanliebiao.get(position).imageUrl = message.url;
                                                                             finish();
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