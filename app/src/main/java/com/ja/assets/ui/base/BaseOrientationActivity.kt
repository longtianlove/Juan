package com.ja.assets.ui.base

import android.os.Bundle
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ja.assets.config.MyActivityManager
import com.ja.assets.utils.ToastUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

abstract class BaseOrientationActivity : PermissionActivity() , CoroutineScope by MainScope(){
    var myActivityManager: MyActivityManager? = null
    var viewDataBinding: ViewDataBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        viewDataBinding = getDataBinding()

        myActivityManager = MyActivityManager.getActivityManager()
        myActivityManager?.addActivity(this)
        myActivityManager?.addPermissionActivity(this)

        initView()
        initAdapter()
        initData()
    }


    private fun getDataBinding(): ViewDataBinding {
        return DataBindingUtil.setContentView(this, getLayoutId())
    }

    protected abstract fun getLayoutId(): Int
    protected abstract fun initView()
    protected abstract fun initAdapter()
    protected abstract fun initData()


    protected fun toast(toastMessage: String) {
        ToastUtil.toast(toastMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
        myActivityManager?.removePermissionActivity(this)
        myActivityManager?.finishActivity()
    }
}