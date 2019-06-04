package com.renanbatel.libwallet.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.renanbatel.libwallet.R;

public class GUI {

    public static void confirmDialog(
        Context context,
        String message,
        DialogInterface.OnClickListener listener
    ) {
        new AlertDialog
            .Builder( context )
            .setMessage( message )
            .setPositiveButton( R.string.yes_label, listener )
            .setNegativeButton( R.string.no_label, listener )
            .create()
            .show();
    }
}
