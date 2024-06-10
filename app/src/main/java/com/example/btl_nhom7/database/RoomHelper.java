package com.example.btl_nhom7.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RoomHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Room";
    private static final String COL_ID = "RoomID";

    private static final String COL_NAME = "RoomName";
    private  static final String COL_DEVICE = "Devices";
    private static final String  COL_TASK = "Task";
    private static final String COL_METHOD = "Method";
    public static String getColID(){
        return COL_ID;
    }
    public static String getColName(){
        return COL_NAME;
    }
    public static String getTableName(){
        return  TABLE_NAME;
    }
    public RoomHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, ConfigDB.DATABASE_NAME, null, ConfigDB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        final String CREATE_TABLE_ROOM = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID + " TEXT PRIMARY KEY, "
                + COL_NAME + " TEXT, "
                + COL_DEVICE + " TEXT, "
                + COL_TASK + " TEXT, "
                + COL_METHOD + " INTEGER);";

        db.execSQL(CREATE_TABLE_ROOM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
