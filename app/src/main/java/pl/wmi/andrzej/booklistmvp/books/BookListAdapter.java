package pl.wmi.andrzej.booklistmvp.books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by andrzej on 08.01.16.
 */
public class BookListAdapter extends BaseAdapter {

    private List<Book> books = new ArrayList<>();

    BookListAdapter(List<Book> books){
        this.books = books;
    }

    public BookListAdapter(){}

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return books.get(i).getId() != null ? books.get(i).getId() : -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (Objects.equals(convertView, null)){
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        TextView text1 = (TextView) convertView.findViewById(android.R.id.text1);
        text1.setText(books.get(position).getTitle());
        return convertView;
    }
}
