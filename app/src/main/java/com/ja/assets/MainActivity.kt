package com.ja.assets

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentTransaction
import com.ja.assets.databinding.ActivityMainBinding
import com.ja.assets.ui.activity.main.HomeFragment
import com.ja.assets.ui.activity.main.UserFragment
import com.ja.assets.ui.activity.other.SweepCodeActivity
import com.ja.assets.ui.base.BaseActivity


import com.ja.assets.utils.ToastUtil
import com.yzq.zxinglibrary.android.CaptureActivity
import com.yzq.zxinglibrary.common.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var homeFragment: HomeFragment? = null
    private var userFragment: UserFragment? = null
    private val requestBackCode = 0
    private var mainBinding: ActivityMainBinding? = null


    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initView() {
        mainBinding = viewDataBinding as ActivityMainBinding
        mainBinding?.handlerClick = this

        mainHandlerClick(homePageRelative)
    }


    override fun initAdapter() = Unit


    override fun initData() {

    }

    fun mainHandlerClick(view: View) {
        goneFragment()
        selectStyle(view.id)
    }
    private fun selectStyle(linearId: Int) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        when (linearId) {
            R.id.homePageRelative -> {
                setClickColor()
                homeStartImg.setImageResource(R.mipmap.home_tab_select)
                homeStartTV.setTextColor(ContextCompat.getColor(this, R.color.mainBgColor))

                if (homeFragment == null) {
                    homeFragment = HomeFragment()
                    fragmentTransaction.add(R.id.homeFrame, homeFragment!!)
                } else {
                    fragmentTransaction.show(homeFragment!!)
                }
                fragmentTransaction.commit()
            }
            R.id.userInfoRelative -> {
                setClickColor()
                homeEndImg.setImageResource(R.mipmap.home_tab_user_select)
                homeEndTV.setTextColor(ContextCompat.getColor(this, R.color.mainBgColor))
                if (userFragment == null) {
                    userFragment = UserFragment()
                    fragmentTransaction.add(R.id.homeFrame, userFragment!!)
                } else {
                    fragmentTransaction.show(userFragment!!)
                }
                fragmentTransaction.commit()
            }
            R.id.homeTraceCodeLinear -> {
                val intent = Intent(this, CaptureActivity::class.java)
                startActivityForResult(intent, requestBackCode)
            }
        }
    }

    private fun setClickColor() {
        homeStartImg.setImageResource(R.mipmap.home_tab_unselect)
        homeStartTV.setTextColor(ContextCompat.getColor(this, R.color.textColor))
        homeEndImg.setImageResource(R.mipmap.home_tab_user_unselect)
        homeEndTV.setTextColor(ContextCompat.getColor(this, R.color.textColor))
    }


    /**
     * 隐藏所有fragment的方法
     */
    private fun goneFragment() {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment!!)
        }
        if (userFragment != null) {
            fragmentTransaction.hide(userFragment!!)
        }
        fragmentTransaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {
            return
        }
        if (requestCode == requestBackCode && resultCode == RESULT_OK) {
            val resultUrl = data.getStringExtra(Constant.CODED_CONTENT)
            val intent = Intent(this, SweepCodeActivity::class.java)
            intent.putExtra("resultUrl", resultUrl)
            startActivity(intent)
        }
    }


    //退出时的时间
    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event?.repeatCount == 0) {
            exit()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun exit() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            ToastUtil.toast("再按一次退出程序")
            mExitTime = System.currentTimeMillis()
        } else {
            myActivityManager?.AppExit()
        }
    }

}
