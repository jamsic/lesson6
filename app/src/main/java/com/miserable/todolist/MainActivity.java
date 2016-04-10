package com.miserable.todolist;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String TAG = "MainActivity";
    private ListView lvItems;
    private ToDoAdapter toDoAdapter;

    //public static final Uri ENTRIES_URI = Uri.withAppendedPath(ReaderContentProvider.CONTENT_URI, "entries");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate ");
        setContentView(R.layout.list_activity);
        getSupportLoaderManager().initLoader(0, null, this);
        lvItems = (ListView)findViewById(R.id.lvItems);
        toDoAdapter = new ToDoAdapter(this, null, 0);
        lvItems.setAdapter(toDoAdapter);
        Log.d(TAG, "aaa ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String[] projection = new String[]{ToDoListTable._ID,ToDoListTable.COLUMN_TASK};
        Log.d(TAG, "onCreateLoader "+projection.toString()+ToDoListContentProvider.TODOLIST_URI);
        Cursor c = getContentResolver().query(ToDoListContentProvider.TODOLIST_URI, projection, null, null, null);
        if (c == null) {
            Log.d(TAG, "onCreateLoader c == null");
        }
        return new CursorLoader(this, ToDoListContentProvider.TODOLIST_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished ");
        if (data == null) {
            Log.d(TAG, "onLoadFinished data is null");
            Toast.makeText(getApplicationContext(),
                    R.string.no_data_message,
                    Toast.LENGTH_SHORT).show();
        } else {
            toDoAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset ");
        toDoAdapter.swapCursor(null);
    }
}
