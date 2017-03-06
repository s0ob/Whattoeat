package com.example.user.project;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.R.attr.id;
import static android.R.attr.name;
import static android.content.ContentValues.TAG;

public class Table extends SQLiteOpenHelper {
    private final Context mHelperContext;
    public static final String DATABASE_NAME="food.db";
    public static final String TABLE_NAME="food_table";
    public static final String COL_1="id";
    public static final String COL_2="name";
    public static final String COL_3="place";
    public Table(Context context) {
        super(context, DATABASE_NAME, null, 1);
        mHelperContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,place TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String place){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,place);
        long result= db.insert(TABLE_NAME,null,contentValues);
        if (result==-1)
            return  false;
        else
            return true;
    }
    public int delData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
      return db.delete(TABLE_NAME,"id = ?",new String[]{id});
    }
    public Cursor getalldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor sd = db.rawQuery("select * from " + TABLE_NAME, null);
        return sd;
    }
    public Cursor getname(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor gn=db.rawQuery("SELECT name FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);
        return gn;
    }
}
