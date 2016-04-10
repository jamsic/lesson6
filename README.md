# It`s NOT working!

У меня так и не получилось заставить это заработать.

Он доходит до создания провайдера:

    @Override
    public boolean onCreate() {
        dbhelper = new ToDoListOpenHelper(getContext());
        Log.d("Provider", "onCreate "); <- есть в логе
        return true;
    }

но не доходит до создания бд в ToDoListOpenHelper:

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODOLIST_QUERY);
        db.execSQL("INSERT INTO todolist(task) VALUES('aaa')");
        Log.d("DBase", "onCreate "); <- нет в логе
    }

И сообщений об ошибках не выдаёт. Я не осознаю, как так происходит, ведь по идее он должен вызывать onCreate сразу после создания new ToDoListOpenHelper, нет? Объясните, пожалуйста, где что нужно дописать и доделать, я не понимаю.
