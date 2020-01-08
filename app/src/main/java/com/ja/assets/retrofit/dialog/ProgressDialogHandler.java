package com.ja.assets.retrofit.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ja.assets.utils.ToastUtil;


/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/12  15:02
 */

public class ProgressDialogHandler extends Handler {
	public static final int SHOW_PROGRESS_DIALOG = 1;
	public static final int DISMISS_PROGRESS_DIALOG = 2;
	
	private Dialog loadingDialog;
	
	private Context context;
	private boolean cancelable;
	private ProgressCancelListener mProgressCancelListener;
	
	public ProgressDialogHandler(Context context, ProgressCancelListener mProgressCancelListener, boolean cancelable) {
		super();
		this.context = context;
		this.mProgressCancelListener = mProgressCancelListener;
		this.cancelable = cancelable;
	}
	
	private void initProgressDialog() {
		if (loadingDialog == null) {
			loadingDialog = ToastUtil.loadingDialog(context);
			
			loadingDialog.setCancelable(cancelable);
			
			if (cancelable) {
				loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
					@Override
					public void onCancel(DialogInterface dialogInterface) {
						mProgressCancelListener.onCancelProgress();
					}
				});
			}
			
			if (!loadingDialog.isShowing()) {
				loadingDialog.show();
			}
		}
	}
	
	private void dismissProgressDialog() {
		try {
			if (loadingDialog != null) {
				loadingDialog.dismiss();
				loadingDialog = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("TAG", e.getMessage().toString());
		}
		
	}
	
	@Override
	public void handleMessage(Message msg) {
		switch (msg.what) {
			case SHOW_PROGRESS_DIALOG:
				initProgressDialog();
				break;
			case DISMISS_PROGRESS_DIALOG:
				dismissProgressDialog();
				break;
			default:
				break;
		}
	}
}
