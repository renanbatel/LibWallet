package com.renanbatel.libwallet.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.renanbatel.libwallet.R;

public class LibraryDetailsDialog extends AppCompatDialogFragment {

    public static final String DETAILS      = "DETAILS";
    public static final String REPOSITORIES = "REPOSITORIES";

    private TextView libraryDetails;
    private TextView libraryRepositories;

    private void setupLibraryDetailsView( View view ) {
        Bundle bundle       = getArguments();
        String details      = bundle.getString( DETAILS );
        String repositories = bundle.getString( REPOSITORIES );

        this.libraryDetails = view.findViewById( R.id.libraryDetails );
        this.libraryRepositories = view.findViewById( R.id.libraryRepositories );

        if ( details.isEmpty() ) {
            this.libraryDetails.setText( getString( R.string.label_no_details ) );
        } else {
            this.libraryDetails.setText( details );
        }

        if ( repositories.isEmpty() ) {
            this.libraryRepositories.setText( getString( R.string.label_no_repositories ) );
        } else {
            this.libraryRepositories.setText( repositories );
        }
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState ) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater     = getActivity().getLayoutInflater();
        View view                   = inflater.inflate( R.layout.dialog_library_details, null );

        builder
            .setView( view )
            .setTitle( R.string.label_details )
            .setPositiveButton(R.string.button_text_close, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        this.setupLibraryDetailsView( view );

        return builder.create();
    }
}
