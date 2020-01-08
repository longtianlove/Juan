package com.ja.assets.new_code.bussiness.Patrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ja.assets.R;
import com.ja.assets.glide.GlideImgUtils;
import com.ja.assets.utils.PhotoUtils;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;


public class NewCode_PatrolCheckDetailActivity extends Activity {

    View ll_tijiaotupian;
View iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcode_activity_inspection_detail);
        initView();
    }

    void initView() {
        iv_back=findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_tijiaotupian = findViewById(R.id.ll_tijiaotupian);
        ll_tijiaotupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PhotoUtils().pickImage(NewCode_PatrolCheckDetailActivity.this,1,0);
            }
        });
    }


        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null && resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 0:
                List<String> photoList1 = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                GlideImgUtils.loadImage(this, photoList1.get(0), R.mipmap.add_img, findViewById(R.id.lossReportImg));
                break;
        }
    }

}