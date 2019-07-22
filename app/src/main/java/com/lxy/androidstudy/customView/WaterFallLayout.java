package com.lxy.androidstudy.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Creator : lxy
 * date: 2019/7/1
 */
public class WaterFallLayout extends ViewGroup {
    public WaterFallLayout(Context context) {
        this(context,null);
    }

    public WaterFallLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaterFallLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public WaterFallLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
