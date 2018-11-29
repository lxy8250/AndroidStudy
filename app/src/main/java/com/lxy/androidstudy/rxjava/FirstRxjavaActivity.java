package com.lxy.androidstudy.rxjava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lxy.androidstudy.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by 刘晓阳 on 2018/1/23.
 */

public class FirstRxjavaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private Button justButton;
    private Button fromArrayButton;
    private Button fromIterableButton;
    private Button neverButton;
    private Button emptyButton;
    private Button errorButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjava);

        initView();

        // 1. 创建一个被观察者，即顾客
        //  在复写的subscribe（）里定义需要发送的事件
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(21);
                e.onNext(3);
                e.onComplete();
            }
        });

        // 2. 创建一个观察者
        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("我是谁", "对subscribe事件作出响应" );
            }

            @Override
            public void onNext(Object o) {
                Log.d("我是谁", "对Next事件作出响应" + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("我是谁", "对error事件作出响应" );
            }

            @Override
            public void onComplete() {
                Log.d("我是谁", "对complete事件作出响应");
            }
        };
        // 3. 建立订阅关系
        observable.subscribe(observer);

    }


    private void initView() {
        button = findViewById(R.id.start);
        fromArrayButton = findViewById(R.id.fromArray);
        fromIterableButton = findViewById(R.id.fromIterable);
        neverButton = findViewById(R.id.never);
        emptyButton = findViewById(R.id.empty);
        errorButton = findViewById(R.id.error);
        justButton = findViewById(R.id.just);

        justButton.setOnClickListener(this);
        fromIterableButton.setOnClickListener(this);
        fromArrayButton.setOnClickListener(this);
        neverButton.setOnClickListener(this);
        errorButton.setOnClickListener(this);
        emptyButton.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start:
                start();
                break;
            case R.id.just:
                just();
                break;
            case R.id.fromArray:
                fromArray();
                break;
            case R.id.fromIterable:
                fromIterable();
                break;
            case R.id.empty:
                empty();
                break;
            case R.id.error:
                error();
                break;
            case R.id.never:
                never();
                break;

        }

    }

    private void fromArray() {
        Integer[] arr = new Integer[]{1,2,3,1};
        Observable.fromArray(arr)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i("test","a响应  "+integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void fromIterable() {

    }
    private void never() {

    }
    private void error() {

    }
    private void empty() {

    }

    /**
     * 快速创建被观察者对象，最多十个，可以是8种基本类型，也可以是混搭
     * 但是混搭onNext()的参数是Serializable
     */
    private void just() {

        int[] arr = new int[]{1,5,1};
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Observable.just(list)
                .subscribe(new Observer<ArrayList<Integer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList<Integer> integers) {
                        Log.i("test","对next事件做出响应  "+integers);
                        for (Integer i : integers) {
                            Log.i("test", "对next事件做出响应  " + i);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void start(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("我是谁", "对subscribe事件作出响应" );
            }

            @Override
            public void onNext(Integer o) {
                Log.d("我是谁", "对Next事件作出响应" + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("我是谁", "对error事件作出响应" );
            }

            @Override
            public void onComplete() {
                Log.d("我是谁", "对complete事件作出响应");
            }
        });
    }

}
