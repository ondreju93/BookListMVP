package pl.wmi.andrzej.booklistmvp.books;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import pl.wmi.andrzej.booklistmvp.R;
import pl.wmi.andrzej.booklistmvp.list.ListPresenter;
import pl.wmi.andrzej.booklistmvp.list.ListView;

public class BookListView extends AppCompatActivity implements ListView<Book> {

    public static final String SELECTED_BOOK = "selectedBook";

    private ListPresenter listPresenter;

    android.widget.ListView booksListView;

    private  BookListAdapter bookListAdapter = new BookListAdapter();

    EditText bookTitleEdit;

    EditText bookAuthorEdit;

    private Button addBookButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_view);

        listPresenter = new BookListPresenter(this);

        booksListView = (android.widget.ListView) findViewById(R.id.booksListView);
        bookTitleEdit = (EditText) findViewById(R.id.bookTitleEdit);
        bookAuthorEdit = (EditText) findViewById(R.id.bookAuthorEdit);

        addBookButton = (Button) findViewById(R.id.addBookButton);
        addBookButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                listPresenter.addButtonClicked();
            }
        });

        booksListView.setAdapter(bookListAdapter);
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                listPresenter.ItemSelected(position);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showAlert(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return super.getApplicationContext();
    }

    @Override
    public void setListItems(List<Book> items) {
        this.bookListAdapter.setBooks(items);
        this.bookListAdapter.notifyDataSetChanged();
        this.booksListView.invalidateViews();
        this.booksListView.refreshDrawableState();
        this.bookTitleEdit.setText(null);
        this.bookAuthorEdit.setText(null);
    }

    @Override
    public Book getNewElementData() {
        final Book newBook = new Book();
        newBook.setTitle(bookTitleEdit.getText().toString());
        newBook.setAuthor(bookAuthorEdit.getText().toString());
        return newBook;
    }

    @Override
    public Book getItemAtPosition(int position) {
        return (Book) booksListView.getItemAtPosition(position);
    }
}
