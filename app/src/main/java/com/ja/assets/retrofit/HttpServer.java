package com.ja.assets.retrofit;

import android.content.Context;
import com.ja.assets.retrofit.dialog.ProgressSubscriber;



public class HttpServer implements ApiSupport {

    private ShowUserView showUserView;
    private Context context;

    public HttpServer(ShowUserView showUserView, Context context) {
        this.showUserView = showUserView;
        this.context = context;
    }


    public void getZcValueAndZcNumber(String token) {
        execute(getApi().getZcValueAndZcNumber(token), new ProgressSubscriber<>(resultResponse -> {
            if (resultResponse.getCode()==0) {
                showUserView.toMainActivity(0, resultResponse.getData());
            }
        }, context));
    }

    public void getAllWailDealList(String token) {
        execute(getApi().getAllWailDealList(token), new ProgressSubscriber<>(resultResponse -> {
            if (resultResponse.getCode()==0) {
                showUserView.toMainActivity(1, resultResponse.getData());
            }
        }, context));
    }

    public void getAllBranchDeptList(String token) {
        execute(getApi().getAllBranchDeptList(token), new ProgressSubscriber<>(resultResponse -> {
            if (resultResponse.getCode()==0) {
                showUserView.toMainActivity(2, resultResponse.getData());
            }
        }, context));
    }

    public void getAllManagerDeptList(String token) {
        execute(getApi().getAllManagerDeptList(token), new ProgressSubscriber<>(resultResponse -> {
            if (resultResponse.getCode()==0) {
                showUserView.toMainActivity(3, resultResponse.getData());
            }
        }, context));
    }

}
