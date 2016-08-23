package com.example.a328789.mysdk.SDK.LocationInfo;

import android.text.TextUtils;

/**
 * Created by 328789 on 2016/8/23.
 */
public class LogUtils {
    private static boolean log_switch=true;
    public enum RankStatus{
        e,d,v,w,i
    }
    public static void e(String tag,Object msg){
        log(tag,msg,RankStatus.e);
    }
    public static void d(String tag,Object msg){
        log(tag,msg,RankStatus.d);
    }
    public static void v(String tag,Object msg){
        log(tag,msg,RankStatus.v);
    }
    public static void w(String tag,Object msg){
        log(tag,msg,RankStatus.w);
    }
    public static void i(String tag,Object msg){
        log(tag,msg,RankStatus.i);
    }
    private static void log(String tag, Object msg,RankStatus level){
        String message="异常信息为null";
        String mesg=msg.toString();
        if(!TextUtils.isEmpty(mesg)){
            message=mesg;
        }

    }
}
