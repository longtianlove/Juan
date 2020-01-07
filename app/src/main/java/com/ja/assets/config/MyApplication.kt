package com.ja.assets.config


import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Environment
import com.ja.assets.retrofit.persistentcookiejar.ClearableCookieJar
import com.ja.assets.retrofit.persistentcookiejar.PersistentCookieJar
import com.ja.assets.retrofit.persistentcookiejar.cache.SetCookieCache
import com.ja.assets.retrofit.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


open class MyApplication : Application() {


    /**
     * 线程池
     */
    val cachedThreadPool: ExecutorService = ThreadPoolExecutor(
        8, 8, 0L,
        TimeUnit.MILLISECONDS, LinkedBlockingQueue(100000),
        ThreadPoolExecutor.CallerRunsPolicy()
    )

    @JvmField
    val photoSavePath: String = Environment.getExternalStorageDirectory().absolutePath + "/固定资产/"


    var cookieJar: ClearableCookieJar? = null


    companion object {
        @SuppressLint("StaticFieldLeak")
        private var instance: MyApplication? = null

        fun instance() = instance!!
        @SuppressLint("StaticFieldLeak")
        private var mContext: Context? = null

        fun mContext() = mContext!!
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        instance = this

        cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(instance))

        closeAndroidPDialog()


    }


    /**
     * 此方法用来解决在MIUI 10升级到 Android P 后 每次进入程序都会弹一个提醒弹窗
     */
    private fun closeAndroidPDialog() {
        try {
            val aClass = Class.forName("android.content.pm.PackageParser\$Package")
            val declaredConstructor = aClass.getDeclaredConstructor(String::class.java)
            declaredConstructor.isAccessible = true
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val cls = Class.forName("android.app.ActivityThread")
            val declaredMethod = cls.getDeclaredMethod("currentActivityThread")
            declaredMethod.isAccessible = true
            val activityThread = declaredMethod.invoke(null)
            val mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown")
            mHiddenApiWarningShown.isAccessible = true
            mHiddenApiWarningShown.setBoolean(activityThread, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
