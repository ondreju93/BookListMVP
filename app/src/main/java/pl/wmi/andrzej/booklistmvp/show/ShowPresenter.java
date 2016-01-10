package pl.wmi.andrzej.booklistmvp.show;

import pl.wmi.andrzej.booklistmvp.books.Book;
import pl.wmi.andrzej.booklistmvp.list.ListObserver;

/**
 * Created by andrzej on 10.01.16.
 */
public interface ShowPresenter extends ListObserver{
    void deleteButtonClicked();
}
