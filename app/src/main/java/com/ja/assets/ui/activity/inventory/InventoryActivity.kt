package com.ja.assets.ui.activity.inventory

import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ja.assets.R
import com.ja.assets.adapter.ViewPageManagerAdapter
import com.ja.assets.databinding.ActivityInventoryBinding
import com.ja.assets.listener.HandlerClickListener
import com.ja.assets.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_inventory.*

class InventoryActivity : BaseActivity(), HandlerClickListener {

    private var inventoryBinding: ActivityInventoryBinding? = null
    private var currIndex: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_inventory

    override fun initView() {
        inventoryBinding = viewDataBinding as ActivityInventoryBinding
        inventoryBinding?.click = this
        val fragmentList: ArrayList<Fragment> = arrayListOf(NoInventoryFragment(), NoInventoryFragment())
        val viewPageManagerAdapter = ViewPageManagerAdapter(supportFragmentManager, fragmentList)
        inventoryViewPager.adapter = viewPageManagerAdapter
        inventoryViewPager.addOnPageChangeListener(onPageChangeListener)
    }

    override fun initAdapter() {
    }

    override fun initData() {
    }

    private var onPageChangeListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) = Unit
        override fun onPageSelected(position: Int) = Unit

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            currIndex = position
            setBgColor(currIndex)
        }

    }


    override fun handlerClick(view: View) {
        when (view.id) {
            R.id.inventoryLeftRelative -> {
                inventoryViewPager.currentItem = 0
                setBgColor(currIndex)
            }
            R.id.inventoryRightRelative -> {
                inventoryViewPager.currentItem = 1
                setBgColor(currIndex)
            }
        }
    }


    private fun setBgColor(currentItem: Int) {
        inventoryLeftTV.setTextColor(ContextCompat.getColor(this, R.color.textColor))
        inventoryLeftLine.setBackgroundResource(R.color.white)
        inventoryRightTV.setTextColor(ContextCompat.getColor(this, R.color.textColor))
        inventoryRightLine.setBackgroundResource(R.color.white)
        when (currentItem) {
            0 -> {
                inventoryLeftTV.setTextColor(ContextCompat.getColor(this, R.color.mainBgColor))
                inventoryLeftLine.setBackgroundResource(R.color.mainBgColor)
            }
            1 -> {
                inventoryLeftTV.setTextColor(ContextCompat.getColor(this, R.color.mainBgColor))
                inventoryLeftLine.setBackgroundResource(R.color.mainBgColor)
            }
        }
    }
}
