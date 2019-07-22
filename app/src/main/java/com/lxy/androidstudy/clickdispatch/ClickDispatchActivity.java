package com.lxy.androidstudy.clickdispatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.lxy.androidstudy.R;

public class ClickDispatchActivity extends AppCompatActivity {

    public static final String TAG = "ClickDispatchActivity";
    private Button btnClick;
    private Button btnClick2;
    private MyViewGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_dispatch);

        btnClick = findViewById(R.id.click_dispatch);
//        btnClick.setClickable(false);
//        btnClick.setEnabled(false);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"Click");
            }
        });

        btnClick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,"btnClick action " + event.getAction());
                return false;
            }
        });

        btnClick2 = findViewById(R.id.click_dispatch2);
        btnClick2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,"btnClick2 action " + event.getAction());
                return false;
            }
        });

        group = findViewById(R.id.group);
        group.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG,"group action " + event.getAction());
                return false;
            }
        });
    }
}
