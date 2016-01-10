package pl.wmi.andrzej.booklistmvp.books;

import java.util.Objects;

import pl.wmi.andrzej.booklistmvp.application.BookListModelFactory;
import pl.wmi.andrzej.booklistmvp.list.ListModel;
import pl.wmi.andrzej.booklistmvp.show.ShowPresenter;

/**
 * Created by andrzej on 10.01.16.
 */
public class BookShowPresenter implements ShowPresenter {
    private final BookShowView bookShowView;
    private final ListModel<Book> observedModel;

    public BookShowPresenter(final BookShowView bookShowView) {
        this.bookShowView = bookShowView;
        this.observedModel = BookListModelFactory.createSQLiteModel(this);
    }

    @Override
    public void deleteButtonClicked() {
        final Long bookId = bookShowView.getBookId();
        if(!Objects.equals(bookId, null) && bookId >= 0 ) {
            observedModel.deleteItem(bookShowView.getBookId());
        } else {
            bookShowView.showAlert(BookShowView.UNEXPECTED_ERROR);
            bookShowView.exitView();
        }
    }

    @Override
    public void update() {
        bookShowView.showAlert(BookShowView.ANOTHER_BOOK_READ);
        bookShowView.exitView();
    }
}
