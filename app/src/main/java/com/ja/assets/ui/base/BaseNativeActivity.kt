package com.fixed.u8.ui.base

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ja.assets.config.MyActivityManager
import com.ja.assets.config.MyApplication
import com.ja.assets.utils.ToastUtil

/**
 * Created by 19428 on 2017/6/4.
 */

abstract class BaseNativeActivity : Activity() {
    var myApplication: MyApplication? = null
    var myActivityManager: MyActivityManager? = null
    var viewDataBinding: ViewDataBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = getDataBinding()

        //透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val window: Window = window
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        myActivityManager = MyActivityManager.getActivityManager()
        myActivityManager?.addActivity(this)
        myActivityManager?.addPermissionActivity(this)
        myApplication = MyApplication.instance()

        initData()
    }



    private fun getDataBinding(): ViewDataBinding {
        return DataBindingUtil.setContentView(this, getLayoutId())
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initData()


    protected fun toast(toastMessage: String) {
        ToastUtil.toast( toastMessage)
    }


    override fun onDestroy() {
        super.onDestroy()
        myActivityManager?.removePermissionActivity(this)
        myActivityManager?.finishActivity()
    }
}
