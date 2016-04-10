package com.miserable.todolist;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;


public class ToDoListContentProvider extends ContentProvider {

    public static final String AUTHORITY = "com.scs.lesson6";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final Uri TODOLIST_URI = Uri.parse(AUTHORITY + "/todolist");

    public static final int TODOLIST = 1;
    public static final int TODOLIST_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "/todolist", TODOLIST);
        uriMatcher.addURI(AUTHORITY, "/todolist/#", TODOLIST_ID);
    }

    private ToDoListOpenHelper dbhelper;

    @Override
    public boolean onCreate() {
        dbhelper = new ToDoListOpenHelper(getContext());
        Log.d("Provider", "onCreate ");
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case TODOLIST:
                builder.setTables(ToDoListTable.TABLE_NAME);
                break;
            case TODOLIST_ID:
                builder.setTables(ToDoListTable.TABLE_NAME);
                builder.appendWhere(ToDoListTable._ID + "=" + uri.getPathSegments().get(ToDoListTable.TODOLIST_ID_POSITION));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        Cursor a = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        Log.d("Provider", "query ");
        return a;
        //return null;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TODOLIST:
                return ToDoListTable.CONTENT_TYPE;
            case TODOLIST_ID:
                return ToDoListTable.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        String tableName;
        switch (uriMatcher.match(uri)) {
            case TODOLIST:
                tableName = ToDoListTable.TABLE_NAME;
                break;
            case TODOLIST_ID:
                default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        long rowId = dbhelper.getWritableDatabase().insert(tableName, null, values);
        Uri inserted = ContentUris.withAppendedId(uri, rowId);
        getContext().getContentResolver().notifyChange(inserted, null);
        return inserted;

        //return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case TODOLIST:
                count = db.delete(ToDoListTable.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

        //return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case TODOLIST:
                count = db.update(ToDoListTable.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
