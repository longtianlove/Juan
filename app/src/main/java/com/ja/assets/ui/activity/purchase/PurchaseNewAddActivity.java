package com.ja.assets.ui.activity.purchase;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivityPurchaseNewAddBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.model.DeptBean;
import com.ja.assets.model.PurchaseAssetsInfo;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.EditIsCanUseBtnUtils;
import com.ja.assets.utils.ToastUtil;
;import org.jetbrains.annotations.NotNull;

public class PurchaseNewAddActivity extends BaseActivity implements HandlerClickListener {

    private ActivityPurchaseNewAddBinding purchaseNewAddBinding;
    private PurchaseAssetsInfo assetsInfo;
    private int position;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchase_new_add;
    }

    @Override
    protected void initView() {


        findViewById(R.id.commonLeftLinearLayout).setOnClickListener(v -> finish());
        TextView titleCenterText = findViewById(R.id.titleCenterText);
        titleCenterText.setText(ToastUtil.getString(R.string.purchaseNewAdd));
        purchaseNewAddBinding = (ActivityPurchaseNewAddBinding) getViewDataBinding();
        purchaseNewAddBinding.setClick(this);

        assetsInfo = (PurchaseAssetsInfo) getIntent().getSerializableExtra("assetsInfo");
        position = getIntent().getIntExtra("position", 0);

        purchaseNewAddBinding.setAssetsInfoBean(assetsInfo);


    }

    @Override
    protected void initAdapter() {
        purchaseNewAddBinding.purchaseQuantityTV.setText(String.valueOf(assetsInfo.getPurchaseQuantity()));
    }

    @Override
    protected void initData() {
        EditIsCanUseBtnUtils.getInstance()
                .addEditext(purchaseNewAddBinding.assetsNameET)
                .addEditext(purchaseNewAddBinding.purposeTV)
                .addEditext(purchaseNewAddBinding.purchaseQuantityTV)
                .addButton(purchaseNewAddBinding.purchaseSubmitBtn)
                .setText("提交")
                .setTextNull("提交")
                .setTextFull("提交")
                .setNullbackgroundResource(R.drawable.btn_circular_selector_company_color)
                .setFullbackgroundResource(R.drawable.btn_circular_selector_main_color).build();
    }


    @Override
    public void handlerClick(@NotNull View view) {
        switch (view.getId()) {
            case R.id.departmentNameLinear:
                Intent intent = new Intent(this, DepartmentActivity.class);
                intent.putExtra("type", "0");
                startActivityForResult(intent, 1);
                break;
            case R.id.purchaseSubmitBtn:

                setAssetsInfo();
                break;
            default:
                break;
        }
    }

    private void setAssetsInfo() {
        assetsInfo.setPurchaseQuantity(Integer.valueOf(purchaseNewAddBinding.purchaseQuantityTV.getText().toString().trim()));


        Intent intent = new Intent();
        intent.putExtra("assetsInfo", assetsInfo);
        intent.putExtra("position", position);
        this.setResult(Activity.RESULT_OK, intent);
        // 关闭Activity
        this.finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            DeptBean deptBean = (DeptBean) data.getSerializableExtra("deptBean");
            purchaseNewAddBinding.adminDepartmentTV.setText(deptBean.getDeptname());
            assetsInfo.setDepartmentName(deptBean.getDeptname());
            assetsInfo.setDepartmentId(deptBean.getId());
        }
    }
}
