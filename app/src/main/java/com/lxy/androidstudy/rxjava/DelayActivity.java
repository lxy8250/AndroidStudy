package com.lxy.androidstudy.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lxy.androidstudy.R;
import com.lxy.androidstudy.ToastUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * rxjava的延迟创建符
 */
public class DelayActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG="DelayActivity-->";

    private Button deferButton;
    private Button timerButton;
    private Button intervalButton;
    private Button intervalRangeButton;
    private Button rangeButton;
    private Button rangeLongButton;
    Integer i = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay);

        initView();
    }

    private void initView() {
        deferButton = findViewById(R.id.defer);
        timerButton = findViewById(R.id.timer);
        intervalButton = findViewById(R.id.interval);
        intervalRangeButton = findViewById(R.id.intervalRange);
        rangeButton = findViewById(R.id.range);
        rangeLongButton = findViewById(R.id.rangeLong);

        deferButton.setOnClickListener(this);
        timerButton.setOnClickListener(this);
        intervalButton.setOnClickListener(this);
        intervalRangeButton.setOnClickListener(this);
        rangeButton.setOnClickListener(this);
        rangeLongButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.defer:
                defer();
                break;
            case R.id.timer:
                timer();
                break;
            case R.id.interval:
                interval();
                break;
            case R.id.intervalRange:
                intervalRange();
                break;
            case R.id.range:
                range();
                break;
            case R.id.rangeLong:
                rangeLong();
                break;
        }
    }

    private void rangeLong() {
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable.rangeLong(3,10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Long value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    private void range() {
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 注：若设置为负数，则会抛出异常
        Observable.range(3,10)
                // 该例子发送的事件序列特点：从3开始发送，每次发送事件递增1，一共发送10个事件
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }
                    // 默认最先调用复写的 onSubscribe（）

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件"+ value  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });

    }

    private void intervalRange() {
        // 参数1 = 事件序列起始点；
        // 参数2 = 事件数量；
        // 参数3 = 第1次事件延迟发送时间；
        // 参数4 = 间隔时间数字；
        // 参数5 = 时间单位
        Observable.intervalRange(3,5,3,1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "接收到的整数是"+ aLong  );
                        ToastUtil.showToast(DelayActivity.this,"延迟3秒，接收到的整数是"+ aLong  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }


    /**
     * 延迟指定时间后，每间隔x秒发送一次事件，从0 开始，无限递增1的的整数序列
     * 运行同样不是在主线程
     */
    private void interval() {

        // 参数说明：
        // 参数1 = 第1次延迟时间；
        // 参数2 = 间隔时间数字；
        // 参数3 = 时间单位；
        // 延迟3s 后，每隔1秒发送一个事件，发送的事件序列 = 从0开始、无限递增1的的整数序列
        Observable.interval(3,1,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "接收到的整数是"+ aLong  );
                        ToastUtil.showToast(DelayActivity.this,"延迟3秒，接收到的整数是"+ aLong  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 延迟指定时间后，创建observable对象，发送事件
     * 但是并不是运行在主线程中，如果需要更新ui操作，就必须转到android主线程
     */
    private void timer() {
        Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d(TAG, "延迟3秒，接收到的整数是"+ aLong  );
                        ToastUtil.showToast(DelayActivity.this,"延迟3秒，接收到的整数是"+ aLong  );
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * 动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
     * 运行在android主线程
     */
    private void defer() {
//        <-- 1. 第1次对i赋值 ->>


        // 2. 通过defer 定义被观察者对象
        // 注：此时被观察者对象还没创建

        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
//                return Observable.just(i);
                return Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(i);
                    }
                });
            }
        });

//        <-- 2. 第2次对i赋值 ->>
        i = 15;

//        <-- 3. 观察者开始订阅 ->>
                // 注：此时，才会调用defer（）创建被观察者对象（Observable）
                observable.subscribe(new Observer<Integer>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到的整数是"+ value  );
                        Toast.makeText(DelayActivity.this,"接收到的整数是"+ value ,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });


    }
}
