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


        //        //最大图片数量,最多可以选择9张图片
        //        val maxNumInt = maxNum
        //        val selector = MultiImageSelector.create()
        //
        //        Intent intent = new Intent(MainActivity.this, MultiImageSelectorActivity.class);
        //        // 是否显示调用相机拍照
        //        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);


        //        val intent = Intent(activity, MultiImageSelectorActivity::class.java)


        //        Intent intent = new Intent(MainActivity.this, MultiImageSelectorActivity.class)
        //        // 是否显示调用相机拍照
        //        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        //        // 最大图片选择数量
        //        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, maxNum);
        //        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
        //        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
        //        // 默认选择图片,回填选项(支持String ArrayList)
        //        val defaultDataArray: ArrayList<String> = arrayListOf()
        //        intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, defaultDataArray);
        //        activity.  startActivityForResult(intent, requestCode);


        //
        //
        //        selector.showCamera(true)
        //        selector.count(maxNumInt)
        //
        //        if (maxNum == 1) {
        //            //单选图片
        //            selector.single()
        //        } else {
        //            //多选图片
        //            selector.multi()
        //        }
        //        val imageList: ArrayList<String> = arrayListOf()
        //        selector.origin(imageList)
        //        selector.start(activity, requestCode)
    }
}