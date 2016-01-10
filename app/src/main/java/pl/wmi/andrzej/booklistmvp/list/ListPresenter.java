package pl.wmi.andrzej.booklistmvp.list;

/**
 * Created by andrzej on 10.01.16.
 */

public interface ListPresenter extends ListObserver {
    void ItemSelected(final int position);
    void addButtonClicked();
}
