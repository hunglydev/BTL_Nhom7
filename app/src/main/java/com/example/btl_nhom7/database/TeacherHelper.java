package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TeacherHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TrucNhat.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Teacher";
    private static final String COL_ID = "ID";
    private static final String COL_PASSWORD = "Password";
    private static final String COL_NAME = "Name";
    public TeacherHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static String getTableName() {
        return TABLE_NAME;
    }

    // Phương thức getter cho COL_ID
    public static String getTeacherID() {
        return COL_ID;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_TEACHER = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY, "
                + COL_NAME + " TEXT, "
                + COL_PASSWORD + " TEXT);";
        db.execSQL(CREATE_TABLE_TEACHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
