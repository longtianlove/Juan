package com.ja.assets.utils.view

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow


abstract class BasePopup(context: Context, layoutID: Int, linearLayout: LinearLayout) {

    var contentView: View? = null
    var popupWindow: PopupWindow? = null

    init {
        contentView = LayoutInflater.from(context).inflate(layoutID, null, false)
        contentView?.setOnClickListener { popupWindow?.dismiss() }
        popupWindow = PopupWindow(contentView!!)
        popupWindow?.width = ViewGroup.LayoutParams.MATCH_PARENT
        popupWindow?.height = ViewGroup.LayoutParams.MATCH_PARENT

        popupWindow?.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        popupWindow?.isFocusable = true
        popupWindow?.isOutsideTouchable = false// 设置允许在外点击消失
        val dw = ColorDrawable(0x50000000)
        popupWindow?.setBackgroundDrawable(dw)

        showAsDropDown(popupWindow!!, linearLayout, 0, linearLayout.height)
    }

    private fun showAsDropDown(pw: PopupWindow, anchor: View, xoff: Int, yoff: Int) {
        when {
            Build.VERSION.SDK_INT >=28->{
                val visibleFrame = Rect()
                anchor.getGlobalVisibleRect(visibleFrame)
                val height: Int = anchor.resources.displayMetrics.heightPixels - visibleFrame.bottom+ anchor.resources.getDimensionPixelSize(anchor.resources.getIdentifier("status_bar_height", "dimen", "android"))
                pw.height = height
                pw.showAsDropDown(anchor, xoff, yoff)
            }
            Build.VERSION.SDK_INT in 26..27 -> {
                val visibleFrame = Rect()
                anchor.getGlobalVisibleRect(visibleFrame)
                val height: Int = anchor.resources.displayMetrics.heightPixels - visibleFrame.bottom + anchor.resources.getDimensionPixelSize(anchor.resources.getIdentifier("status_bar_height", "dimen", "android"))
                pw.height = height
                pw.showAsDropDown(anchor, xoff, yoff)
            }
            Build.VERSION.SDK_INT in 24..25 -> {
                val visibleFrame = Rect()
                anchor.getGlobalVisibleRect(visibleFrame)
                val height: Int = anchor.resources.displayMetrics.heightPixels - visibleFrame.bottom
                pw.height = height
                pw.showAsDropDown(anchor, xoff, yoff)
            }
            else -> pw.showAsDropDown(anchor, xoff, yoff)
        }
    }

}