package com.miserable.todolist;

import android.provider.BaseColumns;


interface ToDoListTable extends BaseColumns {

    String TABLE_NAME = "todolist";

    String COLUMN_DATE = "date";
    String COLUMN_TASK = "task";
    String COLUMN_IS_DONE = "iscompleted";

    String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.com.csc.less.todolist";
    String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.com.csc.less.todolist";

    int TODOLIST_ID_POSITION = 1;

    String SORT_ORDER = "date ASC";
}
