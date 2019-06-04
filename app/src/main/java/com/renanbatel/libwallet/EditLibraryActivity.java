package com.renanbatel.libwallet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.renanbatel.libwallet.models.Feature;
import com.renanbatel.libwallet.models.Library;
import com.renanbatel.libwallet.resources.AlterLibraryActivity;

import java.util.ArrayList;
import java.util.List;

public class EditLibraryActivity extends AlterLibraryActivity {

    private Library library;
    private List<Feature> tempNewFeatures;
    private List<Feature> tempDeleteFeatures;

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

        for ( int i = 0; i < this.tempNewFeatures.size(); i++ ) {
            Feature feature = this.tempNewFeatures.get( i );

            this.libWalletDatabase.featureDAO().insert( feature );
        }
        for ( int i = 0; i < this.tempDeleteFeatures.size(); i++ ) {
            Feature feature = this.tempDeleteFeatures.get( i );

            this.libWalletDatabase.featureDAO().delete( feature );
        }

        intent.putExtra( LIBRARY, library );
        setResult( Activity.RESULT_OK, intent );
        finish();
    }

    @Override
    protected void setupForm() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        this.tempNewFeatures    = new ArrayList<>();
        this.tempDeleteFeatures = new ArrayList<>();
        this.library            = bundle.getParcelable( LIBRARY );

        this.features.addAll( this.libWalletDatabase.featureDAO().getAllFromLibrary( this.library.getId() ) );

        this.featuresAdapter.notifyDataSetChanged();

        this.labelInstructions.setText( R.string.instructions_edit_lib );
        this.fieldName.setText( this.library.getName() );
        this.fieldLanguage.setText( this.library.getLanguage() );
        this.fieldFeatures.setText( this.library.getFeatures() );
        this.fieldDetails.setText( this.library.getDetails() );
        this.fieldRepositories.setText( this.library.getRepositories() );
    }

    @Override
    protected void addFeature() {
        Feature feature = new Feature( this.fieldFeatures.getText().toString(), this.library.getId() );

        this.tempNewFeatures.add( feature );
        this.addFeature( feature );
    }

    @Override
    protected void deleteFeature(int featureSelectedPosition) {
        Feature feature = this.features.get( featureSelectedPosition );

        this.tempDeleteFeatures.add( feature );
        this.removeFeature( featureSelectedPosition );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
