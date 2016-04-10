package com.miserable.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class ToDoListOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "todolist.db";

    private static final String CREATE_TABLE_TODOLIST_QUERY = "CREATE TABLE " +
            ToDoListTable.TABLE_NAME + "(" +
            ToDoListTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            ToDoListTable.COLUMN_DATE + " DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
            ToDoListTable.COLUMN_TASK + " TEXT NOT NULL, " +
            ToDoListTable.COLUMN_IS_DONE + " INTEGER NOT NULL"
            +")";

    public static final String DROP_TABLE_TODOLIST_QUERY = "DROP TABLE " + ToDoListTable.TABLE_NAME;


    public ToDoListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODOLIST_QUERY);
        db.execSQL("INSERT INTO todolist(task) VALUES('aaa')");
        Log.d("DBase", "onCreate ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_TODOLIST_QUERY);
        onCreate(db);
    }
}
