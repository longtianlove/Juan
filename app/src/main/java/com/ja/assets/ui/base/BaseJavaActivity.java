package com.ja.assets.ui.base;

import com.ja.assets.utils.ToastUtil;

public class BaseJavaActivity extends PermissionActivity {

    protected void toast(String toastMessage) {
        ToastUtil.toast(toastMessage);
    }

}
