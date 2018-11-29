package com.lxy.androidstudy;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 刘晓阳 on 2018/2/1.
 */

public class ToastUtil {

    private Context context;
    private static Toast toast;

    public static void showToast(Context context,String message){
        if (toast == null){
            toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        }
        toast.setText(message);

        toast.show();

    }
}
