package com.ja.assets.ui.activity.main

import android.content.Intent
import android.graphics.Color
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import com.fixed.u8.animation.RecyclerViewUtilKt
import com.fixed.u8.ui.base.BaseFragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.ja.assets.MainActivity
import com.ja.assets.R
import com.ja.assets.adapter.HomeAdapter
import com.ja.assets.databinding.FragmentHomeLayoutBinding
import com.ja.assets.model.HomePage01
import com.ja.assets.ui.activity.purchase.PurchaseApplyActivity
import com.ja.assets.utils.ACacheUtil
import com.ja.assets.utils.HomePageList

class HomeFragment : BaseFragment() {

    private var mainActivity: MainActivity? = null
    private var homeBinding: FragmentHomeLayoutBinding? = null
    private var homeAdapter: HomeAdapter? = null

    override fun setOnCreate() {
        mainActivity = activity as MainActivity
    }

    override fun getLayoutId(): Int = R.layout.fragment_home_layout

    override fun initDataView() {
        homeBinding = fragmentDataBinding as FragmentHomeLayoutBinding
        initAdapter()

        initView()
    }

    private fun initAdapter() {
        val recyclerViewUtilKt = RecyclerViewUtilKt(mainActivity!!, homeBinding!!.homeFunctionModel)
        recyclerViewUtilKt.initTable(3)
        val homeList: MutableList<HomePage01> = HomePageList().getHomePageList01(ACacheUtil.getUserInfo())
        homeAdapter = HomeAdapter(mainActivity!!, R.layout.item_fragment_home_layout, homeList)
        recyclerViewUtilKt.setAdapter(homeAdapter!!)
        homeAdapter?.setOnItemClickListener { adapter, view, position ->
            when (position) {
                0 -> {
                    val intent = Intent(mainActivity!!, PurchaseApplyActivity::class.java)
                    startActivity(intent)
                }
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
                4 -> {
                }
                5 -> {
                }
                6 -> {
                }
                7 -> {
                }
                8 -> {
                }
                9 -> {
                }
                10 -> {
                }
                11 -> {
                }
                12 -> {
                }
                13 -> {
                }
                14 -> {
                }
                15 -> {
                }
            }
        }
    }

    private fun initView() {

        homeBinding!!.homeChat1.setUsePercentValues(true)
        homeBinding!!.homeChat1.getDescription().setEnabled(false)
        homeBinding!!.homeChat1.setExtraOffsets(5f, 10f, 5f, 5f)
        homeBinding!!.homeChat1.setDragDecelerationFrictionCoef(0.95f)
        homeBinding!!.homeChat1.setCenterText(generateCenterSpannableText())
        homeBinding!!.homeChat1.setDrawHoleEnabled(true)
        homeBinding!!.homeChat1.setHoleColor(Color.WHITE)
        homeBinding!!.homeChat1.setTransparentCircleColor(Color.WHITE)
        homeBinding!!.homeChat1.setTransparentCircleAlpha(110)
        homeBinding!!.homeChat1.setHoleRadius(58f)
        homeBinding!!.homeChat1.setTransparentCircleRadius(61f)
        homeBinding!!.homeChat1.setDrawCenterText(true)
        homeBinding!!.homeChat1.setRotationAngle(0f)
        homeBinding!!.homeChat1.setRotationEnabled(true)
        homeBinding!!.homeChat1.setHighlightPerTapEnabled(true)

        homeBinding!!.homeChat1.setDrawEntryLabels(false)

        val legend: Legend = homeBinding!!.homeChat1.getLegend()
        legend.isEnabled = false
        homeBinding!!.homeChat1.setEntryLabelColor(Color.WHITE)
        homeBinding!!.homeChat1.setEntryLabelTextSize(12f)
        setData()
    }

    private fun setData() {

        val entries: ArrayList<PieEntry> = arrayListOf()
        entries.add(PieEntry(146 / 274.toFloat()))
        entries.add(PieEntry(76 / 274.toFloat()))
        entries.add(PieEntry(28 / 274.toFloat()))
        entries.add(PieEntry(24 / 274.toFloat()))
        val dataSet = PieDataSet(entries, "")

        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 5f
        dataSet.selectionShift = 5f
        val colors = ArrayList<Int>()
        colors.add(Color.rgb(71, 173, 247))
        colors.add(Color.rgb(39, 229, 153))
        colors.add(Color.rgb(93, 112, 146))
        colors.add(Color.rgb(255, 191, 0))
        dataSet.colors = colors
        val data = PieData(dataSet)

        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter())

        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        homeBinding!!.homeChat1.setData(data)
        homeBinding!!.homeChat1.highlightValues(null)
        homeBinding!!.homeChat1.invalidate()
    }

    private fun generateCenterSpannableText(): SpannableString {
        val s = SpannableString("管理部门\n资产数量")
        s.setSpan(RelativeSizeSpan(0.7f), 0, 8, 0)
        return s
    }
}