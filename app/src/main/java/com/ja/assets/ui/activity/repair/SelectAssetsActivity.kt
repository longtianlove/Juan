package com.ja.assets.ui.activity.repair

import android.app.Activity
import android.content.Intent
import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.SelectAssetsAdapter
import com.ja.assets.databinding.ActivitySelectAssetsBinding
import com.ja.assets.model.AssetsInfo
import com.ja.assets.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_select_assets.*

class SelectAssetsActivity : BaseActivity() {
    private var assetsInfoList: MutableList<AssetsInfo>? = null
    private var selectAssetsBinding: ActivitySelectAssetsBinding? = null
    private var selectAssetsAdapter: SelectAssetsAdapter? = null
    override fun getLayoutId(): Int = R.layout.activity_select_assets

    override fun initView() {
        selectAssetsBinding = viewDataBinding as ActivitySelectAssetsBinding
    }

    override fun initAdapter() {
        assetsInfoList = mutableListOf()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, selectAssetsRecycler)
        recyclerViewUtilKt.initRecyclerView()
        selectAssetsAdapter = SelectAssetsAdapter(R.layout.item_select_assets, assetsInfoList!!)
        //上拉加载
        selectAssetsAdapter?.setOnLoadMoreListener({ getAssetsList() }, selectAssetsRecycler)
        selectAssetsAdapter?.openLoadAnimation()
        recyclerViewUtilKt.setAdapter(selectAssetsAdapter!!)
        selectAssetsAdapter?.setOnItemClickListener { adapter, view, position ->
            val intent = Intent()
            intent.putExtra("assetsInfo", assetsInfoList!![position])
            this.setResult(Activity.RESULT_OK, intent)
            // 关闭Activity
            this.finish()
        }
    }

    override fun initData() {

    }

    fun getAssetsList() {

    }
}