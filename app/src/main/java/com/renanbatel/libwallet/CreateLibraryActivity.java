package com.renanbatel.libwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.renanbatel.libwallet.daos.LibWalletDatabase;
import com.renanbatel.libwallet.models.Library;
import com.renanbatel.libwallet.resources.AlterLibraryActivity;

public class CreateLibraryActivity extends AlterLibraryActivity {

    private LibWalletDatabase libWalletDatabase;

    protected void handleFormSubmission( View view) {
        Intent intent   = new Intent();
        Library library = new Library(
            this.fieldName.getText().toString(),
            this.fieldLanguage.getText().toString(),
            this.fieldFeatures.getText().toString(),
            this.fieldDetails.getText().toString(),
            this.fieldRepositories.getText().toString()
        );
        Long id = this.libWalletDatabase.libraryDao().insert( library );

        library.setId( id );
        intent.putExtra( LIBRARY, library );
        setResult( Activity.RESULT_OK, intent );
        finish();
    }

    protected void setupForm() {
        this.labelInstructions.setText( R.string.instructions_create_lib );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.libWalletDatabase = LibWalletDatabase.getDatabase( this );
    }
}
