package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class StudentHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Student";
    private static final String COL_ID = "StudentID";
    private static final String COL_PASSWORD = "Password";
    private static final String COL_NAME = "Name";
    private static final String COL_RATING = "Rating";
    public static String getStudentID(){
        return  COL_ID;
    }

    public StudentHelper(@Nullable Context context) {
        super(context, ConfigDB.DATABASE_NAME, null, ConfigDB.DATABASE_VERSION);
    }

    public static String getTableName() {
        return TABLE_NAME;
    }
    public static String getColId(){return  COL_ID; }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        final String CREATE_TABLE_SINHVIEN = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY, "
                + COL_NAME + " TEXT, "
                + COL_PASSWORD + " TEXT, "
                + COL_RATING + " INTEGER);";
        db.execSQL(CREATE_TABLE_SINHVIEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
