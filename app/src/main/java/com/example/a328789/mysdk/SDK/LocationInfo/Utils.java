package com.example.a328789.mysdk.SDK.LocationInfo;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

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
}
