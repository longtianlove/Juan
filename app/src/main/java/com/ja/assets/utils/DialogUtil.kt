package com.ja.assets.utils

import android.app.Activity
import android.app.Dialog
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.ja.assets.R


/**
 * @author zry
 * @date 2017/10/19
 * 可能会多次调用的弹框
 */

class DialogUtil(activity: Activity, layoutId: Int, boolean: Boolean, gravity: Int, x: Int, y: Int) {
    var dialog: Dialog? = null
    var view: View? = null

    init {
        dialog = Dialog(activity, R.style.dialogStyle)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
         //触摸弹框四周，即可关闭弹框
        //dialog?.setCanceledOnTouchOutside(true)
        //禁止触摸即可关闭dialog
        dialog?.setCanceledOnTouchOutside(false)
        //禁用返回键
        dialog?.setOnKeyListener { dialog, keyCode, event -> keyCode == KeyEvent.KEYCODE_BACK && event!!.getRepeatCount() == 0 }
        val dialogWindow = dialog?.window
        val layoutParams: WindowManager.LayoutParams? = dialogWindow?.attributes
        if (boolean) {
            dialogWindow?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
            dialogWindow?.setGravity(gravity)

//        dialogWindow?.setGravity(Gravity.LEFT or Gravity.TOP)
            //显示的坐标
            layoutParams?.x = x
            layoutParams?.y = y
            val width = activity.resources.getDimensionPixelOffset(R.dimen.margin_240)
            val height = activity.resources.getDimensionPixelOffset(R.dimen.margin_270)
            //dialog的大小
            layoutParams?.width = width
            layoutParams?.height = height
        }
        val width = activity.resources.getDimensionPixelOffset(R.dimen.margin_240)
        val height = activity.resources.getDimensionPixelOffset(R.dimen.margin_240)
        //dialog的大小
        layoutParams?.width = width
        layoutParams?.height = height
        dialogWindow?.attributes = layoutParams
        //透明度
//        layoutParams?.alpha = 0.9f
        view = activity.layoutInflater.inflate(layoutId, null)
        dialog?.setContentView(view!!)
        dialog?.show()
    }

    /**
     * 关闭dialog
     */
    fun dialogDismiss() {
        dialog?.dismiss()
    }

}
