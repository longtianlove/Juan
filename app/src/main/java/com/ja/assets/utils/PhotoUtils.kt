package com.ja.assets.utils

import android.app.Activity
import android.content.Intent
import me.nereo.multi_image_selector.MultiImageSelectorActivity
import com.yzq.zxinglibrary.common.Constant.REQUEST_IMAGE
import com.ja.assets.MainActivity
import android.R.id
import com.ja.assets.R
import me.nereo.multi_image_selector.MultiImageSelector


class PhotoUtils {
    fun pickImage(activity: Activity, maxNum: Int, requestCode: Int) {
        val selector = MultiImageSelector.create()
        selector.showCamera(true)
        //最大图片数量,最多可以选择9张图片
        selector.count(maxNum)
        if (maxNum == 1) {
            //单选图片
            selector.single()
        } else {
            //多选图片
            selector.multi()
        }
        val imageList: ArrayList<String> = arrayListOf()
        selector.origin(imageList)
        selector.start(activity, requestCode)
    }
}