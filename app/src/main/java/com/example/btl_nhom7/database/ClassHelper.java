package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClassHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Class";
    private static final String COL_ID = "ClassID";
    private static final String COL_NAME = "Name";

    public ClassHelper(@Nullable Context context) {
        super(context, ConfigDB.DATABASE_NAME, null, ConfigDB.DATABASE_VERSION);
    }


    public static String getTableName() {
        return  TABLE_NAME;
    }

    public static String getClassID() {
        return COL_ID;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY, "
                + COL_NAME + " TEXT, "
                + TeacherHelper.getTeacherID() + " TEXT, "
                + "FOREIGN KEY (" + TeacherHelper.getTeacherID() + ") REFERENCES " + TeacherHelper.getTableName() + "(" + TeacherHelper.getTeacherID() + "));";
        db.execSQL(CREATE_TABLE_CLASS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
