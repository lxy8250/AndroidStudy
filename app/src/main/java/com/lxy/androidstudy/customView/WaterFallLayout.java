package com.lxy.androidstudy.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Creator : lxy
 * date: 2019/7/1
 */
public class WaterFallLayout extends ViewGroup {

    List<List<View>> views;
    List<Integer> lineHeight = new ArrayList<>();

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
        views = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        views.clear();
        int lineW = 0;
        int lineH = 0;
        int realW = 0,realH = 0;
        List<View> lineView = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childW = child.getMeasuredWidth();
            int childH = child.getMeasuredHeight();

            // 如果不用换行
            if(lineW + childW <= widthSize){
                lineView.add(child);
                lineH = lineH > childH ? lineH : childH;
                lineW += childW;
                if (i == childCount - 1){
                    realH += lineH;
                    realW = Math.max(realW, lineW);
                    views.add(lineView);
                    lineHeight.add(lineH);
                }
            }else {// 如果需要换行
                views.add(lineView);
                lineHeight.add(lineH);
                realW = realW > lineW ? realW : lineW;
                realH += lineH;
                lineView = new ArrayList<>();
                lineW = 0;
                lineH = 0;
            }

        }
        views.add(lineView);
        lineHeight.add(lineH);
        realW = realW > lineW ? realW : lineW;
        realH += lineH;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : realW + getPaddingLeft() + getPaddingRight(),
                (heightMode == MeasureSpec.EXACTLY ? heightSize : realH + getPaddingTop() + getPaddingBottom()));

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();

        for (int i = 0; i < views.size(); i++) {
            List<View> list = this.views.get(i);
            Integer integer = lineHeight.get(i);
            int left = paddingLeft;
            int top = paddingTop;
            for (int i1 = 0; i1 < list.size(); i1++) {
                View view = list.get(i1);
                int right = left + view.getMeasuredWidth();
                int bottom = top + view.getMeasuredHeight();
                view.layout(left,top,right,bottom);
                left = left + view.getMeasuredWidth();

            }
            paddingLeft = getPaddingLeft();
            paddingTop = paddingTop + integer;
        }
    }


}
