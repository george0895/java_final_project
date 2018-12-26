package com.example.user.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends  SQLiteOpenHelper {
    private static final String name = "mbdatabase.db";
    private static final int version = 1;
    MyDBHelper(Context context) {
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE myTable(number text PRIMARY KEY, TeamA text NOT NULL,TeamB text NOT NULL,scoreA integar NOT NULL,scoreB integar NOT NULL )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS myTable");
        onCreate(db);
        
        
    }
}
