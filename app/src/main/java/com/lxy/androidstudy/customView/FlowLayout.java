package com.lxy.androidstudy.customView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘晓阳 on 2017/8/29.
 */

public class FlowLayout extends ViewGroup {

    List<ViewPos> vPos = new ArrayList<>();
    private Scroller scroller;
    private VelocityTracker velocityTracker;
    private int mLastScreen;
    private int realHeight;

    public interface OnItemClickListener {
        void onClick(View v, int index);
    }

    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller = new Scroller(context);
        velocityTracker = VelocityTracker.obtain();
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        //自己测量的宽高
        int width = 0;
        int height = 0;

        //每一行的宽高
        int lineW = 0;
        int lineH = 0;

        vPos.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);//获取每一个子view
            measureChild(child,widthMeasureSpec,heightMeasureSpec);//测量子view
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childW = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;//得到子view的宽高
            int childH = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (childW + lineW > widthSize - getPaddingLeft() - getPaddingRight()){//如果放入该view是超过父布局宽度，换行
                width = Math.max(lineW,childW);//取最大行宽为父布局行宽
                height +=lineH;//高度累加

                //开启新行
                lineW = childW;
                lineH = childH;
                vPos.add(new ViewPos(getPaddingLeft()+lp.leftMargin,
                        getPaddingTop()+lp.topMargin+height,
                        getPaddingLeft() + childW - lp.rightMargin,
                        getPaddingTop() + height + childH - lp.bottomMargin));
            }else {//如果不换行，则宽度累加，高度取最大值
                vPos.add(new ViewPos(getPaddingLeft() + lineW + lp.leftMargin,
                        getPaddingTop() + height + lp.topMargin,
                        getPaddingLeft() + lineW + childW - lp.rightMargin,
                        getPaddingTop() + height + childH - lp.bottomMargin));
                lineW += childW;
                lineH = Math.max(lineH,childH);

            }

            if (i == childCount -1){
                width = Math.max(width, lineW);
                height += lineH;
            }
        }
        Log.i("FLOW",width+"   "+height);
        realHeight = height;
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY) ? widthSize : width +getPaddingLeft()+getPaddingRight(),
                (heightMode == MeasureSpec.EXACTLY ? heightSize : height +getPaddingTop()+getPaddingBottom()));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            ViewPos pos = vPos.get(i);
            //设置View的左边、上边、右边底边位置
            child.layout(pos.left, pos.top, pos.right, pos.bottom);
        }
    }


    public void setOnItemClickListener(final OnItemClickListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(v, index);
                }
            });
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    private int lastHeight = 0;
    private float nextHeight;
    private float startHeight;
    private float endHeight;
    private int tempHeight;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startHeight = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                nextHeight = event.getY();
                int subY = (int) (nextHeight - startHeight);
                tempHeight = lastHeight + subY;
                Log.i("height",String.valueOf(tempHeight));
                if (tempHeight > realHeight){
                    tempHeight = realHeight;
                }
                if (tempHeight < 0){
                    tempHeight = 0;
                }
                scrollTo(0, -tempHeight);
                break;
            case MotionEvent.ACTION_UP:
                endHeight = event.getY();
                lastHeight = tempHeight;

        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            invalidate();
        }
    }

    private class ViewPos{
        int left;
        int top;
        int right;
        int bottom;

        public ViewPos(int left,int top,int right,int bottom){
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }
}
