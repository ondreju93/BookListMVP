package pl.wmi.andrzej.booklistmvp.common;

import android.content.Context;

/**
 * Created by andrzej on 07.01.16.
 */
public interface PresentableView {
    Context getContext();
    void showAlert(final String message);
}
