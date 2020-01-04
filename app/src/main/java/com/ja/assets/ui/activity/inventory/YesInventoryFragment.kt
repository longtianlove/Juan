package com.ja.assets.ui.activity.inventory

import com.fixed.u8.ui.base.BaseFragment
import com.ja.assets.R

class YesInventoryFragment:BaseFragment(){
    private var inventoryActivity: InventoryActivity? = null
    override fun setOnCreate() {
        inventoryActivity = activity as InventoryActivity
    }

    override fun getLayoutId(): Int = R.layout.fragment_yes_inventory

    override fun initDataView() {

    }
}