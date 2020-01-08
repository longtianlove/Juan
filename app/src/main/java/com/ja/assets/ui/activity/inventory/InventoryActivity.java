package com.ja.assets.ui.activity.inventory;

import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ja.assets.R;
import com.ja.assets.adapter.ViewPageManagerAdapter;
import com.ja.assets.databinding.ActivityInventoryBinding;
import com.ja.assets.listener.HandlerClickListener;
import com.ja.assets.ui.base.BaseActivity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class InventoryActivity extends BaseActivity implements HandlerClickListener {

    private ActivityInventoryBinding inventoryBinding;
    private int currIndex;
    private NoInventoryFragment noInventoryFragment;
    private YesInventoryFragment yesInventoryFragment;
    private ViewPager.OnPageChangeListener onPageChangeListener;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_inventory;
    }

    @Override
    protected void initView() {
        inventoryBinding = (ActivityInventoryBinding) getViewDataBinding();
        inventoryBinding.setClick(this);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        noInventoryFragment = new NoInventoryFragment();
        yesInventoryFragment = new YesInventoryFragment();
        fragmentList.add(noInventoryFragment);
        fragmentList.add(yesInventoryFragment);


        ViewPageManagerAdapter viewPageManagerAdapter = new ViewPageManagerAdapter(getSupportFragmentManager(), fragmentList);
        inventoryBinding.inventoryViewPager.setAdapter(viewPageManagerAdapter);
        inventoryBinding.inventoryViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currIndex = position;
                setBgColor(currIndex);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void handlerClick(@NotNull View view) {

    }


    private void setBgColor(int currentItem) {
        inventoryBinding.inventoryLeftTV.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        inventoryBinding.inventoryLeftLine.setBackgroundResource(R.color.white);
        inventoryBinding.inventoryRightTV.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        inventoryBinding.inventoryRightLine.setBackgroundResource(R.color.white);

        switch (currentItem) {
            case 0:
                inventoryBinding.inventoryLeftTV.setTextColor(ContextCompat.getColor(this, R.color.mainBgColor));
                inventoryBinding.inventoryLeftLine.setBackgroundResource(R.color.mainBgColor);
                break;
            case 1:
                inventoryBinding.inventoryLeftTV.setTextColor(ContextCompat.getColor(this, R.color.mainBgColor));
                inventoryBinding.inventoryLeftLine.setBackgroundResource(R.color.mainBgColor);
                break;
            default:
                break;
        }
    }
}
