package com.ja.assets.config;


import android.app.Application;

import androidx.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.Bugly;

public class JavaApplication extends MyApplication {
    public static Application mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;

        Bugly.init(getApplicationContext(), "cd5e8caa91", false);
        Fresco.initialize(this);
        MultiDex.install(this);
    }
}
