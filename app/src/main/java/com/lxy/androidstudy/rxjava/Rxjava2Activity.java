package com.lxy.androidstudy.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lxy.androidstudy.R;

public class Rxjava2Activity extends AppCompatActivity implements View.OnClickListener{

    private Button delayButton;
    private Button immediatelyButton;
    private Button changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava2);

        delayButton = findViewById(R.id.delay);
        immediatelyButton = findViewById(R.id.immediately);
        changeButton = findViewById(R.id.change);

        delayButton.setOnClickListener(this);
        immediatelyButton.setOnClickListener(this);
        changeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.delay:
                intent.setClass(this,DelayActivity.class);
                startActivity(intent);
                break;
            case R.id.immediately:
                intent.setClass(this,FirstRxjavaActivity.class);
                startActivity(intent);
                break;
            case R.id.change:
                intent.setClass(this,ChangeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
