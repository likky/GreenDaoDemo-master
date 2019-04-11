package com.meitu.lyz.greendaodemo;

import android.app.Application;

/**
 * @author LYZ 2018.08.06
 */
public class MyApplication extends Application {
    private static Application mInstance;

    public static Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
