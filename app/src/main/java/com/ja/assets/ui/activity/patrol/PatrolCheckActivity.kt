package com.ja.assets.ui.activity.patrol

import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.PatrolCheckAdapter
import com.ja.assets.databinding.ActivityPatorlCheckBinding
import com.ja.assets.model.PatrolCheck
import com.ja.assets.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_patorl_check.*

class PatrolCheckActivity : BaseActivity() {
    private var patrolCheckBinding: ActivityPatorlCheckBinding? = null
    private var patrolCheckAdapter: PatrolCheckAdapter? = null
    private var patrolCheckList: MutableList<PatrolCheck>? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_patorl_check
    }

    override fun initView() {
        patrolCheckBinding = viewDataBinding as ActivityPatorlCheckBinding
    }

    override fun initAdapter() {
        patrolCheckList = mutableListOf()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, patrolCheckRecycler)
        recyclerViewUtilKt.initRecyclerView()
        patrolCheckAdapter = PatrolCheckAdapter(R.layout.item_inventory_record, patrolCheckList!!);
        recyclerViewUtilKt.setAdapter(patrolCheckAdapter!!)
    }

    override fun initData() {

    }
}