package com.miserable.todolist;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ToDoAdapter extends CursorAdapter {

    private LayoutInflater mInflater; //нужен для создания объектов класса View

    public ToDoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View root = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        ViewHolder holder = new ViewHolder();
        TextView tvClassName = (TextView)root.findViewById(android.R.id.text1);
        holder.tvClassName = tvClassName;
        root.setTag(holder);
        return root;
        //return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(ToDoListTable._ID));
        String task = cursor.getString(cursor.getColumnIndex(ToDoListTable.COLUMN_TASK));
        //String classLetter = cur.getString(cur.getColumnIndex(ContractClass.Classes.COLUMN_NAME_CLASS_LETTER));
        ViewHolder holder = (ViewHolder) view.getTag();
        if(holder != null) {
            holder.tvClassName.setText(task);
            holder.classID = id;
        }
    }
}
