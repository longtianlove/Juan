package com.ja.assets.utils.view

import android.app.Activity
import android.view.View
import com.ja.assets.R
import com.ja.assets.databinding.DialogBackLayoutBinding
import com.ja.assets.listener.HandlerClickListener

class BackDialog(val activity: Activity) : HandlerClickListener {


    private var dialogUtil: DialogBindingUtil? = null
    private var dialogBackBinding: DialogBackLayoutBinding? = null


    init {
        dialogUtil = DialogBindingUtil(activity, R.layout.dialog_back_layout)
        dialogBackBinding = dialogUtil!!.dialogBinding as DialogBackLayoutBinding
        dialogBackBinding?.click = this
    }

    override fun handlerClick(view: View) {
        when (view.id) {
            R.id.dialogBackCancel -> {
                if (listener != null) {
                    listener!!.handleResult()
                }
                dialogUtil!!.dialogDismiss()
            }
            R.id.dialogBackConfirm -> {
                dialogUtil!!.dialogDismiss()

            }
            R.id.dialogBackDeleteImg -> {
              dialogUtil!!.dialogDismiss()
            }
        }
    }

    private var listener: DateListener? = null

    interface DateListener {
        fun handleResult()
    }

    fun setListener(listener: DateListener): BackDialog {
        this.listener = listener
        return this
    }
}