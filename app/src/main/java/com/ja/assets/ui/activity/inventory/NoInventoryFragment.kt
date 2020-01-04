package com.ja.assets.ui.activity.inventory

import com.fixed.u8.animation.RecyclerViewUtilKt
import com.fixed.u8.ui.base.BaseFragment
import com.ja.assets.R
import com.ja.assets.adapter.InventoryAdapter
import com.ja.assets.databinding.FragmentYesInventoryBinding
import com.ja.assets.model.InventoryBean

class NoInventoryFragment : BaseFragment() {
    private var inventoryActivity: InventoryActivity? = null
    private var inventoryBinding:FragmentYesInventoryBinding?=null
    private var inventoryAdapter: InventoryAdapter?=null
    private var inventoryList:MutableList<InventoryBean> ?=null
    override fun setOnCreate() {
        inventoryActivity = activity as InventoryActivity
    }

    override fun getLayoutId(): Int= R.layout.fragment_yes_inventory

    override fun initDataView() {
        inventoryBinding=fragmentDataBinding as FragmentYesInventoryBinding


        initAdapter()
    }

    private fun initAdapter() {
        val recyclerViewUtilKt=RecyclerViewUtilKt(inventoryActivity!!,inventoryBinding!!.inventoryRecycler)
        recyclerViewUtilKt.initRecyclerView()
        inventoryAdapter=InventoryAdapter(R.layout.item_fragment_inventory,inventoryList!!)
        recyclerViewUtilKt.setAdapter(inventoryAdapter!!)
    }
}