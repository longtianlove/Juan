package com.ja.assets.retrofit;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author ray
 * @date 2018/3/22
 */

public interface ApiSupport {
	/**
	 * 获取动态API对象
	 */
	default ApiDynamic getApi() {
		return  RetrofitClient.retrofit;
	}
	
	/**
	 * 执行动态API调用
	 */
//	default <T> void execute(Observable<T> api, final Action1<? super T> callback) {
//		execute(api, callback, this::requestError);
//	}
	
	/**
	 * API请求，直接返回一个response实体对象
	 */
	default <T> void execute(Observable<T> api, Subscriber<T> subscriber) {
		if (api == null) {
			new RuntimeException("无效API请求").printStackTrace();
			return;
		}
		api.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(subscriber);
	}
	
	/**
	 * 使用okhttp的GET请求，返回的是字符串
	 */
	default void get(String url, final Action1<String> callback) {
		Request request = new Request.Builder().url(url).build();
		RetrofitClient.okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(@NonNull Call call, @NonNull IOException e) {
				Log.e("error", "请求失败" + url);
			}
			
			@Override
			public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
				callback.call(response.body().string());
			}
		});
	}
	
	/**
	 * 使用okhttp的POST请求
	 *
	 * @param url      请求地址
	 * @param params   请求参数
	 * @param callback 请求的回调
	 */
	default void post(String url, Map<String, String> params, final Action1<String> callback) {
		FormBody.Builder body = new FormBody.Builder();
		for (String key : params.keySet()) {
			body.add(key, params.get(key));
		}
		Request request = new Request.Builder().post(body.build()).url(url).build();
		RetrofitClient.okHttpClient.newCall(request).enqueue(new Callback() {
			@Override
			public void onFailure(@NonNull Call call, @NonNull IOException e) {
				Log.e("error", "请求失败" + url);
			}
			
			@Override
			public void onResponse(@NonNull Call call, @NonNull Response response) throws
				IOException {
				callback.call(response.body().string());
			}
		});
	}
	
	default void requestError(Throwable e) {
		e.printStackTrace();
		Log.e("error", e.getMessage());
	}
}
