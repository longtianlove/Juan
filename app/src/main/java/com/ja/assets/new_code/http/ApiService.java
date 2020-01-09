package com.ja.assets.new_code.http;

import com.ja.assets.new_code.Constants;
import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.bussiness.bean.result.UploadImageResultBean;
import com.ja.assets.new_code.bussiness.bean.result.ZiChansBean;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by long
 */
public interface ApiService {

    @POST(Constants.Url.Patrol.PatrolCheckList)
    Call<BaseBean<ArrayList<ZiChansBean>>> PatrolCheckList(@Header("token") String token);

    @POST(Constants.Url.Patrol.JiluPatrolCheckList)
    Call<BaseBean<ArrayList<ZiChansBean>>> JiluPatrolCheckList(@Header("token") String token);


    //上传头像
    @Multipart
    @POST(Constants.Url.UploadImage)
    Call<UploadImageResultBean> uploadLogo(
            @Part MultipartBody.Part file,@Header("token") String token
    );
}