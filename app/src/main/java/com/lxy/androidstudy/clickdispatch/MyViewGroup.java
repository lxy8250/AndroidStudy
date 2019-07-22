package com.lxy.androidstudy.clickdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import static com.lxy.androidstudy.clickdispatch.ClickDispatchActivity.TAG;

/**
 * Created by lxy on 2018/12/13.
 */

public class MyViewGroup extends LinearLayout {


    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean boo = super.dispatchTouchEvent(ev);
        Log.i(TAG,"group action " + boo);
        return boo;
    }
}
