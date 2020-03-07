package com.lxy.androidstudy.customView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxy.androidstudy.R;


public class ItemCustomFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;


    public static ItemCustomFragment newInstance(String param1) {
        ItemCustomFragment fragment = new ItemCustomFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getLayoutIdByKey(mParam1), container, false);
    }

    private int getLayoutIdByKey(String key){
        int layoutId = 0;
        switch (key){
            case "流式布局":
                layoutId = R.layout.fragment_item_coustom;
                break;
            case "custom_item_flow":
                layoutId = R.layout.coustom_item_flow;
                break;
        }
        return layoutId;
    }

}
