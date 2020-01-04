package com.ja.assets.utils.view;

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.AdminPopupAdapter
import com.ja.assets.databinding.AdminItemLayoutBinding
import com.ja.assets.model.AdminBean

class AdminDepartmentPopup(context: Activity, linearLayout: ViewGroup) {

    private var itemAdminList: MutableList<AdminBean> = mutableListOf(AdminBean(1,"综合办"),AdminBean(2,"科技部"),
        AdminBean(3,"运营部"),AdminBean(4,"保卫部"))
    private var adminPopupAdapter: AdminPopupAdapter? = null

    init {
        val popupWindow = PopupWindowUtils(context, linearLayout, R.layout.admin_item_layout, R.style.AnimBottom).popupWindow()
//        val popupView = popupWindow.contentView
        val popupBinding: AdminItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.admin_item_layout, null, false)
        val recyclerViewUtilKt = RecyclerViewUtilKt(context, popupBinding.adminItemRecycler)
        recyclerViewUtilKt.initRecyclerView()
        adminPopupAdapter = AdminPopupAdapter(R.layout.item_admin_popup_layout, itemAdminList)
        recyclerViewUtilKt.setAdapter(adminPopupAdapter!!)

        adminPopupAdapter?.notifyDataSetChanged()
        adminPopupAdapter?.setOnItemClickListener { adapter, view, position ->
            if (listener != null) {
                listener?.handleResult(itemAdminList[position])
                popupWindow.dismiss()
            }
        }
//        popupBinding.adminItemCancel.setOnClickListener { popupWindow.dismiss() }
    }

    private var listener: AdminDepartmentListener? = null

    interface AdminDepartmentListener {
        fun handleResult(adminBean: AdminBean)
    }

    fun setListener(listener: AdminDepartmentListener): AdminDepartmentPopup {
        this.listener = listener
        return this
    }
}
