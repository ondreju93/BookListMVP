package pl.wmi.andrzej.booklistmvp.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import pl.wmi.andrzej.booklistmvp.R;
import pl.wmi.andrzej.booklistmvp.books.Book;
import pl.wmi.andrzej.booklistmvp.list.ListModel;
import pl.wmi.andrzej.booklistmvp.list.ListObserver;

/**
 * Created by andrzej on 07.01.16.
 */
public class SQLiteBookListModel implements ListModel<Book> {

    private final SQLiteDatabase db;
    private final SQLiteOpenHelper databaseOpenHelper;
    private final List<ListObserver> observers = new ArrayList<>();
    private final Context context;

    SQLiteBookListModel(final Context context){
        this.context = context;
        databaseOpenHelper = new SQLiteOpenHelper(context, context.getString(R.string.dbName), null, context.getResources().getInteger(R.integer.dbVersion)) {
            @Override
            public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL(context.getString(R.string.createBooksTable));
            }

            @Override
            public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
                sqLiteDatabase.execSQL(context.getString(R.string.dropTable, context.getString(R.string.bookTableName)));
                this.onCreate(sqLiteDatabase);
            }
        };
        db = this.databaseOpenHelper.getWritableDatabase();
    }

    @Override
    public Book getItem(Long id) {
        Cursor cursor = null;
        Book book = new Book();
        try {
            cursor = this.db.rawQuery(context.getString(R.string.selectBookById), new String[]{id + ""});
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                book.setId(cursor.getLong(cursor.getColumnIndex(context.getString(R.string.idColumn))));
                book.setTitle(cursor.getString(cursor.getColumnIndex(context.getString(R.string.titleColumn))));
                if (!Objects.equals(cursor.getString(cursor.getColumnIndex(context.getString(R.string.authorColumn))), null)) {
                    book.setAuthor(cursor.getString(cursor.getColumnIndex(context.getString(R.string.authorColumn))));
                }
            }
        } finally {
            cursor.close();
        }
        return book;
    }

    @Override
    public List<Book> getItems() {
        Cursor cursor = null;
        Book book = null;
        List<Book> result = new ArrayList<>();

        try {
            cursor = this.db.rawQuery(context.getString(R.string.selectBooks), null);
            //cursor = this.db.rawQuery("SELECT id, title, author FROM " + SQLiteBookListModel.TABLE_NAME, null);
            if (!Objects.equals(cursor, null)) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    book = new Book();
                    book.setId(cursor.getLong(cursor.getColumnIndex(context.getString(R.string.idColumn))));
                    book.setTitle(cursor.getString(cursor.getColumnIndex(context.getString(R.string.titleColumn))));
                    if (!Objects.equals(cursor.getString(cursor.getColumnIndex(context.getString(R.string.authorColumn))), null)) {
                        book.setAuthor(cursor.getString(cursor.getColumnIndex(context.getString(R.string.authorColumn))));
                    }
                    result.add(book);
                    cursor.moveToNext();
                }
            }
        } finally {
            cursor.close();
        }
        return result;
    }

    @Override
    public void addItem(Book book) {
        final ContentValues data = new ContentValues();
        data.put(context.getString(R.string.titleColumn), book.getTitle());
        data.put(context.getString(R.string.authorColumn), book.getAuthor());
        this.db.insert(context.getString(R.string.bookTableName), null, data);
        notifyObservers();
    }

    @Override
    public void deleteItem(Long id) {
        this.db.delete(context.getString(R.string.bookTableName), context.getString(R.string.deleteIdParam), new String[]{id + ""});
        notifyObservers();
    }

    @Override
    public void attach(ListObserver listObserver) {
        if (!Objects.equals(listObserver, null)) {
            observers.add(listObserver);
        }
    }

    private void notifyObservers() {
        for (ListObserver listObserver : observers) {
            listObserver.update();
        }
    }
}
