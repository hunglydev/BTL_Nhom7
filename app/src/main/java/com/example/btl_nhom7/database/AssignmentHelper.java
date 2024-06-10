package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AssignmentHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Assign";
    private static final String COL_START_TIME = "StartTime";
    private static final String COL_END_TIME = "EndTime";
    private  static final String COL_STATUS = "Status";
    public AssignmentHelper(@Nullable Context context) {
        super(context, ConfigDB.DATABASE_NAME, null, ConfigDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        final String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE " + TABLE_NAME + " ("
                + ClassHelper.getClassID() + " TEXT, "
                + COL_START_TIME + " TEXT, "
                + COL_END_TIME + " TEXT, "
                + RoomHelper.getColID() + " TEXT, "
                + TeacherHelper.getTeacherID() + " Text,"
                + COL_STATUS + " Text,"
                + "PRIMARY KEY (" + ClassHelper.getClassID() + ", " + COL_START_TIME + ", " + RoomHelper.getColID() + "), "
                + "FOREIGN KEY (" + ClassHelper.getClassID() + ") REFERENCES " + ClassHelper.getTableName() + "(" + ClassHelper.getClassID() + "), "
                + "FOREIGN KEY (" + TeacherHelper.getTeacherID() + ") REFERENCES " + TeacherHelper.getTableName() + "(" + TeacherHelper.getTableName() + "), "
                + "FOREIGN KEY (" + RoomHelper.getColID() + ") REFERENCES " + RoomHelper.getColName() + "(" + RoomHelper.getColID() + "));";

        db.execSQL(CREATE_TABLE_ASSIGNMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
