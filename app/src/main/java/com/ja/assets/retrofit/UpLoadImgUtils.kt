package com.ja.assets.retrofit

import android.app.Activity
import android.util.Log
import com.ja.assets.utils.ToastUtil
import com.tsy.sdk.myokhttp.response.RawResponseHandler
import org.json.JSONObject
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import top.zibin.luban.Luban
import java.io.File


class UpLoadImgUtils(private var activity: Activity, private var showUserView: ShowUserView) {

    fun uploadUserImg(userHeaderImgPath: String, dir: String,what:Int) {
        val file1 = File(userHeaderImgPath)
        if (file1.exists()) {
            Luban.get(activity)
                    .load(file1)
                    .putGear(Luban.THIRD_GEAR)
                    .asObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError { throwable -> throwable.printStackTrace() }
                    .onErrorResumeNext { Observable.empty() }
                    .subscribe {
                        RetrofitClient.myOkHttp.upload()
                                .url(RetrofitClient.BASE_URL + "file/Save?")
                                .addParam("dir", dir)
                                .addFile("image", it)
                                //.addFile("image", "asdsda.png", byteContents)    //直接上传File bytes
                                .tag(this)
                                .enqueue(object : RawResponseHandler() {
                                    override fun onSuccess(statusCode: Int, response: String?) {
                                        Log.e("TAGStatusCode", statusCode.toString())
                                        Log.e("TAGResponse", response.toString())
                                        val jsonObject = JSONObject(response)
                                        if (jsonObject.getString("state").toString() == "1") {
                                            ToastUtil.toast("上传成功")
                                            showUserView.toMainActivity(what, jsonObject.getString("filepath"))
                                        } else {
                                            ToastUtil.toast("上传失败")
                                        }
                                    }

                                    override fun onFailure(statusCode: Int, error_msg: String?) {
                                        Log.e("TAGResponse", statusCode.toString())
                                        Log.e("TAGStatusCode", error_msg.toString())
                                    }
                                })
                    }
        }
    }

}