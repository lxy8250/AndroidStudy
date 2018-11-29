package com.lxy.androidstudy.appbar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by 刘晓阳 on 2018/2/1.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;
    private Context context;
    private View convertView;

    public ViewHolder(Context context,View itemView,ViewGroup parentView) {
        super(itemView);
        this.context = context;
        this.convertView = itemView;
        views = new SparseArray<>();
    }

    public static ViewHolder get(Context context,int layoutId,ViewGroup parentView){
        View view = LayoutInflater.from(context).inflate(layoutId,parentView,false);

        ViewHolder holder = new ViewHolder(context,view,parentView);
        return holder;
    }

    public <T extends View> T getView(int viewId){
        View view = views.get(viewId);
        if (view == null){
            view = convertView.findViewById(viewId);
            views.append(viewId,view);
        }

        return (T) view;
    }

    public ViewHolder setText(int viewId,String message){
        TextView tv = getView(viewId);
        tv.setText(message);
        return this;
    }

    public ViewHolder setText(int viewId,Spannable message){
        TextView tv = getView(viewId);
        tv.setText(message);
        return this;
    }

    public ViewHolder setText(int viewId,CharSequence message){
        TextView tv = getView(viewId);
        tv.setText(message);
        return this;
    }

    public ViewHolder setBitmap(int viewId,String url){
        ImageView iv = getView(viewId);
        return this;
    }

    public void setViewOnClickListener(int viewId, View.OnClickListener listener){
        if (listener != null){
            getView(viewId).setOnClickListener(listener);
        }
    }

    public void setViewOnLongClickListener(int viewId, View.OnLongClickListener listener){
        if (listener != null){
            getView(viewId).setOnLongClickListener(listener);
        }
    }
}
