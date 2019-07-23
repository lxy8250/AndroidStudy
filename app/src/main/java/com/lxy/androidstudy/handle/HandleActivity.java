package com.lxy.androidstudy.handle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lxy.androidstudy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Creator : lxy
 * date: 2019/7/21
 */
public class HandleActivity extends AppCompatActivity {


    @BindView(R.id.handle_tv_message)
    TextView tvMessage;

    Handler threadHandler;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    tvMessage.setText((CharSequence) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
        ButterKnife.bind(this);

        initThreadHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void initThreadHandler() {
        new Thread(()->{
            Looper.prepare();
            threadHandler = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    switch (msg.what){
                        case 1:
                            Log.i("threadHandler",(CharSequence) msg.obj + "23333");
                    }
                    return false;
                }
            });
            Looper.loop();
        }).start();
    }

    @OnClick(R.id.handle_btn)
    public void sendHandleMessage(View view){
        new Thread(() -> {
            Message message = Message.obtain();
            message.what = 1;
            message.obj = "我是可爱的小老鼠";

            threadHandler.sendMessage(message);
        }).start();
    }
}
