package com.example.adriamartinez.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;

/**
 * Created by adriamartinez on 27/01/2017.
 */

public class UserHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;

    public static final String DATABASE_NAME = "Users";

    public static final String USER_TABLE ="User";

    public static final String USER_TABLE_CREATE = "CREATE TABLE " + USER_TABLE + " (name TEXT PRIMARY KEY UNIQUE, points INTEGER);";

    public UserHelper(Context context){ super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(USER_TABLE_CREATE);
    }

    public void createUser (ContentValues values, String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(tableName,null,values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2){

    }
    public Cursor getUsersByName(String Name){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"name"};
        String[] where = {Name};
        Cursor c = db.query(USER_TABLE,
                columns,
                "name=?",
                where,
                null,
                null,
                null);
        return c;
    }
    public void check_login(String name) {
        Cursor c = getUsersByName(name);
        if (!c.moveToFirst()) {
            ContentValues valuesToStore = new ContentValues();
            valuesToStore.put("name", name);
            valuesToStore.put("points", 0);
            createUser(valuesToStore, "User");
        }
    }

}
