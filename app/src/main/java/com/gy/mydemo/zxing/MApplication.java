package com.gy.mydemo.zxing;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by aaron on 16/9/7.
 */

public class MApplication extends Application{

    public static boolean isLogion;
    @Override
    public void onCreate() {
        super.onCreate();
        isLogion=false;
        ZXingLibrary.initDisplayOpinion(this);
    }
}
