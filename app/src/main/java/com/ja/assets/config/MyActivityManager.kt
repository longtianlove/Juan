package com.ja.assets.config

import android.app.Activity
import android.util.Log
import java.util.*

/**
 * Created by zry on 2017/12/16.
 */
class MyActivityManager private constructor() {


    // 单例模式中获取唯一的ActivityManager实例
    companion object {
        fun getActivityManager(): MyActivityManager {
            return Inner.activityManager
        }
    }

    private object Inner {
        val activityManager = MyActivityManager()
    }


    private var activityStack: Stack<Activity>? = null

    // 添加Activity到容器中
    fun addActivity(activity: Activity) {

        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    private fun finishActivity(activity: Activity?) {
        val activity1 :Activity?= activity
        if (activity1 != null) {
            activityStack!!.remove(activity1)
            activity1.finish()
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    fun AppExit() {
        try {
            finishAllActivity()
            System.exit(0)
        } catch (e: Exception) {
            Log.e("TAG", e.message)
            e.printStackTrace()
        }

    }


    /**
     * extends 权限获取的 activity
     */
    fun getPermissionActivityList(): MutableList<Activity> {
        val permissionActivityList: MutableList<Activity> = ArrayList()
        return permissionActivityList
    }

    fun addPermissionActivity(activity: Activity) {
        if (getPermissionActivityList().contains(activity)) {
            getPermissionActivityList().remove(activity)
            getPermissionActivityList().add(activity)
        } else {
            getPermissionActivityList().add(activity)
        }
    }

    fun removePermissionActivity(activity: Activity) {
        if (getPermissionActivityList().contains(activity)) {
            getPermissionActivityList().remove(activity)
        }
    }
}
