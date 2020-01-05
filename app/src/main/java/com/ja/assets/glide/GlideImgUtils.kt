package com.ja.assets.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ja.assets.glide.GlideLoadPhoto

object GlideImgUtils {

    //加载普通图片
    @JvmStatic
    fun loadImage(context: Context, URL: Any, defaultImg: Int, mImageView: ImageView) {
        Glide.with(context).load(URL).apply(GlideLoadPhoto().GlideLoadImg(defaultImg, defaultImg)).into(mImageView)
    }

    //加载圆角图片
    @JvmStatic
    fun loadRoundImage(context: Context, URL: Any, defaultImg: Int, mImageView: ImageView, roundDp: Int) {
        Glide.with(context).load(URL).apply(GlideLoadPhoto().GlideRound(defaultImg, defaultImg, roundDp)).into(mImageView)
    }

    //加载圆形图片
    @JvmStatic
    fun loadCircleImage(context: Context, URL: Any, defaultImg: Int, mImageView: ImageView) {
        Glide.with(context).load(URL).apply(GlideLoadPhoto().GlideCircle(defaultImg, defaultImg)).into(mImageView)
    }

}