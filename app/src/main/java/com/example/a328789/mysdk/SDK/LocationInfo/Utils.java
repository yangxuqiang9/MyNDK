package com.example.a328789.mysdk.SDK.LocationInfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 328789 on 2016/8/17.
 */
public class Utils {
    /**
     * 获取本地联系人
     */
    public ArrayList<String> getContactList(Context context){

        ArrayList<String> list= new ArrayList<String>();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] columns    = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER};

        Cursor people = context.getContentResolver().query(uri, columns, null, null, null);

        int _nameIndex = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        int _numberIndex = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

        people.moveToFirst();
        do {
            String name   = people.getString(_nameIndex);
            String number = people.getString(_numberIndex);
            list.add(name+" :"+number);
            // Do work...
        } while (people.moveToNext());

        return list;
    }
    /**
     * 打开手机相册
     */
    public void openPhoto(Activity activity, int requestCode){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent,requestCode);
    }
    /**
     *打开手机摄像头获取图片
     */
    public void openCamera(Activity activity,int requestCode){
        String externalStorageState = Environment.getExternalStorageState();
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            activity.startActivityForResult(intent,requestCode);
        }else {
            Toast.makeText(activity.getApplicationContext(),"sd卡没有插入",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 打开手机摄像头获取图片并保存到临时路径
     * @param activity
     * @param uriPath
     * @param requestCode
     */
    public void openVamera(Activity activity,String uriPath,int requestCode){
        //设置一个临时路径，保存所拍的照片
        String TEMP_IMAGE_PATH;
        //获取该路径
        TEMP_IMAGE_PATH= Environment.getExternalStorageDirectory().getPath()+"/"+uriPath+".png";
        //传入ACTION_IMAGE_CAPTURE:该action指向一个照相机app
        Intent intent1=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //创建File并获取它的URI值
        Uri photoUri=Uri.fromFile(new File(TEMP_IMAGE_PATH));
        //MediaStore.EXTRA_OUTPUT为字符串"output"，即将该键值对放进intent中
        intent1.putExtra(MediaStore.EXTRA_OUTPUT,photoUri);
        activity.startActivityForResult(intent1,requestCode);
    }
    /**
     * 在onActivityResult中获取图片的uri
     */
    public Bitmap getImageBitmap(Context context,Intent data){
        Uri photoUri = data.getData();
        Bitmap bitmap=null;
        Bitmap bp=null;
        if(photoUri==null){
            Bundle extras = data.getExtras();
            bp= (Bitmap) extras.get("data");
            bitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), null, true);
        }else{
            Cursor cursor = context.getContentResolver().query(photoUri,
                    new String[] { MediaStore.Images.ImageColumns.ORIENTATION }, null, null, null);
            if(cursor==null){
                return null;
            }
            cursor.moveToFirst();
            int anInt = cursor.getInt(0);
            cursor.close();
            Matrix matrix = new Matrix();
            matrix.postRotate(anInt);
            try {
                bp = MediaStore.Images.Media.getBitmap(context.getContentResolver(),photoUri);
                bitmap = Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmap;
    }
    /**
     * 获取本地相册照片
     */
    public void getLocatPhoto(){

    }
}
