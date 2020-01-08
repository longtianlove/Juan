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
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.ja.assets.MainActivity;
import com.ja.assets.R;
import com.ja.assets.adapter.HomeAdapter;
import com.ja.assets.databinding.FragmentHomeLayoutBinding;
import com.ja.assets.model.*;
import com.ja.assets.retrofit.HttpServer;
import com.ja.assets.retrofit.RetrofitClient;
import com.ja.assets.retrofit.ShowUserView;
import com.ja.assets.ui.activity.dispose.DisposeAddAttrActivity;
import com.ja.assets.ui.activity.purchase.PurchaseApplyActivity;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.HomePageList;
import com.ja.assets.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.net.HttpCookie;
import java.util.ArrayList;
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
        initView();
        getData();
    }

    private void initAdapter() {
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(mainActivity, homeBinding.homeFunctionModel);
        recyclerViewUtilKt.initTable(3);
        List<HomePage01> homeList = new HomePageList().getHomePageList01(ACacheUtil.getUserInfo());
        homeAdapter = new HomeAdapter(mainActivity, R.layout.item_fragment_home_layout, homeList);
        recyclerViewUtilKt.setAdapter(homeAdapter);
        homeAdapter.setOnItemClickListener((adapter, view, position) -> {
            switch (homeList.get(position).getPosition()) {
                /**0-5是使用部门的功能**/
                /**6-7是财务部门的功能**/
                /**8-13是管理部门的功能**/
                case 0:
                    //采购申请
                    Intent intent = new Intent(mainActivity, PurchaseApplyActivity.class);
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
                new HttpServer(this, mainActivity).getAllWailDealList(ACacheUtil.getToken());
                break;
            case 1:
                List<DeptBean> details = (List<DeptBean>) data;
                if (details.size() == 0) {
                    homeBinding.homeMsgToastTV.setVisibility(View.GONE);
                    homeBinding.readyToDoNewsCheckAcceptTV.setText(R.string.readyNoNewsCheckAccept);
                } else {
                    homeBinding.homeMsgToastTV.setVisibility(View.VISIBLE);
                    homeBinding.readyToDoNewsCheckAcceptTV.setText("您有" + details.size() + "条待办消息，请查收~");
                }
                break;
            default:
                break;
        }

    }

    private void getData() {
        new HttpServer(this, mainActivity).getZcValueAndZcNumber(ACacheUtil.getToken());
    }
    private void initView() {

        homeBinding.homeChat1.setUsePercentValues(true);
        homeBinding.homeChat1.getDescription().setEnabled(false);
        homeBinding.homeChat1.setExtraOffsets(5f, 10f, 5f, 5f);
        homeBinding.homeChat1.setDragDecelerationFrictionCoef(0.95f);
        homeBinding.homeChat1.setCenterText(generateCenterSpannableText());
        homeBinding.homeChat1.setDrawHoleEnabled(true);
        homeBinding.homeChat1.setHoleColor(Color.WHITE);
        homeBinding.homeChat1.setTransparentCircleColor(Color.WHITE);
        homeBinding.homeChat1.setTransparentCircleAlpha(110);
        homeBinding.homeChat1.setHoleRadius(58f);
        homeBinding.homeChat1.setTransparentCircleRadius(61f);
        homeBinding.homeChat1.setDrawCenterText(true);
        homeBinding.homeChat1.setRotationAngle(0f);
        homeBinding.homeChat1.setRotationEnabled(true);
        homeBinding.homeChat1.setHighlightPerTapEnabled(true);
        homeBinding.homeChat1.setDrawEntryLabels(false);

        Legend legend = homeBinding.homeChat1.getLegend();
        legend.setEnabled(false);
        homeBinding.homeChat1.setEntryLabelColor(Color.WHITE);
        homeBinding.homeChat1.setEntryLabelTextSize(12f);
        setData();
    }

    private void setData() {

        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(146 / 274));
        entries.add(new PieEntry(76 / 274));
        entries.add(new PieEntry(28 / 274));
        entries.add(new PieEntry(24 / 274));
        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        //设置饼状图Item之间的间隙
        dataSet.setSliceSpace(3f);
        //饼图Item被选中时变化的距离
        dataSet.setSelectionShift(10f);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(71, 173, 247));
        colors.add(Color.rgb(39, 229, 153));
        colors.add(Color.rgb(93, 112, 146));
        colors.add(Color.rgb(255, 191, 0));
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);

        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter());

        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        homeBinding.homeChat1.setData(data);
        homeBinding.homeChat1.highlightValues(null);
        homeBinding.homeChat1.invalidate();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("管理部门\n资产数量");
        s.setSpan(new RelativeSizeSpan(0.7f), 0, 8, 0);
        return s;
    }


}