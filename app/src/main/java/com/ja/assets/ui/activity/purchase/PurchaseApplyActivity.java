package com.ja.assets.ui.activity.purchase;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fixed.u8.animation.RecyclerViewUtilKt;
import com.ja.assets.R;
import com.ja.assets.adapter.PurchaseApplyAdapter;
import com.ja.assets.databinding.ActivityPurchaseApplyBinding;
import com.ja.assets.model.AssetsInfo;
import com.ja.assets.model.PurchaseAssetsInfo;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.view.BackDialog;

import java.util.ArrayList;
import java.util.List;


public class PurchaseApplyActivity extends BaseActivity {
    private ActivityPurchaseApplyBinding purchaseApplyBinding;
    private PurchaseApplyAdapter purchaseApplyAdapter;
    private List<PurchaseAssetsInfo> assetsInfoList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_purchase_apply;
    }

    @Override
    protected void initView() {

        LinearLayoutCompat commonLeftLinearLayout = findViewById(R.id.commonLeftLinearLayout);
        commonLeftLinearLayout.setOnClickListener(v -> {
            finish();
        });
        TextView titleCenterText = findViewById(R.id.titleCenterText);
        titleCenterText.setText(R.string.purchaseApply);
        TextView titleRightText = findViewById(R.id.titleRightText);
        titleRightText.setText(R.string.addAssets);
        purchaseApplyBinding = (ActivityPurchaseApplyBinding) getViewDataBinding();


        titleRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PurchaseApplyActivity.this, PurchaseNewAddActivity.class);
                intent.putExtra("assetsInfo", new PurchaseAssetsInfo("", "", "", 0, "", 0, "", "", "", ""));
                intent.putExtra("position", assetsInfoList.size());
                startActivityForResult(intent, 0);
            }
        });
        purchaseApplyBinding.submitAuditBtn.setOnClickListener(v -> toast("点击事件"));
    }

    @Override
    protected void initAdapter() {
        assetsInfoList = new ArrayList<>();
        setRecycler();
        RecyclerViewUtilKt recyclerViewUtilKt = new RecyclerViewUtilKt(this, purchaseApplyBinding.purchaseApplyRecycler);
        recyclerViewUtilKt.initRecyclerView();
        purchaseApplyAdapter = new PurchaseApplyAdapter(R.layout.item_purachse_apply_layout, assetsInfoList);
        recyclerViewUtilKt.setAdapter(purchaseApplyAdapter);
        purchaseApplyAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = new Intent(this, PurchaseNewAddActivity.class);
            intent.putExtra("assetsInfo", assetsInfoList.get(position));
            intent.putExtra("position", position);
            startActivityForResult(intent, 1);
        });
        purchaseApplyAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.itemPurchaseApplyDelete:
                    assetsInfoList.remove(position);
                    purchaseApplyAdapter.notifyDataSetChanged();
                    setRecycler();
                    break;
                case R.id.itemPurchaseApplyEdit:
                    break;
                case R.id.itemPurchaseApplyUnAgree:
                    break;
                case R.id.itemPurchaseApplyAgree:
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case 0:
                PurchaseAssetsInfo assetsInfo = (PurchaseAssetsInfo) data.getSerializableExtra("assetsInfo");
                int position = data.getIntExtra("position", 0);
                assetsInfoList.add(assetsInfo);
                purchaseApplyAdapter.notifyDataSetChanged();
                setRecycler();
                break;
            case 1:
                PurchaseAssetsInfo assetsInfo1 = (PurchaseAssetsInfo) data.getSerializableExtra("assetsInfo");
                int position1 = data.getIntExtra("position", 0);
                assetsInfoList.set(position1, assetsInfo1);
                purchaseApplyAdapter.notifyDataSetChanged();
                setRecycler();
                break;
            default:
                break;
        }
    }

    private void setRecycler() {
        if (assetsInfoList.size() == 0) {
            purchaseApplyBinding.noAssetsLinear.setVisibility(View.VISIBLE);
            purchaseApplyBinding.purchaseApplyRecycler.setVisibility(View.GONE);
        } else {
            purchaseApplyBinding.noAssetsLinear.setVisibility(View.GONE);
            purchaseApplyBinding.purchaseApplyRecycler.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            new BackDialog(this).setListener(this::finish);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
