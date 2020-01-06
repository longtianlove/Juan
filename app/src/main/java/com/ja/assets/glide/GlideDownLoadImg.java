package com.ja.assets.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.ja.assets.config.MyApplication;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 19428 on 2017/6/26.
 */

public class GlideDownLoadImg {

//    //保存图片
//    public void savePicture(Context context, final String fileName, String url) {
//        Glide.with(context).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
//            @Override
//            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//                try {
//                    saveFileToSD(fileName, Bitmap2Bytes(resource));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

//
//    //往SD卡写入文件的方法
//    private void saveFileToSD(String filename, byte[] bytes) throws Exception {
//        //如果手机已插入sd卡,且app具有读写sd卡的权限
//        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//            String filePath = MyApplication.Companion.instance().photoSavePath;
//            File dir1 = new File(filePath);
//            if (!dir1.exists()) {
//                dir1.mkdirs();
//            }
//            filename = filePath + "/" + filename;
//            //这里就不要用openFileOutput了,那个是往手机内存中写数据的
//            FileOutputStream output = new FileOutputStream(filename);
//            output.write(bytes);
//            //将bytes写入到输出流中
//            output.close();
//            //关闭输出流
//        }
//    }

    //将bitmap转成byte数组
    private byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);//png类型
        return byteArrayOutputStream.toByteArray();
    }

}
