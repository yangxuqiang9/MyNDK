package com.example.a328789.mysdk.SDK.LocationInfo;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by 328789 on 2016/8/23.
 * 配置文件的工具类
 */
public class PropertiesUtils {
    private static final String TAG=PropertiesUtils.class.getSimpleName();
    private static Properties propertie;
    /**
     * 打开需要操作的配置文件
     */
    public Properties openProperties(String path){
        Properties props = new Properties();
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
                FileInputStream fileInputStream = new FileInputStream(file);
                props.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG,path+"文件创建失败");
            }
        }
        return props;
    }
    /**
     * 添加配置文件内容
     */
    public void setPropertie(String name,String value){
        if(propertie==null){
            Log.e(TAG,"打开配置文件");
        }
        propertie.setProperty(name,value);
    }
    /**
     * 添加配置文件内容
     */
    public String getPropertie(String name){
        if(propertie==null){
            Log.e(TAG,"打开配置文件");
        }
        return propertie.getProperty(name);
    }
    /**
     * 删除配置文件
     */
    public void delet(String path){
        File file = new File(path);
        if(file.exists()){
            file.delete();
        }
    }
}
