package com.ja.assets.utils

import android.app.Activity
import android.util.Log
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import top.zibin.luban.Luban
import java.io.File

class LuBanImgUtils (activity: Activity, userHeaderImgPath: String){

    init {
        if (File(userHeaderImgPath).exists()) {
            Luban.get(activity)
                .load(File(userHeaderImgPath))
                .putGear(Luban.THIRD_GEAR)
                .asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { throwable -> throwable.printStackTrace() }
                .onErrorResumeNext { Observable.empty() }
                .subscribe {
                    if (listener != null) {
                        listener!!.handleResult(it)
                    }
                }
        } else {
            Log.e("TAG", "图片不存在")
        }
    }

    private var listener: ImgListener? = null

    interface ImgListener {
        fun handleResult(file: File)
    }

    fun setListener(listener: ImgListener): LuBanImgUtils {
        this.listener = listener
        return this
    }
}