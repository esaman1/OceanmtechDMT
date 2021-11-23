package com.oceanmtech.dmt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Business Card.db";
    public static final String TABLE_NAME = "Store_table";
    public static final String Data_1 = "ID";
    private static final String Data = "logo";

    public static final String Data_2 = "businessname";
    public static final String Data_3 = "Emailaddress";
    public static final String Data_4 = "Website";
    public static final String Data_5 = "Address";
    public static final String Data_6 = "MobileNo";
    public static final String Data_7 = "bid";


    public DataBase(Context context) {
        super(context,DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,logo BLOB,businessname String,Emailaddress String,Website String,Address String,MobileNo String,bid String)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean InsertData(byte[] logo ,String businessname, String Emailaddress,String Website,String Address,String MobileNo,String bid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Data,logo);
        contentValues.put(Data_2,businessname);
        contentValues.put(Data_3,Emailaddress);
        contentValues.put(Data_4,Website);
        contentValues.put(Data_5,Address);
        contentValues.put(Data_6,MobileNo);
        contentValues.put(Data_7,bid);
        long result = db.insert(TABLE_NAME,null ,contentValues);


        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor retriveData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }


    public int deleteData(String id) {
        SQLiteDatabase database = getWritableDatabase();
        int a = database.delete(TABLE_NAME, "ID = ?", new String[]{id});
        database.close();
        return a;
    }

    public int deleteDataallimage(String image_id) {
        SQLiteDatabase database = getWritableDatabase();
        int b = database.delete(TABLE_NAME, "Image_id = ?", new String[]{image_id});
        database.close();
        return b;
    }


}
