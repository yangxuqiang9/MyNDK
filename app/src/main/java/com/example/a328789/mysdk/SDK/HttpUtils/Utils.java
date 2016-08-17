package com.example.a328789.mysdk.SDK.HttpUtils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by 328789 on 2016/8/17.
 */
public class Utils {
    public enum NetType{

        //网络连接类型为wifi
        WIFI,
        //蓝牙连接
        BLUETOOTH,
        //连接了vpn
        VPN,
        //4G网络
        LTE,
        //2g3g网
        OTHER,
        //非wifi网络
        MOBILE,
        //没有网络
        NOTNET

    }
    /**
     * 判断网络有没有连接
     */
    public boolean wifiIsOpen(Activity activity){
        Application context = activity.getApplication();
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        for(int i=0;i<allNetworkInfo.length;i++){
            if(allNetworkInfo[i].getState()==NetworkInfo.State.CONNECTED){
                return true;
            }
        }
        return false;
    }
    /**
     * 判断网络连接的类型
     */
    public NetType getNetType(Activity activity){
        Application application = activity.getApplication();
        ConnectivityManager systemService = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
        if(activeNetworkInfo==null){
            return NetType.NOTNET;
        }
        int type = activeNetworkInfo.getType();
        switch (type){
            case ConnectivityManager.TYPE_BLUETOOTH:
                return NetType.BLUETOOTH;
            case ConnectivityManager.TYPE_WIFI:
                return NetType.WIFI;
            case ConnectivityManager.TYPE_MOBILE:
                int subtype = activeNetworkInfo.getSubtype();
                if(subtype==TelephonyManager.NETWORK_TYPE_LTE){
                    return NetType.LTE;
                }else {
                    return NetType.OTHER;
                }
            default:
                return NetType.MOBILE;

        }
    }
    /**
     * 判断网络是否处于漫游状态
     */
    public boolean isRoaming(Activity activity){
        TelephonyManager telephonyManager = (TelephonyManager) activity.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.isNetworkRoaming();
    }
}
