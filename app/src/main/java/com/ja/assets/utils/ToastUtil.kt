package com.ja.assets.utils


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import com.ja.assets.R
import com.ja.assets.config.MyApplication

/**
 * Created by zry on 2017/12/22.
 */

object ToastUtil {
    @JvmStatic
    fun toast(toastMessage: CharSequence?) {
        Toast.makeText(getContext(), toastMessage, Toast.LENGTH_LONG).show()
    }

    @JvmStatic
    fun getString(stringId: Int): String {
        return getContext().resources.getString(stringId)
    }

    @SuppressLint("InflateParams")
    @JvmStatic
    fun loadingDialog(context: Context): Dialog {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.layout_loading_dialog, null) // 得到加载view
        val layout = v.findViewById(R.id.dialog_view) as LinearLayout // 加载布局
        val loadingDialog = Dialog(context, R.style.loading_dialog) // 创建自定义样式dialog
        loadingDialog.setCancelable(false) // 不可以用"返回键"取消
        loadingDialog.setContentView(
            layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        return loadingDialog
    }

    @JvmStatic
    fun getContext(): Context {
        return MyApplication.mContext()
    }

    @JvmStatic
    fun getInstance(): MyApplication {
        return MyApplication.instance()
    }
}

