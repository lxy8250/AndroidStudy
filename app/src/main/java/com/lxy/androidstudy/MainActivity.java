package com.lxy.androidstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lxy.androidstudy.anim.AnimatorActivity;
import com.lxy.androidstudy.appbar.AppBarActivity;
import com.lxy.androidstudy.customView.CoustomViewActivity;
import com.lxy.androidstudy.customView.ruler.RulerActivity;
import com.lxy.androidstudy.handle.HandleActivity;
import com.lxy.androidstudy.rxjava.Rxjava2Activity;
import com.lxy.androidstudy.swipeback.SwipeBackActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button animatorButton;
    Button rxJavaButton;
    Button appbarButton;
    Button swipebackButton;
    Button likeButton;
    Button handleButton;
    Button customViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initListener() {
        animatorButton.setOnClickListener(this);
        rxJavaButton.setOnClickListener(this);
        appbarButton.setOnClickListener(this);
        swipebackButton.setOnClickListener(this);
        likeButton.setOnClickListener(this);
        handleButton.setOnClickListener(this);
        customViewButton.setOnClickListener(this);
    }

    private void initView() {
        animatorButton = findViewById(R.id.value_animator);
        rxJavaButton = findViewById(R.id.handle_la);
        appbarButton = findViewById(R.id.appbar);
        swipebackButton = findViewById(R.id.swipeback);
        likeButton = findViewById(R.id.like);
        handleButton = findViewById(R.id.handle);
        customViewButton = findViewById(R.id.customView);
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
            case R.id.swipeback:
                intent.setClass(MainActivity.this, SwipeBackActivity.class);
                startActivity(intent);
                break;
            case R.id.like:
                intent.setClass(MainActivity.this, RulerActivity.class);
                startActivity(intent);
                break;
            case R.id.handle:
                intent.setClass(MainActivity.this, HandleActivity.class);
                startActivity(intent);
                break;
            case R.id.customView:
                intent.setClass(MainActivity.this, CoustomViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
