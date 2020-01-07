package com.ja.assets.ui.activity.allocation;

import android.os.Bundle;

import com.ja.assets.R;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.ui.base.BaseJavaActivity;

import org.jetbrains.annotations.Nullable;

public class AllocationAddActivity extends BaseJavaActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocation_add);
    }
}