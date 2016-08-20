package com.example.a328789.mysdk.SDK.TOOLS;

import android.content.Context;

/**
 * Created by 328789 on 2016/8/17.
 */
public class ScreenUtils {
    /**
     *根据手机分辨路将dp单位转换为px
     */
    public int dp2px(Context context,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(scale*dp+0.5f);
    }
    /**
     * 根据手机分辨率将px单位转换为dp
     */
    public int px2dp(Context context,int px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale+0.5f);

    }
}
