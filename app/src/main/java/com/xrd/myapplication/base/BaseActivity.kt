package com.xrd.myapplication.base

import android.app.ActivityManager
import android.app.AppOpsManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by WJ on 2019/1/25.
 */

abstract class BaseActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        AppManager.instance.addActivity(this)
        initView()
        setListener()
        getData()
    }

    open fun getData(){

    }

    open fun setListener(){

    }

    abstract fun initView()

    abstract fun getLayoutId(): Int
    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.removeActivity(this);
    }
}