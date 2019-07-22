package com.lxy.androidstudy;

import android.app.Application;
import android.content.Context;

/**
 * Creator : lxy
 * date: 2019/4/3
 */

public class App extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }
}
