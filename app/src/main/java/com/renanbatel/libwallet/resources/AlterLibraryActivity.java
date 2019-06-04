package com.renanbatel.libwallet.resources;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.renanbatel.libwallet.R;
import com.renanbatel.libwallet.adapters.FeaturesAdapter;
import com.renanbatel.libwallet.daos.LibWalletDatabase;
import com.renanbatel.libwallet.models.Feature;

import java.util.ArrayList;
import java.util.List;

public abstract class AlterLibraryActivity extends BaseActivity {

    public static final String LIBRARY = "LIBRARY";

    protected ActionMode featuresActionMode;
    protected View featureSelectedView;
    protected int featureSelectedPosition;
    protected LibWalletDatabase libWalletDatabase;
    protected List<Feature> features;
    protected RecyclerView featuresRecyclerView;
    protected FeaturesAdapter featuresAdapter;
    protected RecyclerView.LayoutManager featuresLayoutManager;
    protected Button buttonSave;
    protected Button buttonAddFeature;
    protected EditText fieldName;
    protected EditText fieldLanguage;
    protected EditText fieldFeatures;
    protected EditText fieldDetails;
    protected EditText fieldRepositories;
    protected TextView labelInstructions;

    protected abstract void handleFormSubmission( View view );
    protected abstract void setupForm();
    protected abstract void addFeature();
    protected abstract void deleteFeature( int featureSelectedPosition );

    private void loadForm() {
        this.buttonSave        = findViewById( R.id.buttonSave );
        this.fieldName         = findViewById( R.id.fieldName );
        this.fieldLanguage     = findViewById( R.id.fieldLanguage );
        this.fieldFeatures     = findViewById( R.id.fieldFeatures );
        this.fieldDetails      = findViewById( R.id.fieldDetails );
        this.fieldRepositories = findViewById( R.id.fieldRepositories );
        this.labelInstructions = findViewById( R.id.labelInstructions );
        this.buttonAddFeature  = findViewById( R.id.buttonAddFeature );

        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                handleFormSubmission( view );
            }
        });
        this.buttonAddFeature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFeature();
            }
        });
        this.setupForm();
    }

    protected void setupFeaturesRecyclerView() {
        features = new ArrayList<>();

        this.featuresRecyclerView  = findViewById( R.id.featuresRecyclerView );
        this.featuresLayoutManager = new LinearLayoutManager( this );
        this.featuresAdapter       = new FeaturesAdapter( features );


        this.featuresAdapter.setOnItemLongClickListener( new FeaturesAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick( int position, View view ) {
                if ( featuresActionMode != null ) {

                    return false;
                } else {
                    featuresActionMode      = toolbar.startActionMode( featuresActionModeCallback );
                    featureSelectedView     = view;
                    featureSelectedPosition = position;

                    view.setBackgroundColor( Color.LTGRAY );

                    return true;
                }
            }
        });
        this.featuresRecyclerView.setHasFixedSize( false );
        this.featuresRecyclerView.setNestedScrollingEnabled( false );
        this.featuresRecyclerView.setLayoutManager( this.featuresLayoutManager );
        this.featuresRecyclerView.setAdapter( this.featuresAdapter );
    }

    protected void addFeature( Feature feature ) {
        this.features.add( feature );
        this.featuresAdapter.notifyDataSetChanged();
        this.fieldFeatures.setText( "" );
    }

    protected void removeFeature( int position ) {
        this.features.remove( position );
        this.featuresAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_edit_library);

        this.libWalletDatabase = LibWalletDatabase.getDatabase( this );

        this.setupToolbar( true );
        this.setupFeaturesRecyclerView();
        this.loadForm();
    }

    private ActionMode.Callback featuresActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate( R.menu.feature_action_menu, menu );

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch ( menuItem.getItemId() ) {
                case R.id.actionDelete:
                    deleteFeature( featureSelectedPosition );
                    actionMode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            featureSelectedView.setBackgroundColor( Color.WHITE );

            featureSelectedView     = null;
            featuresActionMode      = null;
            featureSelectedPosition = -1;
        }
    };
}
