package com.lxy.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lxy.androidstudy.anim.AnimatorActivity;
import com.lxy.androidstudy.appbar.AppBarActivity;
import com.lxy.androidstudy.rxjava.Rxjava2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button animatorButton;
    Button handleButton;
    Button appbarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initListener() {
        animatorButton.setOnClickListener(this);
        handleButton.setOnClickListener(this);
        appbarButton.setOnClickListener(this);
    }

    private void initView() {
        animatorButton = findViewById(R.id.value_animator);
        handleButton = findViewById(R.id.handle_la);
        appbarButton = findViewById(R.id.appbar);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.value_animator:
                intent.setClass(MainActivity.this, AnimatorActivity.class);
                startActivity(intent);
                break;
            case R.id.handle_la:
                intent.setClass(MainActivity.this, Rxjava2Activity.class);
                startActivity(intent);
                break;
            case R.id.appbar:
                intent.setClass(MainActivity.this, AppBarActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
