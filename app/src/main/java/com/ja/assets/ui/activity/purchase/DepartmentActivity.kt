package com.ja.assets.ui.activity.purchase

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.DepartmentAdapter
import com.ja.assets.databinding.ActivityDepartmentBinding
import com.ja.assets.model.DeptBean
import com.ja.assets.model.ResultResponse
import com.ja.assets.retrofit.RetrofitClient
import com.ja.assets.ui.base.BaseActivity
import com.ja.assets.utils.ACacheUtil
import com.ja.assets.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_department.*
import kotlinx.android.synthetic.main.common_title.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DepartmentActivity : BaseActivity() {
    private var departmentBinding: ActivityDepartmentBinding? = null
    private var departmentAdapter: DepartmentAdapter? = null
    private var deptList: MutableList<DeptBean>? = null
    private var type = "0"
    override fun getLayoutId(): Int = R.layout.activity_department

    override fun initView() {
        type = intent.getStringExtra("type")!!
        when (type) {
            "0" -> titleCenterText.text = ToastUtil.getString(R.string.selectAdminDepartment)
            "1" -> titleCenterText.text = ToastUtil.getString(R.string.selectBranch)
            else -> titleCenterText.text = ToastUtil.getString(R.string.selectDepartmentOrBranch)
        }

        commonLeftLinearLayout.setOnClickListener { finish() }
        departmentBinding = viewDataBinding as ActivityDepartmentBinding

    }

    override fun initAdapter() {
        deptList = mutableListOf()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, departmentRecycler)
        recyclerViewUtilKt.initRecyclerView()
        departmentAdapter = DepartmentAdapter(R.layout.item_admin_popup_layout, deptList!!)
        recyclerViewUtilKt.setAdapter(departmentAdapter!!)
        departmentAdapter?.setOnItemClickListener { adapter, view, position ->

            val intent = Intent()
            intent.putExtra("deptBean", deptList!![position])
            this.setResult(Activity.RESULT_OK, intent)
            // 关闭Activity
            this.finish()
        }
    }

    override fun initData() {

        launch {
            val loadingDialog: Dialog = ToastUtil.loadingDialog(this@DepartmentActivity)
            loadingDialog.show()

            when (type) {
                "0" -> {

                    //获取所有的管理部门
                    val result: ResultResponse<MutableList<DeptBean>> =
                        async { RetrofitClient.networkService.getAllManagerDeptList(ACacheUtil.getToken()) }.await()

                    deptList!!.clear()
                    deptList!!.addAll(result.data)
                    departmentAdapter?.notifyDataSetChanged()
                }
                "1" -> {
                    //获取所有的支行和使用部门
                    val result: ResultResponse<MutableList<DeptBean>> =
                        async { RetrofitClient.networkService.getAllBranchDeptList(ACacheUtil.getToken()) }.await()

                    deptList!!.clear()
                    deptList!!.addAll(result.data)
                    departmentAdapter?.notifyDataSetChanged()
                }
            }






            loadingDialog.dismiss()
        }


    }
}