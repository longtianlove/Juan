package com.ja.assets.glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by 19428 on 2017/6/29.
 */

public class GlideLoadPhoto {


    RequestOptions options;

    //加载圆形图片
    public RequestOptions GlideCircle(int placeholderImg, int errorImg) {
        return options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholderImg)
                .error(errorImg)
                .priority(Priority.HIGH)
                .transform(new GlideCircleTransform());
    }


    //加载圆角图片
    public RequestOptions GlideRound(int placeholderImg, int errorImg, int roundDp) {
        return options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholderImg)
                .error(errorImg)
                .priority(Priority.HIGH)
                .transform(new GlideRoundTransform(roundDp));
    }

    //加载普通图片
    public RequestOptions GlideLoadImg(int placeholderImg, int errorImg) {
        return options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholderImg)
                .error(errorImg)
                .priority(Priority.HIGH);
    }
}
