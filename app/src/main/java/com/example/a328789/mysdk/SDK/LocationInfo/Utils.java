package com.example.a328789.mysdk.SDK.LocationInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;

/**
 * Created by 328789 on 2016/8/17.
 */
public class Utils {
    /**
     * 获取本地联系人
     */
    public void getPhoneContact(Context context){
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor query = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);


    }
}
