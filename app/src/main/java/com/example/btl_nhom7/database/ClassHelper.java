package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClassHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TrucNhat.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Class";
    private static final String COL_ID = "ID";
    private static final String COL_NAME = "Name";
    private static final String COL_ID_TEACHER = "ID Teacher";
    public ClassHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static String getTableName() {
        return  TABLE_NAME;
    }

    public static String getClassID() {
        return COL_ID;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY, "
                + COL_NAME + " TEXT, "
                + COL_ID_TEACHER + " TEXT, "
                + "FOREIGN KEY (" + COL_ID_TEACHER + ") REFERENCES " + TeacherHelper.getTableName() + "(" + TeacherHelper.getTeacherID() + "));";
        db.execSQL(CREATE_TABLE_CLASS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
