package com.ja.assets.ui.activity.inventory

import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.InventoryRecordAdapter
import com.ja.assets.databinding.ActivityInventoryRecordBinding
import com.ja.assets.model.InventoryRecord
import com.ja.assets.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_inventory_record.*

class InventoryRecordActivity : BaseActivity() {
    private var inventoryRecordBinding: ActivityInventoryRecordBinding? = null
    private var inventoryRecordAdapter: InventoryRecordAdapter? = null
    private var inventoryRecordList: MutableList<InventoryRecord>? = null
    override fun getLayoutId(): Int = R.layout.activity_inventory_record

    override fun initView() {
        inventoryRecordBinding = viewDataBinding as ActivityInventoryRecordBinding
    }

    override fun initAdapter() {
        inventoryRecordList = mutableListOf()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, inventorySurplusAssetsRecycler)
        recyclerViewUtilKt.initRecyclerView()
        inventoryRecordAdapter = InventoryRecordAdapter(R.layout.item_inventory_record, inventoryRecordList!!)
        recyclerViewUtilKt.setAdapter(inventoryRecordAdapter!!)
    }

    override fun initData() {

    }
}
