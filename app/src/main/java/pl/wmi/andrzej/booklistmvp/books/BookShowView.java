package pl.wmi.andrzej.booklistmvp.books;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.wmi.andrzej.booklistmvp.R;
import pl.wmi.andrzej.booklistmvp.show.ShowPresenter;
import pl.wmi.andrzej.booklistmvp.show.ShowView;

public class BookShowView extends AppCompatActivity implements ShowView{

    public static final String ANOTHER_BOOK_READ = "Another book read!";

    public static final String UNEXPECTED_ERROR = "Sorry, a problem occured";

    private ShowPresenter presenter;

    private EditText showBookTitleEdit;

    private EditText showBookAuthorEdit;

    private Button alreadyReadButton;

    private Book selectedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_show_view);

        presenter = new BookShowPresenter(this);
        selectedBook = getIntent().getParcelableExtra(BookListView.SELECTED_BOOK);

        showBookTitleEdit = (EditText) findViewById(R.id.showBookTitle);
        showBookTitleEdit.setText(selectedBook.getTitle());
        showBookAuthorEdit = (EditText) findViewById(R.id.showBookAuthor);
        showBookAuthorEdit.setText(selectedBook.getAuthor());

        alreadyReadButton = (Button) findViewById(R.id.alreadyReadButton);
        alreadyReadButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                presenter.deleteButtonClicked();
            }
        });
    }

    @Override
    public void exitView() {
        finish();
    }

    @Override
    public long getBookId() {
        return selectedBook.getId();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showAlert(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}
