package com.lxy.androidstudy.customView;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.lxy.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

public class CoustomViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager pager;
    private CustomAdapter adapter;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coustom_view);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        tabLayout = findViewById(R.id.coustom_tab);
        pager = findViewById(R.id.coustom_pager);
        adapter = new CustomAdapter(getSupportFragmentManager(),list);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(adapter);
    }

    private void initListener() {

    }

    private void initData(){
        list.add("流式布局");
        list.add("custom_item_flow");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {

    }
}
