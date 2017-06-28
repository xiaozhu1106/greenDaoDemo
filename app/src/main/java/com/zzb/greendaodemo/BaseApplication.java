package com.zzb.greendaodemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by ZZB on 2017/6/28.
 */

public class BaseApplication extends Application {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
