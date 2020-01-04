package  com.fixed.u8.animation

import android.content.Context
import androidx.recyclerview.widget.*
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.flexbox.*
import androidx.recyclerview.widget.LinearSmoothScroller


/**
 * Created by zry on 2017/12/16.
 */
class RecyclerViewUtilKt(private val context: Context, private val recyclerView: RecyclerView) {


    //自动换行列表
    fun initFlexbox() {
        val layoutManager = FlexboxLayoutManager(context)
//        layoutManager.flexDirection = FlexDirection.COLUMN//纵向
        layoutManager.flexDirection = FlexDirection.ROW//横向
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.flexWrap = FlexWrap.WRAP
        layoutManager.alignItems = AlignItems.STRETCH
        layoutManager.justifyContent = JustifyContent.FLEX_START
        recyclerView.layoutManager = layoutManager
        recyclerView.isNestedScrollingEnabled = false //禁止嵌套滑动
    }


    fun setTargetPosition(position: Int) {
        val linearLayoutManager = LinearLayoutManager(context)
        val smoothScroller = object : LinearSmoothScroller(context) {

            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        smoothScroller.targetPosition = position
        recyclerView.layoutManager = linearLayoutManager
        linearLayoutManager.startSmoothScroll(smoothScroller)
    }


    //列表
    fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        //设置增加或删除条目的动画
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false //禁止嵌套滑动
//        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
    }

    //方格
    fun initTableRecyclerView(tableNumber: Int, decorationNumber: Int) {
        val gridLayoutManager = GridLayoutManager(context, tableNumber)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = gridLayoutManager
        //可选）如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true)
        //decorationNumber:间距，tableNumber：列数
        val decoration = SpacesItemDecoration(decorationNumber, tableNumber)
        recyclerView.addItemDecoration(decoration)
        //设置增加或删除条目的动画
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false //禁止嵌套滑动
    }


    //方格
    fun initTable(tableNumber: Int) {
        val gridLayoutManager = GridLayoutManager(context, tableNumber)
        gridLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = gridLayoutManager
        //可选）如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true)
        //设置增加或删除条目的动画
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false //禁止嵌套滑动
    }

    //瀑布流
    fun initCascadeRecyclerView(decorationNumber: Int, staggeredNumber: Int) {
        //设置item之间的间隔
        val decoration = SpacesItemDecoration(decorationNumber, staggeredNumber)
        recyclerView.addItemDecoration(decoration)
        //解决滑动不顺畅的问题
        recyclerView.layoutManager = StaggeredGridLayoutManager(staggeredNumber, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView.isNestedScrollingEnabled = false //禁止嵌套滑动
    }


    fun setAdapter(baseQuickAdapter: BaseQuickAdapter<*, *>) {
        recyclerView.adapter = baseQuickAdapter
    }


}