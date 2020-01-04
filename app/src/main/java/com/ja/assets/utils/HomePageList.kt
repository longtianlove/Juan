package com.ja.assets.utils

import android.text.TextUtils
import com.ja.assets.R
import com.ja.assets.model.AdminBean
import com.ja.assets.model.HomePage01
import com.ja.assets.model.UserInfo

class HomePageList {
    fun getHomePageList01(userInfo: UserInfo): MutableList<HomePage01> {
        val homePageList: MutableList<HomePage01> = mutableListOf()

        if (TextUtils.isEmpty(userInfo.c03)) {
            homePageList.add(HomePage01(0, R.mipmap.home_icon_caigoushenqing, "采购申请"))
            homePageList.add(HomePage01(3, R.mipmap.home_icon_diaoruqueren, "调入确认"))
            homePageList.add(HomePage01(4, R.mipmap.home_icon_diaochuqueren, "调出确认"))
            homePageList.add(HomePage01(9, R.mipmap.home_icon_baoxiushenqing, "报修申请"))
            homePageList.add(HomePage01(11, R.mipmap.home_icon_baoxiuqueren, "报修确认"))
            homePageList.add(HomePage01(12, R.mipmap.home_icon_chuzhishenqing, "处置申请"))
        } else if (userInfo.c03 == "cwb") {
            homePageList.add(HomePage01(5, R.mipmap.home_icon_pandiandanchuangjian, "盘点单创建"))
            homePageList.add(HomePage01(6, R.mipmap.home_icon_pandianjilu, "盘点记录"))
            homePageList.add(HomePage01(13, R.mipmap.home_icon_chuzhishenhe, "处置审核"))
            homePageList.add(HomePage01(14, R.mipmap.home_icon_baoxiuqueren, "调配审核"))
            homePageList.add(HomePage01(15, R.mipmap.home_icon_diaopeishenqing, "采购审核"))
        } else if (userInfo.c03 == "zhb" || userInfo.c03 == "kjb" || userInfo.c03 == "yyb" || userInfo.c03 == "bwb") {
            homePageList.add(HomePage01(1, R.mipmap.home_icon_caigoushenhe, "采购审批"))
            homePageList.add(HomePage01(2, R.mipmap.home_icon_diaopeishenqing, "调配申请"))
            homePageList.add(HomePage01(5, R.mipmap.home_icon_pandiandanchuangjian, "盘点单创建"))
            homePageList.add(HomePage01(6, R.mipmap.home_icon_pandianjilu, "盘点记录"))
            homePageList.add(HomePage01(7, R.mipmap.home_icon_xunjiankiebiao, "巡检列表"))
            homePageList.add(HomePage01(8, R.mipmap.home_icon_xunjianjilu, "巡检记录"))
            homePageList.add(HomePage01(10, R.mipmap.home_icon_baoxiushenhe, "报修审批"))
        }
        return homePageList
    }


    fun getAdaminList(): MutableList<AdminBean> =
        mutableListOf(AdminBean(1, "综合办"), AdminBean(2, "科技部"), AdminBean(3, "运营部"), AdminBean(4, "保卫部"))
}
