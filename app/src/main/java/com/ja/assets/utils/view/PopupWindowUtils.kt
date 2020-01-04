package com.ja.assets.utils.view

import android.app.Activity
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.PopupWindow


/**
 * Created by zry on 2017/12/15.
 */
class PopupWindowUtils(private val context: Activity, private val linearLayout: ViewGroup, private val layoutID: Int, private val style: Int) {
    private var popupWindow: PopupWindow? = null

    /**
     * 底部弹框
     */
    fun popupWindow(): PopupWindow {
        val parent = (context.findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0)
        val popView = View.inflate(context, layoutID, null)

        val width = WindowManager.LayoutParams.MATCH_PARENT
        val height = WindowManager.LayoutParams.MATCH_PARENT

        popupWindow = PopupWindow(popView, width, height)
        popupWindow?.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        popupWindow?.animationStyle = style
        popupWindow?.isFocusable = true
        popupWindow?.isOutsideTouchable = false// 设置允许在外点击消失

        popView?.setOnClickListener { popupWindow!!.dismiss() }
        //设置为半透明
        val dw = ColorDrawable(0x30000000)
        //设置为全透明
        //val dw = ColorDrawable(0x00000000)
        popupWindow?.setBackgroundDrawable(dw)
        popupWindow?.showAtLocation(parent, Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 0)
        return popupWindow as PopupWindow
    }


    /**
     * 顶部弹框
     */
    fun showPopupWindow(otherHeight: Int): PopupWindow {
        val mLayoutInflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupWindowView = mLayoutInflater.inflate(layoutID, null)
        val width = context.resources.displayMetrics.widthPixels
        val height = context.resources.displayMetrics.heightPixels

        popupWindow = PopupWindow(popupWindowView, width, height - otherHeight)
        popupWindow?.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//        popupWindow = PopupWindow(popupWindowView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        popupWindow?.isFocusable = true
        popupWindow?.isOutsideTouchable = false// 设置允许在外点击消失
        popupWindowView.setOnClickListener { popupWindow?.dismiss() }
        val dw = ColorDrawable(0x30000000)
        popupWindow?.setBackgroundDrawable(dw)
        val location = IntArray(2)
        linearLayout.getLocationOnScreen(location)
        //居于空间的正下方
        popupWindow?.showAsDropDown(linearLayout, 0, 0)

        return popupWindow as PopupWindow
    }
}


