package com.example.a328789.mysdk.SDK.TOOLS;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by 328789 on 2016/8/17.
 */
public class ScreenUtils {
    /**
     *根据手机分辨路将dp单位转换为px
     */
    public static int dp2px(Context context,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(scale*dp+0.5f);
    }
    /**
     * 根据手机分辨率将px单位转换为dp
     */
    public static int px2dp(Context context,int px){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px/scale+0.5f);

    }

    /**
     * 截取当前屏幕，不包括状态栏
     * @param activity
     * @return
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity){
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int top = rect.top;
        int width = rect.width();
        int height = rect.height();
        Bitmap bitmap = Bitmap.createBitmap(drawingCache, 0, top, width, height - top);
        decorView.destroyDrawingCache();
        return bitmap;
    }
}
