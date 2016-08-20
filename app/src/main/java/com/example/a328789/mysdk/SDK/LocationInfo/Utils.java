package com.example.a328789.mysdk.SDK.LocationInfo;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;

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
     *
     */
    /**
     * 在onActivityResult中获取图片的uri
     */
    public Bitmap getImageUri(Context context,Intent data){
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
