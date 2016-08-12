package com.example.a328789.mysdk.SDK.TOOLS;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 328789 on 2016/8/12.
 * 对toast进行的一些封装
 */
public class ToastTools {

    private Toast toast;

    /**
     * 使用此方法弹出的toast不会连续多次显示
     * 显示时间永远是点击后设置的时常
     */
    public void showToast(Context context,int time,String message){
        if(toast==null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setDuration(time);
            toast.show();
        }else {
            toast.setText(message);
        }
    }
}
