package com.ja.assets.config;


import android.app.Application;

public class JavaApplication extends MyApplication {
    public static Application mcontext;
    @Override
    public void onCreate() {
        super.onCreate();
        mcontext=this;
    }
}
