package pl.wmi.andrzej.booklistmvp.books;

import android.content.Intent;

import java.util.Objects;

import pl.wmi.andrzej.booklistmvp.application.BookListModelFactory;
import pl.wmi.andrzej.booklistmvp.list.ListModel;
import pl.wmi.andrzej.booklistmvp.list.ListPresenter;
import pl.wmi.andrzej.booklistmvp.list.ListView;

/**
 * Created by andrzej on 10.01.16.
 */
public class BookListPresenter implements ListPresenter {
    private final ListModel<Book> observedModel;
    private final ListView<Book> bookListView;

    public BookListPresenter(BookListView bookListView) {
        this.bookListView = bookListView;
        this.observedModel = BookListModelFactory.createSQLiteModel(this);
    }

    @Override
    public void ItemSelected(final int position) {
        Intent intent = new Intent(bookListView.getContext(), BookShowView.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(BookListView.SELECTED_BOOK, this.bookListView.getItemAtPosition(position));
        bookListView.getContext().startActivity(intent);
    }

    @Override
    public void addButtonClicked() {
        final Book newBook = bookListView.getNewElementData();
        if(!Objects.equals(newBook.getTitle(), "")){
            observedModel.addItem(bookListView.getNewElementData());
            bookListView.showAlert(NEW_BOOK_ADDED);
        } else {
            bookListView.showAlert(EMPTY_TITLE_ERROR);
        }
    }

    @Override
    public void update() {
        bookListView.setListItems(observedModel.getItems());
    }

    public static final String EMPTY_TITLE_ERROR = "Tytuł nie może być pusty!!!";

    public static final String NEW_BOOK_ADDED = "Dodano nową pozycję!";
}
