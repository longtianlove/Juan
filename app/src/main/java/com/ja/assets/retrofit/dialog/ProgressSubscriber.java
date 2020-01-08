package com.ja.assets.retrofit.dialog;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;


import com.ja.assets.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
	
	private SubscriberOnNextListener<T> mListener;
	private Context mContext;
	private ProgressDialogHandler mHandler;
	
	public ProgressSubscriber(SubscriberOnNextListener<T> listener, Context context) {
		this.mListener = listener;
		this.mContext = context;
		mHandler = new ProgressDialogHandler(context, this, true);
	}
	
	private void showProgressDialog() {
		if (mHandler != null) {
			mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
		}
	}
	
	private void dismissProgressDialog() {
		if (mHandler != null) {
			mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
			mHandler = null;
		}
	}
	
	
	/**
	 * 订阅开始时调用
	 * 显示ProgressDialog
	 */
	@Override
	public void onStart() {
		super.onStart();
		showProgressDialog();
	}
	
	@Override
	public void onCompleted() {
		dismissProgressDialog();
//		Toast.makeText(MyApplication.Companion.getInstance().getApplicationContext(), "获取数据完成！", Toast
// .LENGTH_SHORT).show();
	}
	
	@Override
	public void onError(Throwable e) {
		if (e instanceof SocketTimeoutException) {
			Toast.makeText(ToastUtil.getContext(),
				"网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
		} else if (e instanceof ConnectException) {
			Toast.makeText(ToastUtil.getContext(),
				"网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
		} else {
//			Toast.makeText(MyApplication.Companion.getInstance().getApplicationContext(), "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
		dismissProgressDialog();
	}
	
	@Override
	public void onNext(T t) {
		if (mListener != null) {
			mListener.onSuccess(t);
		}
	}
	
	@Override
	public void onCancelProgress() {
		if (!this.isUnsubscribed()) {
			this.unsubscribe();
		}
	}
}
