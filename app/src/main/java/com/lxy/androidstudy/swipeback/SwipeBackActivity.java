package com.lxy.androidstudy.swipeback;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.lxy.androidstudy.R;
import com.lxy.androidstudy.appbar.AppBarActivity;
import com.lxy.androidstudy.appbar.BaseAdapter;
import com.lxy.androidstudy.appbar.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SwipeBackActivity extends AppCompatActivity {

    View decorView;
    float downX, downY;
    float screenWidth, screenHeight;

    private RecyclerView rvApp;
    private List<String> list = new ArrayList<>();
    private TestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // setContentView(View view)方法会把view放到bgLayout中。
        setContentView(R.layout.activity_swipe_back);

        // 获得decorView
        decorView = getWindow().getDecorView();

        // 获得手机屏幕的宽度和高度，单位像素
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        rvApp = findViewById(R.id.recycler);
        adapter = new TestAdapter(this,list,R.layout.item_appbar);
        rvApp.setLayoutManager(new LinearLayoutManager(this));
        rvApp.setAdapter(adapter);
        for (int i = 0; i < 10; i++){
            list.add("item " + i);
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 通过重写该方法，对触摸事件进行处理
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        checkIsBack(event);
        return super.onTouchEvent(event);
    }

    private void checkIsBack(MotionEvent event) {
        Log.i("subX" ,downX + "");
        if (downX > 100) return;

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            downX = event.getX();
            downY = event.getY();

        }else if(event.getAction() == MotionEvent.ACTION_MOVE){

            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > 0){
                decorView.setX(moveDistanceX);
            }

        }else if(event.getAction() == MotionEvent.ACTION_UP){
            float moveDistanceX = event.getX() - downX;
            if(moveDistanceX > screenWidth / 3){
                // 如果滑动的距离超过了手机屏幕的一半, 滑动处屏幕后再结束当前Activity
                continueMove(moveDistanceX);
            }else{
                // 如果滑动距离没有超过一半, 往回滑动
                rebackToLeft(moveDistanceX);
            }
            downX = 0;
        }
    }

    /**
     * 从当前位置一直往右滑动到消失。
     * 这里使用了属性动画。
     */
    private void continueMove(float moveDistanceX){
        // 从当前位置移动到右侧。
        ValueAnimator anim = ValueAnimator.ofFloat(moveDistanceX, screenWidth);
        anim.setDuration(500); // 一秒的时间结束, 为了简单这里固定为1秒
        anim.start();

        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 位移
                float x = (float) (animation.getAnimatedValue());
                decorView.setX(x);
            }
        });

        // 动画结束时结束当前Activity
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }
        });
    }

    /**
     * Activity被滑动到中途时，滑回去~
     */
    private void rebackToLeft(float moveDistanceX){
        ObjectAnimator.ofFloat(decorView, "X", moveDistanceX, 0).setDuration(300).start();
    }

    class TestAdapter extends BaseAdapter<String> {

        public TestAdapter(Context context, List<String> list, int layoutId) {
            super(context, list, layoutId);
        }

        @Override
        protected void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.item_appbar_tv,s);
        }
    }
}
