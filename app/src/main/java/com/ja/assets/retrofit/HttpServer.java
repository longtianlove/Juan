package com.ja.assets.retrofit;

import android.content.Context;

import com.ja.assets.model.DeptBean;
import com.ja.assets.model.HomeIndexCount;
import com.ja.assets.model.ResultResponse;
import com.ja.assets.retrofit.dialog.ProgressSubscriber;

import java.util.List;


public class HttpServer implements ApiSupport {

    private ShowUserView showUserView;
    private Context context;

    public HttpServer(ShowUserView showUserView, Context context) {
        this.showUserView = showUserView;
        this.context = context;
    }


    public void getZcValueAndZcNumber(String token) {
        execute(getApi().getZcValueAndZcNumber(token), new ProgressSubscriber<>(resultResponse -> {
            if (resultResponse.isSuccess()) {
                showUserView.toMainActivity(0, resultResponse.getMessage());
            }
        }, context));
    }

    public void getAllWailDealList(String token) {
        execute(getApi().getAllWailDealList(token), new ProgressSubscriber<>(resultResponse -> {
            if (resultResponse.isSuccess()) {
                showUserView.toMainActivity(1, resultResponse.getMessage());
            }
        }, context));
    }


}
