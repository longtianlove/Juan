package com.ja.assets.ui.activity.purchase;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.ja.assets.R;
import com.ja.assets.adapter.DepartmentAdapter;
import com.ja.assets.databinding.ActivityDepartmentBinding;
import com.ja.assets.model.DeptBean;
import com.ja.assets.model.ResultResponse;
import com.ja.assets.retrofit.HttpServer;
import com.ja.assets.retrofit.RetrofitClient;
import com.ja.assets.retrofit.ShowUserView;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.ACacheUtil;
import com.ja.assets.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DepartmentActivity extends BaseActivity implements ShowUserView {
    private ActivityDepartmentBinding departmentBinding;
    private DepartmentAdapter departmentAdapter;
    private List<DeptBean> deptList;
    private String type = "0";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_department;
    }

    @Override
    protected void initView() {
        LinearLayout commonLeftLinearLayout = findViewById(R.id.commonLeftLinearLayout);
        commonLeftLinearLayout.setOnClickListener(v -> finish());
        TextView titleCenterText = findViewById(R.id.titleCenterText);

        departmentBinding = (ActivityDepartmentBinding) getViewDataBinding();


        type = getIntent().getStringExtra("type");
        switch (type) {
            case "0":
                titleCenterText.setText(ToastUtil.getString(R.string.selectAdminDepartment));
                break;
            case "1":
                titleCenterText.setText(ToastUtil.getString(R.string.selectBranch));
                break;
            default:
                titleCenterText.setText(ToastUtil.getString(R.string.selectDepartmentOrBranch));
                break;
        }
    }

    @Override
    protected void initAdapter() {


        deptList = new ArrayList<>();
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(this, departmentBinding.departmentRecycler);
        recyclerViewUtilKt.initRecyclerView();
        departmentAdapter = new DepartmentAdapter(R.layout.item_admin_popup_layout, deptList);
        recyclerViewUtilKt.setAdapter(departmentAdapter);
        departmentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("deptBean", deptList.get(position));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        switch (type) {
            case "0":
                new HttpServer(this, this).getAllBranchDeptList(ACacheUtil.getToken());
                break;
            case "1":
                new HttpServer(this, this).getAllManagerDeptList(ACacheUtil.getToken());
                break;
            default:
                break;
        }
    }

    @Override
    public void toMainActivity(int what, @NotNull Object data) {
        switch (what) {
            case 2:
                List<DeptBean> deptBeanList = (List<DeptBean>) data;
                notify(deptBeanList);
                break;
            case 3:
                List<DeptBean> deptBeanList1 = (List<DeptBean>) data;
                notify(deptBeanList1);
                break;
            default:
                break;
        }

    }

    private void notify(List<DeptBean> deptBeanList) {
        deptList.clear();
        deptList.addAll(deptBeanList);
        departmentAdapter.notifyDataSetChanged();
    }
}