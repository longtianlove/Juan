package com.ja.assets.new_code.bussiness.other;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.ja.assets.R;
import com.ja.assets.databinding.ActivitySweepCodeBinding;
import com.ja.assets.ui.base.BaseActivity;
import com.ja.assets.utils.ToastUtil;

public class NewCode_SweepCodeActivity extends Activity {

    View iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcode_activity_sweep_code);
        initData();
    }

    void initData() {
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
