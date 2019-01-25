package com.xrd.myapplication

import android.os.Build
import android.view.View
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gfd.common.ui.fragment.BaseFragment
import com.gfd.common.utils.ToastUtils
import com.gfd.provider.router.component.Router
import com.gfd.provider.router.service.CrosstalkService
import com.gfd.provider.router.service.HomeService
import com.gfd.provider.router.service.LiveService
import com.gfd.provider.router.service.MusicService
import com.roughike.bottombar.BottomBar
import com.xrd.myapplication.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by WJ on 2019/1/25.
 */
class MainActivity : BaseActivity() {
    private lateinit var mHomeFragment :BaseFragment
    private lateinit var mMusicFragment :BaseFragment
    private lateinit var mLiveFragment :BaseFragment
    private lateinit var mCrosstalkFragment :BaseFragment
    private val mStack=Stack<BaseFragment>()

    override fun initView() {
        initFragment()
    }

    private fun initFragment() {
        val router = Router.instance
        val homeService = router.getService(HomeService::class.simpleName)
        val musicService = router.getService(MusicService::class.simpleName)
        val liveService = router.getService(LiveService::class.simpleName)
        val crosstalkService = router.getService(CrosstalkService::class.simpleName)
        if(homeService!=null){
             mHomeFragment = (homeService as HomeService).getHomeFragment()
        }
        if(musicService!=null){
             mMusicFragment = (musicService as MusicService).getMusicFragment()
        }
        if(liveService!=null){
             mLiveFragment = (liveService as LiveService).getLiveFragment()
        }
        if(crosstalkService!=null){
             mCrosstalkFragment = (crosstalkService as CrosstalkService).getCrosstalkFragment()
        }
        val bt = supportFragmentManager.beginTransaction()
        bt.add(R.id.fl_container,mHomeFragment)
        bt.add(R.id.fl_container,mMusicFragment)
        bt.add(R.id.fl_container,mLiveFragment)
        bt.add(R.id.fl_container,mCrosstalkFragment)

        mStack.add(mHomeFragment)
        mStack.add(mMusicFragment)
        mStack.add(mLiveFragment)
        mStack.add(mCrosstalkFragment)

        bt.commit()
    }

    override fun getData() {
    }

    override fun setListener() {
        bottomBar.setOnTabSelectListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)////显示状态栏
            when (it) {
                R.id.tab_home -> {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    }
                    switchFragment(0)
                    ToastUtils.instance.showToast("首页")
                }
                R.id.tab_music -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    }
                    switchFragment(1)
                    ToastUtils.instance.showToast("音乐")
                }
                R.id.tab_play -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    }
                    switchFragment(2)
                    ToastUtils.instance.showToast("直播")
                }
                R.id.tab_mine -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    }
                    switchFragment(3)
                    ToastUtils.instance.showToast("相声")
                }
            }
        }
    }

    private fun switchFragment(i: Int) {
        val bt = supportFragmentManager.beginTransaction()
        mStack.forEach {
            bt.hide(it)//隐藏所有fragment
        }
        bt.show(mStack[i])//显示点击的fragment
        bt.commitAllowingStateLoss()
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_main;
    }
}