package com.ja.assets.ui.activity.purchase

import android.app.Activity
import android.content.Intent
import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.DepartmentAdapter
import com.ja.assets.databinding.ActivityDepartmentBinding
import com.ja.assets.model.AdminBean
import com.ja.assets.ui.base.BaseActivity
import com.ja.assets.utils.HomePageList
import com.ja.assets.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_department.*
import kotlinx.android.synthetic.main.common_title.*

class DepartmentActivity : BaseActivity() {
    private var departmentBinding: ActivityDepartmentBinding? = null
    private var departmentAdapter: DepartmentAdapter? = null
    private var adminList: MutableList<AdminBean>? = null
    private var type=""
    override fun getLayoutId(): Int = R.layout.activity_department

    override fun initView() {
        type=intent.getStringExtra("type")!!
        when(type){
            "0"->titleCenterText.text=ToastUtil.getString(R.string.selectDepartment)
            "1"->titleCenterText.text=ToastUtil.getString(R.string.selectBranch)
            else->titleCenterText.text=ToastUtil.getString(R.string.selectDepartmentOrBranch)
        }
        departmentBinding = viewDataBinding as ActivityDepartmentBinding

    }

    override fun initAdapter() {
        adminList = mutableListOf()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, departmentRecycler)
        recyclerViewUtilKt.initRecyclerView()
        departmentAdapter = DepartmentAdapter(R.layout.item_admin_popup_layout, adminList!!)
        recyclerViewUtilKt.setAdapter(departmentAdapter!!)
        departmentAdapter?.setOnItemClickListener { adapter, view, position ->

            val intent = Intent()
            intent.putExtra("adminBean", adminList!![position])
            this.setResult(Activity.RESULT_OK, intent)
            // 关闭Activity
            this.finish()
        }
    }

    override fun initData() {
        adminList!!.clear()
        adminList!!.addAll(HomePageList().getAdaminList())
        departmentAdapter?.notifyDataSetChanged()
    }
}