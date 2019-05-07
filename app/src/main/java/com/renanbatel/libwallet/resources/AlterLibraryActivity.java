package com.renanbatel.libwallet.resources;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.renanbatel.libwallet.R;

public abstract class AlterLibraryActivity extends BaseActivity {

    public static final String LIBRARY = "LIBRARY";

    protected Button buttonSave;
    protected EditText fieldName;
    protected EditText fieldLanguage;
    protected EditText fieldFeatures;
    protected EditText fieldDetails;
    protected EditText fieldRepositories;
    protected TextView labelInstructions;

    protected abstract void handleFormSubmission( View view );
    protected abstract void setupForm();

    private void loadForm() {
        this.buttonSave        = findViewById(R.id.buttonSave);
        this.fieldName         = findViewById( R.id.fieldName );
        this.fieldLanguage     = findViewById( R.id.fieldLanguage );
        this.fieldFeatures     = findViewById( R.id.fieldFeatures );
        this.fieldDetails      = findViewById( R.id.fieldDetails );
        this.fieldRepositories = findViewById( R.id.fieldRepositories );
        this.labelInstructions = findViewById( R.id.labelInstructions);

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                handleFormSubmission( view );
            }
        });
        this.setupForm();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_library);
        this.setupToolbar( true );
        this.loadForm();
    }
}
