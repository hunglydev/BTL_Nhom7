package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqlHelper extends SQLiteOpenHelper {
    private static String DATABASE = "TrucNhat.db";
    public static final int DATABASE_VERSION = 1;

    private static String TABLE_STUDENT = "Student";
    private static String TABLE_TEACHER = "Teacher";
    private static String TABLE_ROOM = "Room";
    private static String TABLE_CLASS_STUDENT = "ClassStudent";
    private static  String TABLE_CLASS = "Class";
    private static String TABLE_ASSIGNMENT = "Assignment";


    public SqlHelper(@Nullable Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
        final String CREATE_TABLE_SINHVIEN = "CREATE TABLE " + TABLE_STUDENT + " ("
                + "ID" + " TEXT PRIMARY KEY, "
                + "Name" + " TEXT, "
                + "Password" + " TEXT, "
                + "Rating" + " INTEGER);";
        db.execSQL(CREATE_TABLE_SINHVIEN);
        final String CREATE_TABLE_TEACHER = "CREATE TABLE " + TABLE_TEACHER + " ("
                + "ID" + " TEXT PRIMARY KEY, "
                + "Name" + " TEXT, "
                + "Password" + " TEXT);";
        db.execSQL(CREATE_TABLE_TEACHER);
        final String CREATE_TABLE_ROOM = "CREATE TABLE " + TABLE_ROOM + " ("
                + "ID" + " TEXT PRIMARY KEY, "
                + "Name" + " TEXT, "
                + "Devices" + " TEXT, "
                + "Task" + " TEXT, "
                + "Method" + " INTEGER);";
        db.execSQL(CREATE_TABLE_ROOM);
        final String CREATE_TABLE_CLASS = "CREATE TABLE " + TABLE_CLASS + " ("
                + "ID" + " TEXT PRIMARY KEY, "
                + "Name" + " TEXT, "
                + "TeacherID"+ " TEXT, "
                + "FOREIGN KEY (" + "TeacherID" + ") REFERENCES " + TABLE_TEACHER + "(" + "TeacherID" + "));";
        db.execSQL(CREATE_TABLE_CLASS);
        final String CREATE_CLASS_STUDENT = "CREATE TABLE " + TABLE_CLASS_STUDENT + " ("
                + "ClassID" + " TEXT, "
                + "StudentID" + " TEXT, "
                + "PRIMARY KEY (" + "ClassID" + ", " + "StudentID" + "), "
                + "FOREIGN KEY (" + "ClassID" + ") REFERENCES " + TABLE_CLASS + "(" + "ClassID" + "), "
                + "FOREIGN KEY (" + "StudentID" + ") REFERENCES " + TABLE_STUDENT + "(" + "StudentID" + "));";
        db.execSQL(CREATE_CLASS_STUDENT);
        final String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE " + TABLE_ASSIGNMENT + " ("
                + "ClassID" + " TEXT, "
                + "StartTime" + " TEXT, "
                + "EndTime" + " TEXT, "
                + "RoomID" + " TEXT, "
                + "TeacherID" + " Text,"
                + "Status" + " INTERGER,"
                + "PRIMARY KEY (" + "ClassID" + ", " + "StartTime" + ", " + "RoomID" + "), "
                + "FOREIGN KEY (" + "ClassID" + ") REFERENCES " + TABLE_CLASS + "(" + "ClassID" + "), "
                + "FOREIGN KEY (" + "TeacherID" + ") REFERENCES " + TABLE_TEACHER + "(" + "TeacherID" + "), "
                + "FOREIGN KEY (" + "RoomID" + ") REFERENCES " + TABLE_ROOM + "(" + "RoomID"+ "));";
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEACHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROOM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENT);
        onCreate(db);

    }
}
