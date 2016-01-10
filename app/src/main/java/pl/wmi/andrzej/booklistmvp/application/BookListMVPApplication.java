package pl.wmi.andrzej.booklistmvp.application;

import android.app.Application;

import pl.wmi.andrzej.booklistmvp.list.ListObserver;

/**
 * Created by andrzej on 10.01.16.
 */
public class BookListMVPApplication extends Application{
    private static BookListMVPApplication ourInstance;

    private SQLiteBookListModel sqLiteBookListModel;

    public static BookListMVPApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        ourInstance.initialize();
    }

    SQLiteBookListModel getSqLiteBookListModelWithAttachedObserver(ListObserver listObserver){
        sqLiteBookListModel.attach(listObserver);
        return sqLiteBookListModel;
    }

    private void initialize(){
        sqLiteBookListModel = new SQLiteBookListModel(getApplicationContext());
    }
}
