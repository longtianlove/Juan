package com.ja.assets.new_code.util;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.ja.assets.new_code.view.CustomProgress;


/**
 * Created by long on 2017/4/25.
 */

public class DialogUtil {
    private static CustomProgress mCustomProgress;

    /**
     * 显示带文本的加载进度对话框
     */
    public static void showProgress(Context context, String str) {
        try {
            if ("".equals(str)) {
                str = "";
            }
            if (mCustomProgress == null) {
                mCustomProgress = CustomProgress.show(context, str, false, null);
            } else {
                mCustomProgress.setMessage(str);
                mCustomProgress.show();
            }
        } catch (Exception e) {
            Log.e("showProgress", e.getMessage());
        }
    }

    /**
     * 关掉加载进度对话框
     */
    public static void closeProgress() {
        if (mCustomProgress != null && mCustomProgress.isShowing()) {
            try {//bug fixxed with umeng at 5.0.1 by long
                mCustomProgress.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
            mCustomProgress = null;
        }
    }




}
