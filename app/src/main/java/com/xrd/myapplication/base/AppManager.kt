package com.xrd.myapplication.base

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

/**
 * Created by WJ on 2019/1/25.
 */
class AppManager private constructor() {
    private var mStock = Stack<Activity>()

    //单例模式
    companion object {
        val instance: AppManager by lazy { AppManager() }
    }

    //添加到栈中
    fun addActivity(activity: Activity) {
        mStock.add(activity)
    }

    //finishAcitity
    fun removeActivity(activity: Activity) {
        mStock.remove(activity)
    }

    //clear activity
    fun clearAllActicity() {
        mStock.forEach {
            it.finish();
        }
        mStock.clear()
    }

    //获取当前顶部acitivity
    fun getCurrnentActivity(): Activity {
        return mStock.lastElement()
    }

    @SuppressLint("MissingPermission")
    //退出应用
    fun exitApp(context: Context) {
        clearAllActicity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)
    }
}