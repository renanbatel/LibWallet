package com.renanbatel.libwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.renanbatel.libwallet.models.Library;
import com.renanbatel.libwallet.resources.AlterLibraryActivity;

public class EditLibraryActivity extends AlterLibraryActivity {

    private Library library;

    @Override
    protected void handleFormSubmission(View view) {
        Intent intent   = new Intent();
        Library library = new Library(
            this.library.getId(),
            this.fieldName.getText().toString(),
            this.fieldLanguage.getText().toString(),
            this.fieldFeatures.getText().toString(),
            this.fieldDetails.getText().toString(),
            this.fieldRepositories.getText().toString()
        );

        intent.putExtra( LIBRARY, library );
        setResult( Activity.RESULT_OK, intent );
        finish();
    }

    @Override
    protected void setupForm() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.library = bundle.getParcelable( LIBRARY );

        this.labelInstructions.setText( R.string.instructions_edit_lib );
        this.fieldName.setText( this.library.getName() );
        this.fieldLanguage.setText( this.library.getLanguage() );
        this.fieldFeatures.setText( this.library.getFeatures() );
        this.fieldDetails.setText( this.library.getDetails() );
        this.fieldRepositories.setText( this.library.getRepositories() );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
