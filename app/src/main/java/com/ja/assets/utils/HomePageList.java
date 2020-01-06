package com.ja.assets.utils;

import android.text.TextUtils;

import com.ja.assets.R;
import com.ja.assets.model.AdminBean;
import com.ja.assets.model.HomePage01;
import com.ja.assets.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class HomePageList {
    public List<HomePage01> getHomePageList01(UserInfo userInfo) {
        List<HomePage01> homePageList = new ArrayList<>();

        if (TextUtils.isEmpty(userInfo.getC03())) {
            homePageList.add(new HomePage01(0, R.mipmap.home_icon_caigoushenqing, "采购申请"));
            homePageList.add(new HomePage01(1, R.mipmap.home_icon_diaoruqueren, "采购记录"));
            homePageList.add(new HomePage01(2, R.mipmap.home_icon_baoxiushenqing, "报修申请"));
            homePageList.add(new HomePage01(3, R.mipmap.home_icon_baoxiuqueren, "报修记录"));
            homePageList.add(new HomePage01(4, R.mipmap.home_icon_chuzhishenqing, "处置申请"));
            homePageList.add(new HomePage01(5, R.mipmap.home_icon_diaochuqueren, "处置记录"));
        } else if (userInfo.getC03().equals("cwb")) {
            homePageList.add(new HomePage01(6, R.mipmap.home_icon_pandiandanchuangjian, "盘点单创建"));
            homePageList.add(new HomePage01(7, R.mipmap.home_icon_pandianjilu, "盘点记录"));
        } else if (userInfo.getC03().equals("zhb") || userInfo.getC03().equals("kjb") || userInfo.getC03().equals("yyb") || userInfo.getC03().equals("bwb")) {
            homePageList.add(new HomePage01(8, R.mipmap.home_icon_diaopeishenqing, "调配申请"));
            homePageList.add(new HomePage01(9, R.mipmap.home_icon_caigoushenhe, "调配记录"));
            homePageList.add(new HomePage01(10, R.mipmap.home_icon_pandiandanchuangjian, "盘点单创建"));
            homePageList.add(new HomePage01(11, R.mipmap.home_icon_pandianjilu, "盘点记录"));
            homePageList.add(new HomePage01(12, R.mipmap.home_icon_xunjiankiebiao, "巡检列表"));
            homePageList.add(new HomePage01(13, R.mipmap.home_icon_xunjianjilu, "巡检记录"));
        }
        return homePageList;
    }


    public List<AdminBean> getAdaminList() {
        List<AdminBean> adminBeanList = new ArrayList<>();
        adminBeanList.add(new AdminBean(1, "综合办"));
        adminBeanList.add(new AdminBean(2, "科技部"));
        adminBeanList.add(new AdminBean(3, "运营部"));
        adminBeanList.add(new AdminBean(4, "保卫部"));
        return adminBeanList;
    }

}
