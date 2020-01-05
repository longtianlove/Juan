package com.ja.assets.ui.activity.other

import com.ja.assets.R
import com.ja.assets.databinding.ActivitySweepCodeBinding
import com.ja.assets.ui.base.BaseActivity
import com.ja.assets.utils.ToastUtil
import kotlinx.android.synthetic.main.common_title.*

class SweepCodeActivity : BaseActivity() {

    private var sweepCodeBinding:ActivitySweepCodeBinding?=null
    private var traceCode:String=""
    override fun getLayoutId(): Int {
        return R.layout.activity_sweep_code
    }

    override fun initView() {
        titleCenterText.text=ToastUtil.getString(R.style.assetsInfo)
        commonLeftLinearLayout.setOnClickListener { finish() }
        sweepCodeBinding=viewDataBinding as ActivitySweepCodeBinding
        traceCode=intent.getStringExtra("traceCode")!!
    }

    override fun initAdapter() =Unit

    override fun initData() {
        //获取资产详情

    }
}
