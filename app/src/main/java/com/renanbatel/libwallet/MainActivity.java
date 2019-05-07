package com.renanbatel.libwallet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.renanbatel.libwallet.adapters.LibrariesAdapter;
import com.renanbatel.libwallet.models.Library;
import com.renanbatel.libwallet.resources.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final int LIBRARY_CREATE = 1;
    private static final int LIBRARY_VIEW = 2;

    private List<Library> libraries;
    private RecyclerView librariesRecyclerView;
    private LibrariesAdapter librariesAdapter;
    private RecyclerView.LayoutManager librariesLayoutManager;
    private EditText search;
    private TextView countLibraries;
    private TextView countFeatures;
    private TextView countLanguages;
    private TextView labelLibraries;
    private TextView labelFeatures;
    private TextView labelLanguages;

    protected void openActivity( Class activityClass ) {
        Intent intent = new Intent( this, activityClass );

        startActivity( intent );
    }

    protected void openCreateLibraryActivity() {
        Intent intent = new Intent( this, CreateLibraryActivity.class );

        startActivityForResult( intent, LIBRARY_CREATE);
    }

    protected void openViewLibraryActivity( int position ) {
        Intent intent   = new Intent( this, ViewLibraryActivity.class );
        Library library = this.libraries.get( position );

        intent.putExtra( ViewLibraryActivity.LIBRARY, library );
        intent.putExtra( ViewLibraryActivity.POSITION, position );
        startActivityForResult( intent, LIBRARY_VIEW);
    }

    protected void updateCounters() {
        ConstraintSet constraintSet         = new ConstraintSet();
        SharedPreferences sharedPreferences = this.getSharedPreferences( PreferencesActivity.FILE_KEY, Context.MODE_PRIVATE );
        Boolean showCounter                 = sharedPreferences.getBoolean( PreferencesActivity.SHOW_COUNTERS, true );

        if ( showCounter ) {
            this.countLibraries.setText( String.valueOf( this.libraries.size() ) );
            this.countFeatures.setText( "0" ); // TODO: count features
            this.countLanguages.setText( "0" ); // TODO: count languages
            this.countLibraries.setVisibility( View.VISIBLE );
            this.countFeatures.setVisibility( View.VISIBLE );
            this.countLanguages.setVisibility( View.VISIBLE );
            this.labelLibraries.setVisibility( View.VISIBLE );
            this.labelFeatures.setVisibility( View.VISIBLE );
            this.labelLanguages.setVisibility( View.VISIBLE );
        } else {
            this.countLibraries.setVisibility( View.GONE );
            this.countFeatures.setVisibility( View.GONE );
            this.countLanguages.setVisibility( View.GONE );
            this.labelLibraries.setVisibility( View.GONE );
            this.labelFeatures.setVisibility( View.GONE );
            this.labelLanguages.setVisibility( View.GONE );
        }
    }

    protected void addLibrary( Library library ) {
        this.libraries.add( library );
        this.librariesAdapter.notifyDataSetChanged();
        this.updateCounters();
    }

    protected void updateLibrary( int position, Library library ) {
        this.libraries.set( position, library );
        this.librariesAdapter.notifyItemChanged( position );
    }

    protected void removeLibrary( int position ) {
        this.libraries.remove( position );
        this.librariesAdapter.notifyItemRemoved( position );
        this.updateCounters();
    }

    protected void setupSearch() {
        this.search = findViewById( R.id.search );
    }

    protected void setupLibrariesRecyclerView() {
        libraries = new ArrayList<>();

        this.librariesRecyclerView  = findViewById( R.id.librariesRecyclerView );
        this.librariesLayoutManager = new LinearLayoutManager( this );
        this.librariesAdapter       = new LibrariesAdapter( libraries );

        // Example data
        libraries.add( new Library(
            new Long( 1 ),
            "facebook/react",
            "JS",
            "JavaScript components, UI development, Virtual DOM",
            "Really good performance thanks to VDOM",
            "https://github.com/facebook/react"
        ) );
        libraries.add( new Library(
            new Long( 2 ),
            "reduxjs/redux",
            "JS",
            "Component State Container",
            "Good for using with React",
            ""
        ) );
        libraries.add( new Library(
            new Long( 2 ),
            "sebastianbergmann/phpunit",
            "PHP",
            "Unit test",
            "",
            "https://github.com/sebastianbergmann/phpunit"
        ) );

        this.librariesAdapter.setOnItemClickListener(new LibrariesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openViewLibraryActivity( position );
            }
        });
        this.librariesRecyclerView.setHasFixedSize( false );
        this.librariesRecyclerView.setNestedScrollingEnabled( false );
        this.librariesRecyclerView.setLayoutManager( this.librariesLayoutManager );
        this.librariesRecyclerView.setAdapter( this.librariesAdapter );
    }

    protected void setupCounters() {
        this.countLibraries = findViewById( R.id.countLibraries );
        this.countFeatures  = findViewById( R.id.countFeatures );
        this.countLanguages = findViewById( R.id.countLanguages );
        this.labelLibraries = findViewById( R.id.labelLibraries );
        this.labelFeatures  = findViewById( R.id.labelFeatures );
        this.labelLanguages = findViewById( R.id.labelLanguages );

        this.updateCounters();
    }


    protected void setupButtonAddLib() {
        FloatingActionButton floatingActionButton = findViewById( R.id.buttonAddLib );

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                openCreateLibraryActivity();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setupToolbar( false );
        this.setupSearch();
        this.setupLibrariesRecyclerView();
        this.setupCounters();
        this.setupButtonAddLib();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.updateCounters();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate( R.menu.menu_main, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        switch ( item.getItemId() ) {
            case R.id.optionNewLibrary:
                this.openCreateLibraryActivity();
                return true;
            case R.id.optionPreferences:
                this.openActivity( PreferencesActivity.class );
                return true;
            case R.id.optionAbout:
                this.openActivity( AboutActivity.class );
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent intent ) {
        super.onActivityResult( requestCode, resultCode, intent );

        if ( requestCode == LIBRARY_CREATE && resultCode == Activity.RESULT_OK ) {
            Bundle bundle   = intent.getExtras();
            Library library = bundle.getParcelable( CreateLibraryActivity.LIBRARY );

            this.addLibrary( library );
        } else if ( requestCode == LIBRARY_VIEW && resultCode == Activity.RESULT_OK ) {
            Bundle bundle = intent.getExtras();
            int action    = bundle.getInt( ViewLibraryActivity.ACTION );

            if ( action == ViewLibraryActivity.DELETE ) {
                int position = bundle.getInt( ViewLibraryActivity.POSITION );

                this.removeLibrary( position );
            } else if ( action == ViewLibraryActivity.UPDATE ) {
                int position    = bundle.getInt( ViewLibraryActivity.POSITION );
                Library library = bundle.getParcelable( ViewLibraryActivity.LIBRARY );

                this.updateLibrary( position, library );
            }
        }
    }
}
