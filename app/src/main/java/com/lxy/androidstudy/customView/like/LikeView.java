package com.lxy.androidstudy.customView.like;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.lxy.androidstudy.R;

/**
 * Creator : lxy
 * date: 2019/1/7
 */

public class LikeView extends AppCompatImageView {

    private Paint txtPaint;
    private Paint imgPaint;

    private Bitmap likeBitmap;
    private Bitmap unLikeBitmap;
    private Bitmap shineBitmap;

    private boolean isLike = false;

    private LikeClickListener listener;

    public LikeView(Context context) {
        this(context,null);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        imgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        resetBitmap();
    }

    private void resetBitmap() {
        likeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected);
        unLikeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_unselected);
        shineBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_messages_like_selected_shining);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawIcon(canvas);
        drawText(canvas);

    }

    private void drawIcon(Canvas canvas) {
        if (isLike){
            canvas.drawBitmap(likeBitmap,dp2px(50),dp2px(50),imgPaint);
            canvas.drawBitmap(shineBitmap,dp2px(52),dp2px(38),imgPaint);
        }else {
            canvas.drawBitmap(unLikeBitmap,dp2px(50),dp2px(50),imgPaint);
        }
    }

    private void drawText(Canvas canvas) {

    }

    public void setLike(boolean isLike){
        this.isLike = isLike;
    }


    public void startDownAnim(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(this,"scaleX",1f,0.5f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(this,"scaleY",1f,0.5f);

        setPivotX(getWidth() / 2);
        setPivotY(getHeight() / 2);
        invalidate();
        AnimatorSet set = new AnimatorSet();
        set.play(animator).with(animator2);
        set.setDuration(200);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isLike = !isLike;
                postInvalidate();
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getWidth(widthMeasureSpec), getHeight(heightMeasureSpec));
    }

    private int getWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = specSize;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }

    private int getHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                result = specSize;
                break;
            case MeasureSpec.AT_MOST:
                result = specSize;
                break;
            case MeasureSpec.EXACTLY:
                result = specSize;
                break;
        }
        return result;
    }



    private int dp2px(int dp){
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void setOnLikeClickListener(LikeClickListener likeClickListener){
        this.listener = likeClickListener;
    }

    interface LikeClickListener{
        void onClick(View view);
    }



}
