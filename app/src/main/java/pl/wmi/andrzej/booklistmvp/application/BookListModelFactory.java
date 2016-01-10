package pl.wmi.andrzej.booklistmvp.application;

import android.content.Context;

import pl.wmi.andrzej.booklistmvp.application.BookListMVPApplication;
import pl.wmi.andrzej.booklistmvp.application.SQLiteBookListModel;
import pl.wmi.andrzej.booklistmvp.books.Book;
import pl.wmi.andrzej.booklistmvp.list.ListModel;
import pl.wmi.andrzej.booklistmvp.list.ListObserver;

/**
 * Created by andrzej on 09.01.16.
 */
public class BookListModelFactory {
    public static ListModel<Book> createSQLiteModel(ListObserver listObserver) {
        return BookListMVPApplication.getInstance().getSqLiteBookListModelWithAttachedObserver(listObserver);
    }
    // below there can be placed methods for getting another ListModel<Book> implementations
}
