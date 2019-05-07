package com.renanbatel.libwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.renanbatel.libwallet.dialogs.LibraryDetailsDialog;
import com.renanbatel.libwallet.models.Library;
import com.renanbatel.libwallet.resources.BaseActivity;

public class ViewLibraryActivity extends BaseActivity {

    public static final String LIBRARY  = "LIBRARY";
    public static final String POSITION = "POSITION";
    public static final String ACTION   = "ACTION";
    // request codes
    public static final int EDIT = 1;
    // actions
    public static final int UPDATE = 1;
    public static final int DELETE = 2;

    private Library library;
    private Button buttonDetails;
    private TextView libraryName;
    private TextView libraryLanguage;
    private TextView libraryFeatures;
    private Boolean libraryHasUpdated;

    private void updateLibraryView() {
        this.libraryName.setText( this.library.getName() );
        this.libraryLanguage.setText( this.library.getLanguage() );
        this.libraryFeatures.setText( this.library.getFeatures() );
    }

    private void setupLibraryView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.libraryHasUpdated = false;
        this.library           = bundle.getParcelable( LIBRARY );
        this.buttonDetails     = findViewById( R.id.buttonDetails );
        this.libraryName       = findViewById( R.id.libraryName );
        this.libraryLanguage   = findViewById( R.id.libraryLanguage );
        this.libraryFeatures   = findViewById( R.id.libraryFeatures );

        this.updateLibraryView();
        this.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LibraryDetailsDialog libraryDetailsDialog = new LibraryDetailsDialog();
                Bundle arguments                          = new Bundle();

                arguments.putString( LibraryDetailsDialog.DETAILS, library.getDetails() );
                arguments.putString( LibraryDetailsDialog.REPOSITORIES, library.getRepositories() );
                libraryDetailsDialog.setArguments( arguments );
                libraryDetailsDialog.show( getSupportFragmentManager(), "libraryDetailsDialog" );
            }
        });
    }

    private void handleEdit() {
        Intent intent = new Intent( this, EditLibraryActivity.class );

        intent.putExtra( EditLibraryActivity.LIBRARY, this.library );
        startActivityForResult( intent, EDIT );
    }

    private void handleLibraryResult() {
        Intent intent       = getIntent();
        Intent resultIntent = new Intent();
        Bundle bundle       = intent.getExtras();
        int position        = bundle.getInt( POSITION );

        resultIntent.putExtra( LIBRARY, this.library );
        resultIntent.putExtra( POSITION, position );
        resultIntent.putExtra( ACTION, UPDATE );
        setResult( Activity.RESULT_OK, resultIntent );
        finish();
    }

    private void handleDelete() {
        Intent intent       = getIntent();
        Intent resultIntent = new Intent();
        Bundle bundle       = intent.getExtras();
        int position        = bundle.getInt( POSITION );

        resultIntent.putExtra( POSITION, position );
        resultIntent.putExtra( ACTION, DELETE );
        setResult( Activity.RESULT_OK, resultIntent );
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_library);
        this.setupToolbar( true );
        this.setupLibraryView();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate( R.menu.menu_view_library, menu );

        return true;
    }

    @Override
    public void onBackPressed() {
        if ( this.libraryHasUpdated ) {
            this.handleLibraryResult();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        switch ( item.getItemId() ) {
            case R.id.optionEdit:
                this.handleEdit();
                return true;
            case R.id.optionDelete:
                this.handleDelete();
                return true;
            case android.R.id.home:
                if ( this.libraryHasUpdated ) {
                    this.handleLibraryResult();
                }
                return false;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent intent ) {
        super.onActivityResult( requestCode, resultCode, intent );

        if ( requestCode == EDIT && resultCode == Activity.RESULT_OK ) {
            Bundle bundle   = intent.getExtras();
            Library library = bundle.getParcelable( EditLibraryActivity.LIBRARY );

            this.library           = library;
            this.libraryHasUpdated = true;

            this.updateLibraryView();
        }
    }
}
