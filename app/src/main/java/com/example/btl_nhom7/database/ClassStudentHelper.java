package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.btl_nhom7.model.Student;

public class ClassStudentHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TrucNhat.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "ClassStudent";
    private static final String COL_IDSTUDENT = "IdStudent";
    private static final String COL_IDCLASS = "IdClass";
    public ClassStudentHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_CLASS_STUDENT = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_IDCLASS + " TEXT, "
                + COL_IDSTUDENT + " TEXT, "
                + "PRIMARY KEY (" + COL_IDCLASS + ", " + COL_IDSTUDENT + "), "
                + "FOREIGN KEY (" + COL_IDCLASS + ") REFERENCES " + ClassHelper.getTableName() + "(" + ClassHelper.getClassID() + "), "
                + "FOREIGN KEY (" + COL_IDSTUDENT + ") REFERENCES " + StudentHelper.getTableName() + "(" + StudentHelper.getStudentID() + "));";
        db.execSQL(CREATE_CLASS_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
