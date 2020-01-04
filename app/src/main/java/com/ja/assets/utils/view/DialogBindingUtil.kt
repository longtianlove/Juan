package com.ja.assets.utils.view

import android.app.Activity
import android.app.Dialog
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ja.assets.R


/**
 * @author zry
 * @date 2017/10/19
 * 可能会多次调用的弹框
 */

class DialogBindingUtil(activity: Activity, layoutId: Int) {
    var dialog: Dialog? = null
    var view: View? = null
    var dialogBinding: ViewDataBinding? = null

    init {
        dialog = Dialog(activity, R.style.dialogStyle)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCanceledOnTouchOutside(true)
        val dialogWindow = dialog?.window
        val layoutParams: WindowManager.LayoutParams? = dialogWindow?.attributes
//        val width = activity.resources.getDimensionPixelOffset(R.dimen.margin_437)
//        val height = activity.resources.getDimensionPixelOffset(R.dimen.margin_412)
//        //dialog的大小
//        layoutParams?.width = width
//        layoutParams?.height = height
        layoutParams?.width = ViewGroup.LayoutParams.WRAP_CONTENT
        layoutParams?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialogWindow?.attributes = layoutParams
//        透明度
//        layoutParams?.alpha = 0.9f

        dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(activity), layoutId, null, false)
        dialog?.setContentView(dialogBinding!!.root)
        dialog?.show()
    }

    /**
     * 关闭dialog
     */
    fun dialogDismiss() {
        dialog?.dismiss()
    }

}
