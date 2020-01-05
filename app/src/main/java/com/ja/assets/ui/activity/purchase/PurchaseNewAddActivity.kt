package com.ja.assets.ui.activity.purchase

import android.app.Activity
import android.content.Intent
import android.view.View
import com.ja.assets.R
import com.ja.assets.databinding.ActivityPurchaseNewAddBinding
import com.ja.assets.model.AdminBean
import com.ja.assets.model.AssetsInfo
import com.ja.assets.listener.HandlerClickListener
import com.ja.assets.ui.base.BaseActivity
import com.ja.assets.utils.EditIsCanUseBtnUtils
import com.ja.assets.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_purchase_new_add.*
import kotlinx.android.synthetic.main.common_title.*

class PurchaseNewAddActivity : BaseActivity(), HandlerClickListener {

    private var purchaseNewAddBinding: ActivityPurchaseNewAddBinding? = null
    private var assetsInfo: AssetsInfo? = null
    private var position: Int = 0
    override fun getLayoutId(): Int = R.layout.activity_purchase_new_add

    override fun initView() {
        commonLeftLinearLayout.setOnClickListener { finish() }
        titleCenterText.text = ToastUtil.getString(R.string.purchaseNewAdd)
        purchaseNewAddBinding = viewDataBinding as ActivityPurchaseNewAddBinding
        purchaseNewAddBinding?.click = this

        assetsInfo = intent.getSerializableExtra("assetsInfo") as AssetsInfo
        position = intent.getIntExtra("position", 0)

        purchaseNewAddBinding?.assetsInfoBean = assetsInfo
    }

    override fun initAdapter() {
        purchaseQuantityTV.setText(assetsInfo?.purchaseQuantity.toString())
    }

    override fun initData() {
        EditIsCanUseBtnUtils.getInstance().addEditext(assetsNameET).addEditext(purposeTV).addEditext(purchaseQuantityTV)
            .addButton(purchaseSubmitBtn).setText("提交").setTextNull("提交").setTextFull("提交")
            .setNullbackgroundResource(R.drawable.btn_circular_selector_company_color)
            .setFullbackgroundResource(R.drawable.btn_circular_selector_main_color).build();
    }


    override fun handlerClick(view: View) {
        when (view.id) {
            R.id.departmentNameLinear -> {
                val intent = Intent(this, DepartmentActivity::class.java)
                intent.putExtra("type", "0")
                startActivityForResult(intent, 1)
            }
            R.id.purchaseSubmitBtn -> {

                setAssetsInfo()
            }
        }
    }

    fun setAssetsInfo() {
        assetsInfo!!.purchaseQuantity = purchaseQuantityTV.text.toString().trim().toInt()


        val intent = Intent()
        intent.putExtra("assetsInfo", assetsInfo)
        intent.putExtra("position", position)
        this.setResult(Activity.RESULT_OK, intent)
        // 关闭Activity
        this.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null || resultCode != RESULT_OK) {
            return
        }
        if (requestCode == 1) {
            val adminBean: AdminBean = data.getSerializableExtra("adminBean") as AdminBean
            adminDepartmentTV.text = adminBean.adminName
            assetsInfo!!.departmentName = adminBean.adminName
            assetsInfo!!.departmentId = adminBean.adminId
        }
    }

}
