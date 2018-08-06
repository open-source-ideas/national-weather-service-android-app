package org.osii.nwsapp.util;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Toast;

public final class DialogFactory {

    public static void showError(View view, @StringRes int msg) {
        showError(view, view.getContext().getString(msg));
    }

    public static void showError(View view, String msg) {
        Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
//        Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
//        snackbar.setAction(R.string.ok, view1 -> snackbar.dismiss());
//        snackbar.show();
    }
}
