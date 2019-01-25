package com.xrd.myapplication.base

import com.alibaba.android.arouter.launcher.ARouter
import com.gfd.common.common.BaseApplication
import com.tencent.smtt.sdk.QbSdk
import com.xrd.myapplication.BuildConfig

/**
 * Created by WJ on 2019/1/25.
 */
class YYApplication:BaseApplication(){
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            // 打印日志
            ARouter.openLog()
            //开启调试模式
            ARouter.openDebug()
        }
        ARouter.init(this)
        QbSdk.initX5Environment(this,null)
    }
}