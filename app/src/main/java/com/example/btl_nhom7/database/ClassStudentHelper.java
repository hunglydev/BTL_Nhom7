package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.btl_nhom7.model.Student;

public class ClassStudentHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "ClassStudent";
    public ClassStudentHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, ConfigDB.DATABASE_NAME, null, ConfigDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        final String CREATE_CLASS_STUDENT = "CREATE TABLE " + TABLE_NAME + " ("
                + ClassHelper.getClassID() + " TEXT, "
                + StudentHelper.getStudentID() + " TEXT, "
                + "PRIMARY KEY (" + ClassHelper.getClassID() + ", " + StudentHelper.getStudentID() + "), "
                + "FOREIGN KEY (" + ClassHelper.getClassID() + ") REFERENCES " + ClassHelper.getTableName() + "(" + ClassHelper.getClassID() + "), "
                + "FOREIGN KEY (" + StudentHelper.getStudentID() + ") REFERENCES " + StudentHelper.getTableName() + "(" + StudentHelper.getStudentID() + "));";
        db.execSQL(CREATE_CLASS_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
