package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AssignmentHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TrucNhat.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Assign";
    private static final String COL_CLASS_ID = "ClassID";
    private static final String COL_ROOM_ID = "RoomID";
    private static final String COL_START_TIME = "StartTime";
    private static final String COL_END_TIME = "EndTime";
    public AssignmentHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_CLASS_ID + " TEXT, "
                + COL_START_TIME + " TEXT, "
                + COL_END_TIME + " TEXT, "
                + COL_ROOM_ID + " TEXT, "
                + "PRIMARY KEY (" + COL_CLASS_ID + ", " + COL_START_TIME + ", " + COL_END_TIME + ", " + COL_ROOM_ID + "), "
                + "FOREIGN KEY (" + COL_CLASS_ID + ") REFERENCES " + ClassHelper.getTableName() + "(" + ClassHelper.getClassID() + "), "
                + "FOREIGN KEY (" + COL_ROOM_ID + ") REFERENCES " + RoomHelper.getColName() + "(" + RoomHelper.getColID() + "));";
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
