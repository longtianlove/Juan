package com.ja.assets.ui.activity.purchase

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import com.fixed.u8.animation.RecyclerViewUtilKt
import com.ja.assets.R
import com.ja.assets.adapter.PurchaseApplyAdapter
import com.ja.assets.databinding.ActivityPurchaseApplyBinding
import com.ja.assets.model.AssetsInfo
import com.ja.assets.ui.base.BaseActivity
import com.ja.assets.utils.view.BackDialog
import kotlinx.android.synthetic.main.activity_purchase_apply.*
import kotlinx.android.synthetic.main.common_title.*


class PurchaseApplyActivity : BaseActivity() {
    private var purchaseApplyBinding: ActivityPurchaseApplyBinding? = null
    private var purchaseApplyAdapter: PurchaseApplyAdapter? = null
    private var assetsInfoList: MutableList<AssetsInfo>? = null


    override fun getLayoutId(): Int = R.layout.activity_purchase_apply

    override fun initView() {
        commonLeftLinearLayout.setOnClickListener { finish() }
        titleCenterText.setText(R.string.purchaseApply)
        titleRightText.setText(R.string.addAssets)
        purchaseApplyBinding = viewDataBinding as ActivityPurchaseApplyBinding
    }

    override fun initAdapter() {
        assetsInfoList = mutableListOf()
        setRecycler()
        val recyclerViewUtilKt = RecyclerViewUtilKt(this, purchaseApplyRecycler)
        recyclerViewUtilKt.initRecyclerView()
        purchaseApplyAdapter = PurchaseApplyAdapter(R.layout.item_purachse_apply_layout, assetsInfoList!!)
        recyclerViewUtilKt.setAdapter(purchaseApplyAdapter!!)
        purchaseApplyAdapter?.setOnItemClickListener { adapter, view, position ->
            val intent = Intent(this, PurchaseNewAddActivity::class.java)
            intent.putExtra("assetsInfo", assetsInfoList!![position])
            intent.putExtra("position", position)
            startActivityForResult(intent, 1)
        }
        purchaseApplyAdapter?.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.itemPurchaseApplyDelete -> {
                    assetsInfoList!!.removeAt(position)
                    purchaseApplyAdapter?.notifyDataSetChanged()
                    setRecycler()
                }
                R.id.itemPurchaseApplyEdit -> {
                }
                R.id.itemPurchaseApplyUnAgree -> {
                }
                R.id.itemPurchaseApplyAgree -> {
                }
            }
        }
    }


    override fun initData() {
        titleRightText.setOnClickListener {
            val intent = Intent(this, PurchaseNewAddActivity::class.java)
            intent.putExtra("assetsInfo", AssetsInfo("", "", "", 0, "", 0, "", "", "", ""))
            intent.putExtra("position", assetsInfoList!!.size)
            startActivityForResult(intent, 0)
        }
        submitAuditBtn.setOnClickListener {
            toast("点击事件")
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }
        when (requestCode) {
            0 -> {
                val assetsInfo: AssetsInfo = data.getSerializableExtra("assetsInfo") as AssetsInfo
                val position: Int = data.getIntExtra("position", 0)
                assetsInfoList!!.add(assetsInfo)
                purchaseApplyAdapter?.notifyDataSetChanged()
                setRecycler()
            }
            1 -> {
                val assetsInfo: AssetsInfo = data.getSerializableExtra("assetsInfo") as AssetsInfo
                val position: Int = data.getIntExtra("position", 0)
                assetsInfoList!!.set(position, assetsInfo)
                purchaseApplyAdapter?.notifyDataSetChanged()
                setRecycler()
            }
        }
    }


    fun setRecycler() {
        if (assetsInfoList!!.size == 0) {
            noAssetsLinear.visibility = View.VISIBLE
            purchaseApplyRecycler.visibility = View.GONE
        } else {
            noAssetsLinear.visibility = View.GONE
            purchaseApplyRecycler.visibility = View.VISIBLE
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0) {
            backDialog()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun backDialog() {
        BackDialog(this).setListener(object : BackDialog.DateListener {
            override fun handleResult() {
                finish()
            }
        })
    }

}
