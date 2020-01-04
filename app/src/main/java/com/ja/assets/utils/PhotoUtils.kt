package com.ja.assets.utils

import android.app.Activity
import com.yzq.zxinglibrary.common.Constant.REQUEST_IMAGE
import me.nereo.multi_image_selector.MultiImageSelector

class PhotoUtils {
    fun pickImage(activity: Activity, maxNum: Int) {
        //最大图片数量,最多可以选择9张图片
        val maxNumInt = maxNum
        val selector = MultiImageSelector.create()
        selector.showCamera(true)
        selector.count(maxNumInt)

        if (maxNum == 1) {
            //单选图片
            selector.single()
        } else {
            //多选图片
            selector.multi()
        }
        val imageList: ArrayList<String> = arrayListOf()
        selector.origin(imageList)
        selector.start(activity, REQUEST_IMAGE)
    }
}