package com.ja.assets.ui.activity.main;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.fixed.u8.ui.base.BaseFragment;

import com.ja.assets.MainActivity;
import com.ja.assets.R;
import com.ja.assets.adapter.HomeAdapter;
import com.ja.assets.databinding.FragmentHomeLayoutBinding;
import com.ja.assets.model.*;
import com.ja.assets.new_code.bussiness.Patrol.NewCode_PatrolCheckListActivity;
import com.ja.assets.retrofit.HttpServer;
import com.ja.assets.retrofit.ShowUserView;
import com.ja.assets.ui.activity.purchase.PurchaseApplyActivity;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.HomePageList;
import com.ja.assets.utils.PieChartUtil;
import com.ja.assets.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;


public class HomeFragment extends BaseFragment implements ShowUserView {

    private MainActivity mainActivity;
    private FragmentHomeLayoutBinding homeBinding;
    private HomeAdapter homeAdapter;
    private HomeIndexCount homeIndexCount;


    @Override
    protected void setOnCreate() {
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    protected void initDataView() {
        homeBinding = (FragmentHomeLayoutBinding) getFragmentDataBinding();
        initAdapter();
        getData();
    }

    private void initAdapter() {
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(mainActivity, homeBinding.homeFunctionModel);
        recyclerViewUtilKt.initTable(3);
        List<HomePage01> homeList = new HomePageList().getHomePageList01(ACacheUtil.getUserInfo());
        homeAdapter = new HomeAdapter(mainActivity, R.layout.item_fragment_home_layout, homeList);
        recyclerViewUtilKt.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent;
            switch (homeList.get(position).getPosition()) {
                /**0-5是使用部门的功能**/
                /**6-7是财务部门的功能**/
                /**8-13是管理部门的功能**/
                case 0:
                    //采购申请
                    intent = new Intent(mainActivity, PurchaseApplyActivity.class);
                    startActivity(intent);
                    break;

                case 1: //采购记录
                    break;
                case 2://报修申请
                    break;
                case 3:  //报修记录
                    break;
                case 4: //处置申请
                    break;
                case 5://处置记录
                    break;
                case 6:  //盘点单创建
                    break;
                case 7: //盘点记录
                    break;
                case 8://调配申请
                    break;
                case 9:  //调配记录
                    break;
                case 10: //巡检列表
                    intent = new Intent(mainActivity, NewCode_PatrolCheckListActivity.class);
                    startActivity(intent);
                    break;
                case 11://巡检记录
                    break;
                default:
                    break;
            }

        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        initDataView();
    }

    @Override
    public void toMainActivity(int what, @NotNull Object data) {
        switch (what) {
            case 0:
                homeIndexCount = (HomeIndexCount) data;
                homeBinding.setHomeIndexCountBean(homeIndexCount);
                initView();
                break;
            case 1:
                List<DeptBean> details = (List<DeptBean>) data;
                if (details.size() == 0) {
                    homeBinding.homeMsgToastTV.setVisibility(View.GONE);
                    homeBinding.readyToDoNewsCheckAcceptTV.setText(R.string.readyNoNewsCheckAccept);
                } else {
                    homeBinding.homeMsgToastTV.setVisibility(View.VISIBLE);
                    homeBinding.homeMsgToastTV.setText(String.valueOf(details.size()));
                    homeBinding.readyToDoNewsCheckAcceptTV.setText("您有" + details.size() + "条待办消息，请查收~");
                }
                break;
            default:
                break;
        }

    }

    private void getData() {
        new HttpServer(this, mainActivity).getZcValueAndZcNumber(ACacheUtil.getToken());
        new HttpServer(this, mainActivity).getAllWailDealList(ACacheUtil.getToken());
    }

    private void initView() {
        HashMap<String, Float> dataMap = new HashMap();
        dataMap.put(ToastUtil.getString(R.string.AdminDepartment02), (float) homeIndexCount.getZhbZcCount());
        dataMap.put(ToastUtil.getString(R.string.AdminDepartment04), (float) homeIndexCount.getYybZcCount());
        dataMap.put(ToastUtil.getString(R.string.AdminDepartment03), (float) homeIndexCount.getKjbZcCount());
        dataMap.put(ToastUtil.getString(R.string.AdminDepartment05), (float) homeIndexCount.getBwbZcCount());



        PieChartUtil.getPitChart().setPieChart(homeBinding.homeChat1, dataMap, "管理部门\n资产数量", false);
    }


}