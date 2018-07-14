package com.example.towhid.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class DbControl extends SQLiteOpenHelper {
    public DbControl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS STUDENT ( ID INTEGER PRIMARY KEY,FIRSTNAME TEXT UNIQUE ,LASTNAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void insertStudent(String firstName,String lastName) {
        ContentValues contentValues =new ContentValues();
        contentValues.put("FIRSTNAME",firstName);
        contentValues.put("LASTNAME",lastName);
        this.getWritableDatabase().insertOrThrow("STUDENT","",contentValues);
    }
    public void deleteStudent(String firstName) {
        this.getWritableDatabase().delete("STUDENT","FIRSTNAME='"+firstName+"'",null);
    }
    public void update_student(String old_firstName,String new_firstName) {
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET FIRSTNAME='"+new_firstName+"'WHERE FIRSTNAME='"+old_firstName+"'");
    }
    public void list_all_student(TextView textView){
        Cursor cursor=this.getReadableDatabase().rawQuery("SELECT*FROM STUDENT",null);
        textView.setText("");
        while (cursor.moveToNext()){
            textView.append(cursor.getString(1)+" "+cursor.getString(2)+"\n");
        }
    }
}
