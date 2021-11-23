package com.oceanmtech.dmt.NewFile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NeweDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Frame.db";
    public static final String TABLE_NAME = "Frame_table";
    public static final String Data_1 = "ID";
    private static final String Frame = "frame";



    public NeweDataBase(Context context) {
        super(context,DATABASE_NAME, null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,frame BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean InsertData(byte[] frame) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Frame,frame);
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
