package pl.wmi.andrzej.booklistmvp.show;

import pl.wmi.andrzej.booklistmvp.common.PresentableView;

/**
 * Created by andrzej on 10.01.16.
 */
public interface ShowView extends PresentableView{
    void exitView();
    long getBookId();
}
