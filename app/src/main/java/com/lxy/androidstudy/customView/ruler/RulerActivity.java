package com.lxy.androidstudy.customView.ruler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lxy.androidstudy.R;
import com.lxy.androidstudy.customView.FlowLayout;
import com.lxy.androidstudy.customView.like.LikeView;

import java.util.ArrayList;
import java.util.List;

public class RulerActivity extends AppCompatActivity {

    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler);
        flowLayout = findViewById(R.id.activity_flow);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(this);
        for (int i = 0; i < 50; i++) {
            TextView tv = (TextView) inflater.inflate(R.layout.item_flow,flowLayout,false);
            tv.setText("aaaaaaaaa" + i);
            flowLayout.addView(tv);
        }

    }
}
