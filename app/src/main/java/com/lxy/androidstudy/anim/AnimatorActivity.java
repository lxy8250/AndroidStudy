package com.lxy.androidstudy.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lxy.androidstudy.R;

/**
 * Created by 刘晓阳 on 2017/11/20.
 */

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button tranBtn,rotatBtn,scaleBtn,alphaBut,setBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.value_animator);


        initView();
        initListener();

    }



    private void initView() {
        tv = findViewById(R.id.text);
        tranBtn = findViewById(R.id.translation);
        rotatBtn = findViewById(R.id.rotation);
        scaleBtn = findViewById(R.id.scale);
        alphaBut = findViewById(R.id.alpha);
        setBtn = findViewById(R.id.set);
    }

    private void initListener() {
        tranBtn.setOnClickListener(this);
        scaleBtn.setOnClickListener(this);
        alphaBut.setOnClickListener(this);
        rotatBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rotation:
                rotation();
                break;

            case R.id.alpha:
                alpha();
                break;
            case R.id.translation:
                // 平移
                translate();
                break;
            case R.id.scale:
                scale();
                break;
            case R.id.set:
                together();
                break;
            default:
                break;
        }
    }

    /**
     * 平移
     */
    private void translate(){
        // 获取textView当前 x 坐标
        float curTranslationX = tv.getTranslationX();
        float curTranslationY = tv.getTranslationY();
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"translationX",curTranslationX,-500,curTranslationX);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tv,"translationY",curTranslationY,500,curTranslationY);
        AnimatorSet set = new AnimatorSet();
        set.play(animator).after(animator2);
        set.setDuration(2000);
        set.start();

    }

    /**
     * 缩放
     */
    private void scale(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"scaleY",1f,3f,1f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(tv,"scaleX",1f,3f,1f);
        AnimatorSet set = new AnimatorSet();
        set.play(animator).after(animator2);
        set.setDuration(1000);
        set.start();
    }

    /**
     * 旋转
     */
    private void rotation(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"rotation",0f,360f);
        animator.setDuration(500);
        animator.start();
    }

    /**
     * 透明
     */
    private void alpha(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tv,"alpha",1f,0f,1f);
        animator.setDuration(500);
        animator.start();
    }

    private void together(){
        ObjectAnimator scaleYAnim = ObjectAnimator.ofFloat(tv,"scaleY",1f,3f,1f);
        ObjectAnimator scaleXAnim = ObjectAnimator.ofFloat(tv,"scaleX",1f,3f,1f);
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(tv,"alpha",1f,0f,1f);
        ObjectAnimator rotationAnim = ObjectAnimator.ofFloat(tv,"rotation",0f,360f);
        AnimatorSet set = new AnimatorSet();
        set.play(alphaAnim).after(scaleXAnim).with(scaleYAnim).before(rotationAnim);
        set.setDuration(2000);
        set.start();
    }
}
