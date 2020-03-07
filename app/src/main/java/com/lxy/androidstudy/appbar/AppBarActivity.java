package com.lxy.androidstudy.appbar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lxy.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lxy on 2018/11/29.
 */

public class AppBarActivity extends AppCompatActivity {

    private RecyclerView rvApp;
    private List<String> list = new ArrayList<>();
    private TestAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);

        initView();
    }

    private void initView() {
        toolbar = findViewById(R.id.appToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvApp = findViewById(R.id.appRV);
        adapter = new TestAdapter(this,list,R.layout.item_appbar);
        rvApp.setLayoutManager(new LinearLayoutManager(this));
        rvApp.setAdapter(adapter);
        for (int i = 0; i < 10; i++){
            list.add("item " + i);
        }
        adapter.notifyDataSetChanged();
    }

    class TestAdapter extends BaseAdapter<String>{

        public TestAdapter(Context context, List<String> list, int layoutId) {
            super(context, list, layoutId);
        }

        @Override
        protected void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.item_appbar_tv,s);
        }
    }

}
