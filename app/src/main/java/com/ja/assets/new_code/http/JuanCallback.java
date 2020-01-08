package com.ja.assets.new_code.http;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;


import com.ja.assets.new_code.base.BaseBean;
import com.ja.assets.new_code.util.ToastUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by long on 17/4/7.
 */
@SuppressLint("WrongConstant")
public abstract class JuanCallback<T extends BaseBean> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.code() >= 200 && response.code() < 300) {
            T message = response.body();
            onSuccess(response, message);
            return;
        }

        ToastUtil.showTost("网络错误");

        onFail(call, null);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(call, t);
    }

    public abstract void onSuccess(Response<T> response, T message);

    public abstract void onFail(Call<T> call, Throwable t);
}
