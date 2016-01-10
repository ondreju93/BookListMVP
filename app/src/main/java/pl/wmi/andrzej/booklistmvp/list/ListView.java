package pl.wmi.andrzej.booklistmvp.list;

import java.util.List;
import pl.wmi.andrzej.booklistmvp.common.PresentableView;

/**
 * Created by andrzej on 10.01.16.
 */
public interface ListView<T> extends PresentableView {
    void setListItems(List<T> data);
    T getNewElementData();
    T getItemAtPosition(final int position);
}
